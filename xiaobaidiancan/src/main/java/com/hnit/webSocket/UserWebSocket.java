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

import com.hnit.biz.UserBiz;
import com.hnit.utils.UserUtil;

@ServerEndpoint("/userWebSocket/{phone}")
@Component
public class UserWebSocket {
	// concurrent包的线程安全Set，用来存放每个客户端对应的ProductWebSocket对象。
    private static Map<String,UserWebSocket> webSocketSet = new ConcurrentHashMap<>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
	private static UserBiz userBiz;
	
	@Autowired
	public void setUserBiz(UserBiz userBiz) {
		this.userBiz=userBiz;
	}
	
	@OnOpen
	public void open(Session session,@PathParam(value="phone") String phone) {
		this.session=session;
		webSocketSet.put(phone, this);
	}
	
	@OnClose
	public void close(@PathParam("phone")String  phone) {
		try {
			userBiz.userOutLogin();
			webSocketSet.remove(phone);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@OnMessage
	public void message(String message) {
		
	}
	
	//服务器推送新订单
	public static void sendMessage(String phone, String message) {
		UserWebSocket webSocket = webSocketSet.get(phone);
		Session session = webSocket.session;
        try {
            if (session.isOpen()) {
                // 同步消息
            	/**
            	 * 1:提示前台接单
            	 */
                session.getBasicRemote().sendText(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
           
        }
    }
	
	//服务器消息给客服
		public static void sendMessage(String message) {
			String phone = UserUtil.getRandomToOrders();
			kefuWebSocket.sendMessageToUser(phone, message);
	        
	    }
}
