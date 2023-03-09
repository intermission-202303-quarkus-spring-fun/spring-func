package com.senacor.func.spring;

import com.microsoft.azure.functions.ExecutionContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.json.JsonMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import java.util.Map;
import java.util.function.Function;

@SpringBootApplication
public class FunctionApp {

    public static void main(String[] args) {
        SpringApplication.run(FunctionApp.class, args);
    }

    @Bean
    Function<String, String> echo() {
        return payload -> payload;
    }

    @Bean
    Function<Message<String>,String> uppercase(JsonMapper mapper) {
        return message -> {
            var value = message.getPayload();
            var executionContext = (ExecutionContext) message.getHeaders().get("executionContext");
            try {
                Map<String, String> map = mapper.fromJson(value, Map.class);

                if (map != null) {
                    map.forEach((k, v) -> map.put(k, v != null ? v.toUpperCase() : null));
                }

                if (executionContext != null) {
                    executionContext.getLogger().info(String.format("Function: %s is uppercasing %s",
                            executionContext.getFunctionName(), value.toString()));
                }
                return mapper.toString(map);
            } catch (Exception e) {
                e.printStackTrace();
                if (executionContext != null) {
                    executionContext.getLogger().severe("Function could not parse incoming request");
                }
                return "Function error: bad request";
            }
        };

    }
}
