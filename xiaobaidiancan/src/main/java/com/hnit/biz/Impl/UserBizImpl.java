package com.hnit.biz.Impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnit.bean.OrderManagertype;
import com.hnit.bean.OrderManagertypeExample;
import com.hnit.bean.OrderUser;
import com.hnit.bean.OrderUserExample;
import com.hnit.bean.OrderUserExample.Criteria;
import com.hnit.biz.UserBiz;
import com.hnit.biz.Exception.MyException;
import com.hnit.dao.OrderManagertypeMapper;
import com.hnit.dao.OrderUserMapper;
import com.hnit.utils.IdentityUtil;
import com.hnit.utils.IpUtil;
import com.hnit.utils.RedisUtil;
import com.hnit.utils.SMSUtil;
@Service
public class UserBizImpl implements UserBiz{
	@Autowired
	private OrderUserMapper orderUserMapper;
	@Autowired
	private OrderManagertypeMapper orderManagertypeMapper;
	
	@Override
	public OrderUser login(OrderUser user) throws ClientProtocolException, IOException {
		
		
		OrderUserExample orderUserExample = new OrderUserExample();
		Criteria criteria=orderUserExample.createCriteria();
		criteria.andPhoneEqualTo(user.getUname()).andPasswordEqualTo(user.getPassword());
		List<OrderUser> listOrder = orderUserMapper.selectByExample(orderUserExample);
		if(listOrder==null||listOrder.size()==0) {
			return null;
		}else {
			OrderUser orderUser = listOrder.get(0);
			//redis存储格式,manager_用户权限:手机号，ip:手机号,手机号:权限
			IpUtil ipUtil = new IpUtil();
			RedisUtil redisUtil = new RedisUtil();
			String ip=ipUtil.getIp();
			redisUtil.addString(ip, orderUser.getPhone());
			redisUtil.addList("manager_"+orderUser.getType(), orderUser.getPhone());
			redisUtil.addString(orderUser.getPhone(), orderUser.getType()+"");
			return orderUser;
		}
	}

	@Override
	public int addManager(OrderUser user) throws MyException, UnsupportedEncodingException {
		IdentityUtil idUtil = new IdentityUtil();
		if(!idUtil.isReally(user)) {
			throw new MyException("身份证验证失败");
		}
		if(user.getPhone()==null || user.getIdentity()==null || user.getSex()==null || user.getUname()==null || user.getType()==null) {
			throw new MyException("必填字段不能为空");
		} 
		OrderUserExample orderManagerExample = new OrderUserExample();
		orderManagerExample.createCriteria().andPhoneEqualTo(user.getPhone());
		List<OrderUser> selectByExample = orderUserMapper.selectByExample(orderManagerExample);
		if(selectByExample.size()>0) {
			throw new MyException("手机号已存在");
		}
		user.setPassword(user.getPhone());
		int result = orderUserMapper.insert(user);
		return result;
	}

	@Override
	public PageInfo<List<Map<String, Object>>> queryManagerList(int pageNumber,int pageSize) {
		PageHelper.startPage(pageNumber, pageSize);
		List<Map<String, Object>> userList = orderUserMapper.selectManager();
		PageInfo<List<Map<String,Object>>> pageInfo = new PageInfo(userList);
		return pageInfo;
	}

	@Override
	public List<OrderManagertype> queryAllManagerType() {
		List<OrderManagertype> list = orderManagertypeMapper.selectByExample(null);
		return list;
	}

	@Override
	public int addManagerType(OrderManagertype orderManagerType) throws MyException {
		OrderManagertypeExample orderManagertypeExample = new OrderManagertypeExample();
		orderManagertypeExample.createCriteria().andTypenameEqualTo(orderManagerType.getTypename());
		List<OrderManagertype> tempList = orderManagertypeMapper.selectByExample(orderManagertypeExample);
		if(tempList.size()>0) {
			throw new MyException("该员工类型已存在");
		}
		int result = orderManagertypeMapper.insert(orderManagerType);
		return result;
	}

	@Override
	public OrderUser queryManagerByUid(int uid) {
		OrderUser user = orderUserMapper.selectByPrimaryKey(uid);
		return user;
	}

	@Override
	public void userOutLogin() throws ClientProtocolException, IOException {
		IpUtil ipUtil = new IpUtil();
		RedisUtil redisUtil = new RedisUtil();
		String ip=ipUtil.getIp();
		String phone = redisUtil.getValue(ip);
		String type=redisUtil.getValue(phone);
		redisUtil.deleteList("manager_"+type, phone);
		redisUtil.deleteKey(ip);
		redisUtil.deleteKey(phone);
	}

	@Override
	public int updataManagerType(OrderUser user) throws MyException {
		if(user.getUid()==null) {
			throw new MyException("修改失败");
		}
		int result = orderUserMapper.updateByPrimaryKeySelective(user);
		return result;
	}

	@Override
	public int deleteManager(OrderUser user) throws MyException {
		if(user.getUid()==null || user.getUid()==null) {
			throw new MyException("删除失败");
		}
		int result = orderUserMapper.deleteByPrimaryKey(user.getUid());
		return result;
	}

	@Override
	public String changePassword(String phone) throws MyException {
		StringBuffer str = new StringBuffer();
		Random r = new Random();
		for(int i=0; i<6;i++) {
			str.append(r.nextInt(10));
		}
		String templateParam = "{code:"+str+"}";
		String result= SMSUtil.send(phone, "小白点餐", "SMS_190267917", templateParam);
		 
		if(!result.equals("OK")) {
			throw new MyException("出错啦,请重试!");
		}
		return templateParam;
	}

	@Override
	public int changePassword(String phone, String password1) {
		int result=orderUserMapper.updatePassword(phone,password1);
		return result;
	}

}
