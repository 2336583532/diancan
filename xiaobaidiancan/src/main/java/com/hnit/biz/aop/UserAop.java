package com.hnit.biz.aop;

import java.io.IOException;
import java.util.List;

import javax.persistence.criteria.Order;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hnit.bean.OrderUser;
import com.hnit.bean.OrderUserExample;
import com.hnit.biz.Exception.MyException;
import com.hnit.dao.OrderUserMapper;
import com.hnit.utils.IpUtil;
import com.hnit.utils.UserUtil;

@Aspect
@Component
public class UserAop {
	
	@Autowired
	OrderUserMapper orderUserMapper;
	
	@Pointcut("execution(* com.hnit.biz.UserBiz.addManager(..))")
    public void addManager() {
    
	}
	
	@Pointcut("execution(* com.hnit.biz.UserBiz.addManagerType(..))")
    public void addManagerType() {
    
	}
	
	@Pointcut("execution(* com.hnit.biz.UserBiz.updataManagerType(..))")
    public void updataManagerType() {
    
	}
	
	@Pointcut("execution(* com.hnit.biz.UserBiz.deleteManager(..))")
    public void deleteManager() {
    
	}

	@Before("addManager() || addManagerType() || updataManagerType() || deleteManager()")
	public void theAuthorityNeedOne() throws MyException {
		UserUtil userUtil = new UserUtil();
		try {
			String phone = userUtil.getUserPhone();
			if(phone==null || phone == "") {
				throw new MyException("请重试");
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
			if(user.get(0).getType() != 1) {
				throw new MyException("权限不足");
			}
		} catch (IOException e) {
			throw new MyException("请重试");
		}
	}
	
	
	
	
}
