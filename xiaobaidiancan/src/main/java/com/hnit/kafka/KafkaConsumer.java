package com.hnit.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.hnit.utils.UserUtil;
import com.hnit.webSocket.UserWebSocket;
@Component
public class KafkaConsumer {
	@KafkaListener(topics = "${kafka.topics}")
	public void getData(ConsumerRecord<String, Object> record) {
		Object value=record.value();
		if(value != null) {
			String phone = UserUtil.getRandomToOrders();
			if(phone != null) {
				UserWebSocket.sendMessage(phone, "1");
			}
			
		}
	}
}
