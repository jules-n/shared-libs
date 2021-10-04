package com.ynero.ss.health.autoconfigure;

import com.google.pubsub.v1.PubsubMessage;
import com.ynero.ss.health.checks.PubSubHealthPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(PubsubMessage.class)
public class PubSubAutoConfiguration {

    @Bean("pub/sub")
    @ConditionalOnMissingBean
    PubSubHealthPoint pubSubHealth() {
        return new PubSubHealthPoint();
    }
}
