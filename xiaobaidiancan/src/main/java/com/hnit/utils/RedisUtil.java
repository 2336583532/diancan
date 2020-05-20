package com.hnit.utils;

import java.util.Random;

import redis.clients.jedis.Jedis;
public class RedisUtil {
	private static Jedis jedis;
	
	private void getJedis() {
		jedis=new Jedis("47.94.88.204");
		jedis.auth("TRX.0721");
	
	}
	public void addList(String key,String value) {
		
		getJedis();
		jedis.lpush(key, value);
		jedis.close();
		
	}
	
	public void deleteList(String key,String value) {
		getJedis();
		jedis.lrem(key, 0, value);
		jedis.close();
	}
	
	public void addString(String key,String value) {
		
		getJedis();
		jedis.set(key, value);
		jedis.close();
		
	}
	
	public void deleteKey(String key) {
		getJedis();
		jedis.del(key);
		jedis.close();
	}
	
	public String getValue(String key) {
		getJedis();
		String value = jedis.get(key);
		jedis.close();
		return value;
	}
	
	public String getRandomFromList(String listName) {
		getJedis();
		Long llen = jedis.llen(listName);
		if(llen == null || llen == 0) {
			return null;
		}
		Random r = new Random();
		return jedis.lindex(listName,r.nextInt((int) (llen+0)));
		
	}
	
	
	
}
