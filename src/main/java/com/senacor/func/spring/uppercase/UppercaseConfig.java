package com.senacor.func.spring.uppercase;

import com.microsoft.azure.functions.ExecutionContext;
import org.springframework.cloud.function.json.JsonMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.Map;
import java.util.function.Function;

@Configuration
public class UppercaseConfig {

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
                            executionContext.getFunctionName(), value));
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
