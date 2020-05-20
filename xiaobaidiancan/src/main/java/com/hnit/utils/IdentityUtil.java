package com.hnit.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.hnit.bean.OrderUser;

public class IdentityUtil {
	//判断员工信息是否真实
	/**
	 * 这里只进行了身份证是否为真实存在，没有做身份证和姓名的比对
	 * @param user
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public boolean isReally(OrderUser user) throws UnsupportedEncodingException {
		boolean result = false;
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		StringBuffer params = new StringBuffer();
		params.append("id=" + URLEncoder.encode(user.getIdentity(), "utf-8"));
		HttpPost httpPost = new HttpPost("https://shenfen.supfree.net/search.asp" + "?" + params);
		
		// 响应模型
				CloseableHttpResponse response = null;
				try {
					// 由客户端执行(发送)Post请求
					response = httpClient.execute(httpPost);
			
					// 从响应模型中获取响应实体
					HttpEntity responseEntity = response.getEntity();
					
					if (responseEntity != null) {
			
						String resp=EntityUtils.toString(responseEntity,"GBK");
						result=resp.contains("身份证号码校验为合法号码");
						
					}
				} catch (Exception e) {
					e.printStackTrace();
					result= false;
				} finally {
					try {
						// 释放资源
						if (httpClient != null) {
							httpClient.close();
						}
						if (response != null) {
							response.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return result;
	}
}
