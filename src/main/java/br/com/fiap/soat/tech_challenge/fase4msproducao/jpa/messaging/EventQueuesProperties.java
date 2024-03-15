package br.com.fiap.soat.tech_challenge.fase4msproducao.jpa.messaging;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "events.queues")
public class EventQueuesProperties {

    private String queue1_name;
    private String queue2_name;
    private String queue3_name;

    public String getQueue1_name() {
        return queue1_name;
    }

    public void setQueue1_name(String queue1_name) {
        this.queue1_name = queue1_name;
    }

    public String getQueue2_name() {
        return queue2_name;
    }

    public void setQueue2_name(String queue2_name) {
        this.queue2_name = queue2_name;
    }

    public String getQueue3_name() {
        return queue3_name;
    }

    public void setQueue3_name(String queue3_name) {
        this.queue3_name = queue3_name;
    }

}
