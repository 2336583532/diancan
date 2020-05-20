package com.huit;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class XiaobaidiancanApplicationTests {

	@Test
	void contextLoads() {
		RedisTemplate<String, String> redis = new RedisTemplate<String, String>();
		System.out.println(redis);
	}

}
