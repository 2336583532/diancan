package com.hnit.utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSONObject;

public class IpUtil {
	public String getIp() throws ClientProtocolException, IOException {
		String ip="";
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet("http://pv.sohu.com/cityjson");
		CloseableHttpResponse response = null;
		response=httpClient.execute(get);
		HttpEntity responseEntity=response.getEntity();
		if (responseEntity != null) {
			
			String resp=EntityUtils.toString(responseEntity,"GBK");
			resp=resp.replace("var returnCitySN = ", "");
			resp=resp.replace(";", "");
			JSONObject object = (JSONObject) JSONObject.parse(resp);
			ip=object.get("cip").toString();
		}
		return ip;
	}
}
