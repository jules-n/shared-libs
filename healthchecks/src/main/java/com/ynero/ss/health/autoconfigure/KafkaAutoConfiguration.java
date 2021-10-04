package com.ynero.ss.health.autoconfigure;

import com.ynero.ss.health.checks.KafkaHealthPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration.class)
public class KafkaAutoConfiguration {

    @Bean("kafka")
    @ConditionalOnMissingBean
    KafkaHealthPoint kafkaHealth() {
        return new KafkaHealthPoint();
    }
}
