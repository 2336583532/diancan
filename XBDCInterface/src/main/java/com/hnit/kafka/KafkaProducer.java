package com.hnit.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
@Component
public class KafkaProducer {
    @Autowired
    KafkaTemplate<String,Object> producer;
    public ListenableFuture<SendResult<String, Object>> send(String topic, Object message){
        ListenableFuture<SendResult<String, Object>> send = producer.send(topic, message);
        return send;
    }
}
