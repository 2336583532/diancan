package com.hnit.biz.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnit.bean.OrderFood;
import com.hnit.biz.FoodBiz;
import com.hnit.dao.OrderFoodMapper;
@Service
public class FoodBizImpl implements FoodBiz{
	@Autowired
	OrderFoodMapper orderFoodMapper;
	@Value("${pagehelper.pageSize}")
	private int pageSize;
	@Override
	public int addFood(OrderFood food) {
		if(food.getFname()==null || food.getFname().trim()=="" || food.getFprice()==null) {
			return -1;
		}
		return orderFoodMapper.insert(food);
	}
	@Override
	public PageInfo<OrderFood> queryFoodList(int pageNumber, int pageSize) {
		PageHelper.startPage(pageNumber, pageSize);
		List<OrderFood> foodList = orderFoodMapper.selectByExample(null);
		PageInfo<OrderFood> pageInfo = new PageInfo<OrderFood>(foodList);
		return pageInfo;
	}
	@Override
	public OrderFood queryFoodByFid(int fid) {
		OrderFood food=orderFoodMapper.selectByPrimaryKey(fid);
		return food;
	}
	@Override
	public int updateFood(OrderFood food) {
		if(food.getFname()==null || food.getFname().trim()=="" || food.getFprice()==null) {
			return -1;
		}
		int result = orderFoodMapper.updateByPrimaryKey(food);
		return result;
	}
	@Override
	public int deleteFoodByFid(int fid) {
		int result = orderFoodMapper.deleteByPrimaryKey(fid);
		return result;
	}

}
