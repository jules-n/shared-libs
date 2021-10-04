package com.ynero.ss.health.checks;

import com.google.api.gax.rpc.ApiException;
import com.google.api.gax.rpc.StatusCode;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration("pub/sub")
public class PubSubHealthPoint implements HealthIndicator {

    @Setter(onMethod_ = {@Autowired})
    private PubSubTemplate pubSubTemplate;

    @Override
    public Health health() {
        try {
           this.pubSubTemplate.pull("health-sub"+ UUID.randomUUID(), 1, true);
        } catch (ApiException ex) {
            var code = ex.getStatusCode().getCode();
            if (code.equals(StatusCode.Code.NOT_FOUND) || code.equals(StatusCode.Code.PERMISSION_DENIED))
                return Health.up().build();
            else return Health.down(ex).build();
        }
        return Health.unknown().build();
    }
}
