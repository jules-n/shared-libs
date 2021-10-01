package com.ynero.ss.health.autoconfigure;

import com.ynero.ss.health.checks.KafkaHealthPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaUtils;

@Configuration
@ConditionalOnClass(org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration.class)
public class KafkaAutoConfiguration {

  @Bean
    @ConditionalOnMissingBean
    KafkaHealthPoint kafkaHealth() {
        return new KafkaHealthPoint();
    }
}
