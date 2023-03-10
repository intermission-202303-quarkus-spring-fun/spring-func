package com.senacor.func.spring.echo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class EchoConfig {
    @Bean
    Function<String, String> echo() {
        return payload -> payload;
    }

}
