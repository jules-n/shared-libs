
package com.ynero.ss.health.checks;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeClusterOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;



@Component("kafka")
@Slf4j
public class KafkaHealthPoint implements HealthIndicator {

    @Setter(onMethod_ = {@Autowired})
    private AdminClient client;

    @Override
    public Health health() {
        DescribeClusterOptions options = new DescribeClusterOptions()
                .timeoutMs(1000);
        try {
            var result = client.describeCluster(options);
            var clusterId = result.clusterId().get(100, TimeUnit.MILLISECONDS);
            log.info("Kafka healthcheck up. ClusterId: {}", clusterId);
            return Health.status(Status.UP).build();
        }
        catch (Exception ex){
            log.error("Kafka health check failed with ex: ", ex);
            return Health.down(ex).build();
        }
    }
}
