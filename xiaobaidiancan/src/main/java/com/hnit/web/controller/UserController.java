package com.hnit.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.hnit.bean.OrderManagertype;
import com.hnit.bean.OrderUser;
import com.hnit.biz.UserBiz;
import com.hnit.biz.Exception.MyException;
import com.hnit.utils.OutputObject;

import io.netty.util.internal.ObjectUtil;

@RestController
public class UserController {
	@Autowired
	private UserBiz userBiz;
	
	
	
	@Value("${pagehelper.pageSize}")
	private int pageSize;
	
	private Map<String, String> codeMap = new Hashtable<String, String>();
	
	@RequestMapping("/toLogin")
	public ModelAndView toLogin(ModelAndView model) {
		model.setViewName("freemarker/login");
		return model;
	}
	
	@RequestMapping("/login")
	public ModelAndView login(OrderUser user,ModelAndView model) {
		OrderUser orderUser = null;
		try {
			orderUser = userBiz.login(user);
		} catch (ClientProtocolException e) {
			OutputObject out = new OutputObject();
			out.setCode(-1);
			out.setMessage("服务器开小差!");
			model.addObject("data", out);
			model.setViewName("freemarker/login");
		} catch (IOException e) {
			OutputObject out = new OutputObject();
			out.setCode(-1);
			out.setMessage("服务器开小差!");
			model.addObject("data", out);
			model.setViewName("freemarker/login");
		}
		if(orderUser==null) {
			OutputObject out = new OutputObject();
			out.setMessage("用户名或密码错误");
			model.addObject("data", out);
			model.setViewName("freemarker/login");
		}else {
			model.addObject("user", orderUser);
			model.setViewName("freemarker/index");
		}
		
		return model;
	}
	
	@RequestMapping("/toAddManager")
	public ModelAndView toAddManager(ModelAndView model) {
		List<OrderManagertype> list = userBiz.queryAllManagerType();
		model.addObject("managerType", list);
		model.setViewName("freemarker/addManager");
		return model;
	}
	
	@RequestMapping("/addManager")
	@ResponseBody
	public OutputObject addManager(OrderUser user) {
		OutputObject out = new OutputObject();
		try {
			int result = userBiz.addManager(user);
			if(result<0) {
				out.setCode(-1);
				out.setMessage("添加失败");
			}else {
				out.setCode(1);
			}
		} catch (MyException | UnsupportedEncodingException e) {
			out.setCode(-1);
			out.setMessage(e.getMessage());
		}
		return out;
	}
	
	@RequestMapping("toQueryManagerList")
	public ModelAndView toQueryManagerList(ModelAndView model) {
		model.setViewName("freemarker/managerList");
		return model;
	}
	
	@RequestMapping("queryManagerList")
	@ResponseBody
	public Map<String,Object> queryManagerList(@RequestParam(name="limit")Integer pageSize,@RequestParam(name="page")Integer pageNumber){
		if(pageSize!=null) {
			this.pageSize=pageSize;
		}
		PageInfo<List<Map<String, Object>>> userList = userBiz.queryManagerList(pageNumber,pageSize);
		Map<String,Object> result=new HashMap<String, Object>();
		result.put("code", 0);
		result.put("msg", "");
		result.put("data", userList.getList());
		result.put("count",userList.getTotal());
		return result;
	}
	
	@RequestMapping("/toAddManagerType")
	public ModelAndView toAddManagerType(ModelAndView model) {
		model.setViewName("freemarker/addManagerType");
		return model;
	}
	
	@RequestMapping("/addManagerType")
	@ResponseBody
	public OutputObject addManager(OrderManagertype orderManagerType) {
		OutputObject out = new OutputObject();
		try {
			int result=userBiz.addManagerType(orderManagerType);
			if(result > 0) {
				out.setCode(1);
			}else {
				out.setCode(-1);
			}
		} catch (MyException e) {
			out.setCode(-1);
			out.setMessage(e.getMessage());
		}
		return out;
	}
	
	@RequestMapping("/toUpdataType")
	public ModelAndView toUpdateType(@RequestParam(name="uid") Integer uid,ModelAndView model) {
		OrderUser user = userBiz.queryManagerByUid(uid);
		List<OrderManagertype> list = userBiz.queryAllManagerType();
		model.addObject("managerType", list);
		model.addObject("user",user);
		model.setViewName("freemarker/updataType");
		return model;
	}
	
	@RequestMapping("/updataManagerType")
	@ResponseBody
	public OutputObject updataManagerType(OrderUser user) {
		OutputObject out = new OutputObject();
		try {
			int result=userBiz.updataManagerType(user);
			if(result>0) {
				out.setCode(1);
			}else {
				out.setCode(-1);
			}
		} catch (MyException e) {
			out.setCode(-1);
			out.setMessage(e.getMessage());
		}
		return out;
	}
	
	@RequestMapping("/deleteManager")
	@ResponseBody
	public OutputObject deleteManager(OrderUser user) {
		OutputObject out = new OutputObject();
		try {
			int result=userBiz.deleteManager(user);
			if(result>0) {
				out.setCode(1);
			}else {
				out.setCode(-1);
			}
		} catch (MyException e) {
			out.setCode(-1);
			out.setMessage(e.getMessage());
		}
		return out;
	}
	
	@RequestMapping("/toChat")
	public ModelAndView toChat(ModelAndView model,@RequestParam("userName") String userName) {
		model.addObject("userName", userName);
		model.addObject("id", userName.hashCode());
		model.setViewName("freemarker/chat");
		return model;
	}
	
	@RequestMapping("/toKefu")
	public ModelAndView toKefu(ModelAndView model,@RequestParam("userName") String userName) {
		model.addObject("userName", userName);
		model.addObject("id", userName.hashCode());
		model.setViewName("freemarker/kefu");
		return model;
	}
	
	@RequestMapping("/tochangePassword")
	public ModelAndView tochangePassword(String phone,ModelAndView model) {
		model.addObject("phone", phone);
		model.setViewName("freemarker/changePassword");
		return model;
	}
	
	@RequestMapping("/getChangePasswordCode")
	@ResponseBody
	public OutputObject changePassword(@RequestParam("phone")String phone) {
		OutputObject out = new OutputObject();
		try {
			String code=userBiz.changePassword(phone);
			codeMap.put(phone,code);
			out.setCode(1);
		} catch (MyException e) {
			out.setCode(-1);
			out.setMessage(e.getMessage());
		}
		return out;
	}
	
	@RequestMapping("/changePassword")
	@ResponseBody
	public OutputObject changePassword(@RequestParam("password1") String password1,@RequestParam("password2") String password2,@RequestParam("code")String code,@RequestParam("phone")String phone) {
		OutputObject out = new OutputObject();
		String theCode=codeMap.get(phone);
		if(code==null || theCode==null || !theCode.equals(code)) {
			out.setCode(-1);
			out.setMessage("验证码错误");
		}
		if(!password1.equals(password2)) {
			out.setCode(-1);
			out.setMessage("两次输入密码不一致");
		}
		int result=userBiz.changePassword(phone,password1);
		if(result>0) {
			out.setCode(1);
		}else {
			out.setCode(-1);
		}
		return out;
	}
}
