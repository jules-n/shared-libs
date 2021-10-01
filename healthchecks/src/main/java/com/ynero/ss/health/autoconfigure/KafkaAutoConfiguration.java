package com.ynero.ss.health.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(/* TODO: add class that is available only when kafka is used */)
public class KafkaAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    YourHealthIndicatorBean kafkaHealth() {
        // TODO: implement
        throw new UnsupportedOperationException();
    }

    @Bean
    @ConditionalOnMissingBean
    YourRedinessIndicatorBean kafkaReadiness() {
        // TODO: implement
        throw new UnsupportedOperationException();
    }
}
