package com.hnit.webSocket;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.hnit.biz.UserBiz;
import com.hnit.web.controller.UserController;

@ServerEndpoint("/kefuWebSocket/{userName}")
@Component
public class kefuWebSocket {
    private static Map<String,kefuWebSocket> webSocketSet = new ConcurrentHashMap<>();
 // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
	private static UserBiz userBiz;
	
	@Autowired
	public void setUserBiz(UserBiz userBiz) {
		this.userBiz=userBiz;
	}
	
	@OnOpen
	public void open(Session session,@PathParam(value="userName") String userName) {
		this.session=session;
		webSocketSet.put(userName, this);
	}
	
	@OnClose
	public void close(@PathParam("userName")String  userName) {
		
	}
	
	@OnMessage
	public void message(String message) {
		JSONObject object = (JSONObject) JSONObject.parse(message);
		if(object != null && object.get("type").toString().equals("msg")) {
			sendMessageToKefu(object.get("data").toString());
		}else if(object != null && object.get("type").toString().equals("huifu")){
			sendMessageToUser(object.get("to").toString(),object.get("data").toString());
		}
	}

	private void sendMessageToKefu(String message) {
        UserWebSocket.sendMessage(message);
        
        
	}
	
	public static void sendMessageToUser(String  userName,String message) {
		kefuWebSocket webSocket = webSocketSet.get(userName);
		Session session = webSocket.session;
        
        if (session.isOpen()) {
        	try {
				session.getBasicRemote().sendText(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
	}
	
	
}
