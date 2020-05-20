package com.hnit.biz.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

import com.hnit.bean.OrderList;
import com.hnit.biz.OrderBiz;
import com.hnit.dao.MongodbDao;
import com.hnit.utils.UserUtil;
import com.hnit.webSocket.UserWebSocket;

@Service
public class OrderBizImpl implements OrderBiz{
	@Autowired
	MongodbDao mongodb;

	@Override
	public Map<String, Object> queryOrderList(int pageNumber, int pageSize) {
		Map<String, Object> orderList=mongodb.getData("orderList",pageNumber,pageSize);
		return orderList;
	}

	@Override
	public long orders(String id) {
		long result=mongodb.orders(id);
		return result;
	}

}
