
package com.ynero.ss.health.checks;

import lombok.Setter;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeClusterOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;



@Component("kafka")
public class KafkaHealthPoint implements HealthIndicator {

    @Setter(onMethod_ = {@Autowired})
    private AdminClient client;

    @Override
    public Health health() {
        DescribeClusterOptions options = new DescribeClusterOptions()
                .timeoutMs(1000);
        var result = client.describeCluster(options);
        try {
            var clusterId = result.clusterId().get(100, TimeUnit.MILLISECONDS);
            return Health.status(Status.UP).build();
        }
        catch (Exception ex){
            return Health.down(ex).build();
        }
    }
}
