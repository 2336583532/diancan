package com.hnit.biz;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.github.pagehelper.PageInfo;
import com.hnit.bean.OrderManagertype;
import com.hnit.bean.OrderUser;
import com.hnit.biz.Exception.MyException;

public interface UserBiz {
	//用户登录返回用户类型
	public OrderUser login(OrderUser user) throws ClientProtocolException, IOException;

	public int addManager(OrderUser user) throws MyException, UnsupportedEncodingException;

	public PageInfo<List<Map<String, Object>>> queryManagerList(int pageNumber,int pageSize);

	public List<OrderManagertype> queryAllManagerType();

	public int addManagerType(OrderManagertype orderManagerType) throws MyException;

	public OrderUser queryManagerByUid(int uid);

	public void userOutLogin() throws ClientProtocolException, IOException;

	public int updataManagerType(OrderUser user) throws MyException;

	public int deleteManager(OrderUser user) throws MyException;

	public String changePassword(String phone) throws MyException;

	public int changePassword(String phone, String password1);
}
