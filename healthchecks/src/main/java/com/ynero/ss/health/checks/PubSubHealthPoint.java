package com.ynero.ss.health.checks;

import com.google.api.gax.rpc.ApiException;
import com.google.api.gax.rpc.StatusCode;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration("pub/sub")
@Slf4j
public class PubSubHealthPoint implements HealthIndicator {

    @Setter(onMethod_ = {@Autowired})
    private PubSubTemplate pubSubTemplate;

    @Override
    public Health health() {
        try {
            this.pubSubTemplate.pull("health-sub" + UUID.randomUUID(), 1, true);
        } catch (ApiException ex) {
            var code = ex.getStatusCode().getCode();
            if (code.equals(StatusCode.Code.NOT_FOUND) || code.equals(StatusCode.Code.PERMISSION_DENIED)) {
                log.info("PubSub healthcheck status: Up");
                return Health.up().build();
            } else {
                log.error("PubSub healthcheck failed with exception:", ex);
                return Health.down(ex).build();
            }
        } catch (Exception ex) {
            log.error("PubSub healthcheck failed with unpredictable exception:", ex);
        }
        return Health.unknown().build();
    }
}
