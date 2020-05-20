package com.hnit.biz.aop;

import java.io.IOException;
import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.hnit.bean.OrderUser;
import com.hnit.bean.OrderUserExample;
import com.hnit.biz.Exception.MyException;
import com.hnit.dao.OrderUserMapper;
import com.hnit.utils.UserUtil;

@Aspect
@Component
public class FoodAop {
	@Autowired
	OrderUserMapper orderUserMapper;
	@Pointcut("execution(* com.hnit.biz.FoodBiz.addFood(..))")
    public void addFood() {
		
	}
	
	@Pointcut("execution(* com.hnit.biz.FoodBiz.deleteFoodByFid(..))")
    public void deleteFoodByFid() {
		
	}
	
	@Pointcut("execution(* com.hnit.biz.FoodBiz.updateFood(..))")
    public void updateFood() {
		
	}
	
	@Before("addFood()|| deleteFoodByFid() || updateFood()")
	public void theAuthorityNeedTwo() throws MyException {
		UserUtil userUtil = new UserUtil();
		String phone = null;
		try {
			phone = userUtil.getUserPhone();
		} catch (IOException e) {
			throw new MyException("请重试!");
		}
		if(phone == null) {
			throw new MyException("登录信息已过期，请重新登录");
		}
		OrderUserExample orderUserExample = new OrderUserExample();
		orderUserExample.createCriteria().andPhoneEqualTo(phone);
		List<OrderUser> user = orderUserMapper.selectByExample(orderUserExample);
		if(user.isEmpty() || user.size()==0) {
			throw new MyException("请重试");
		}
		if(user.get(0).getType() > 2) {
			throw new MyException("权限不足");
		}
	}
	
}
