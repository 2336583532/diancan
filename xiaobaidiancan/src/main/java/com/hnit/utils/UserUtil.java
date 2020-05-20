package com.hnit.utils;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.boot.autoconfigure.cache.CacheProperties.Redis;

public class UserUtil {
	public String getUserPhone() throws ClientProtocolException, IOException {
		IpUtil ipUtil = new IpUtil();
		RedisUtil redisUtil = new RedisUtil();
		String ip = ipUtil.getIp();
		String phone = redisUtil.getValue(ip);
		return phone;

	}
	
	public static String getRandomToOrders() {
		RedisUtil redis = new RedisUtil();
		String phone=redis.getRandomFromList("manager_3");
		if(phone == null) {
			phone=redis.getRandomFromList("manager_2");
			if(phone == null) {
				phone=redis.getRandomFromList("manager_1");
			}
		}
		return phone;
	}
}
