package com.ynero.ss.health.autoconfigure;

import com.ynero.ss.health.checks.PubSubHealthPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(PubSubTemplate.class)
public class PubSubAutoConfiguration {

    @Bean("pub/sub")
    @ConditionalOnMissingBean
    PubSubHealthPoint pubSubHealth() {
        return new PubSubHealthPoint();
    }
}
