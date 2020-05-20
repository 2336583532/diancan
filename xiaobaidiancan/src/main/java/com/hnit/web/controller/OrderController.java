package com.hnit.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hnit.bean.OrderList;
import com.hnit.biz.OrderBiz;
import com.hnit.utils.OutputObject;

@RestController
public class OrderController {
	@Autowired
	OrderBiz orderBiz;
	
	@Value("${pagehelper.pageSize}")
	private int pageSize;
	
	@RequestMapping("/toOrderList")
	public ModelAndView toOrderList(ModelAndView model) {
		model.setViewName("freemarker/orderList");
		return model;
	}
	
	@RequestMapping("/queryOrderList")
	@ResponseBody
	public Map<String,Object> queryOrderList(@RequestParam(name = "limit")Integer pageSize,@RequestParam(name = "page")Integer pageNumber){
		if(pageSize!=null) {
			this.pageSize=pageSize;
		}
		Map<String,Object> result=orderBiz.queryOrderList(pageNumber, pageSize);
		if(result != null) {
			result.put("code",0);
			result.put("msg", "");
		}else {
			result.put("code",1);
			result.put("msg", "请刷新重试");
		}
		
		return result;
	}
	
	@RequestMapping("/orders")
	@ResponseBody
	public OutputObject orders(String id) {
		OutputObject out = new OutputObject();
		long result=orderBiz.orders(id);
		if(result>0) {
			out.setCode(1);
		}else {
			out.setCode(-1);
		}
		return out;
	}
}
