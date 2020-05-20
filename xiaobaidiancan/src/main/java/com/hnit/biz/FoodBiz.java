package com.hnit.biz;


import com.github.pagehelper.PageInfo;
import com.hnit.bean.OrderFood;

public interface FoodBiz {
	public int addFood(OrderFood food);
	public PageInfo<OrderFood> queryFoodList(int pageNumber,int pageSize);
	public OrderFood queryFoodByFid(int fid);
	public int updateFood(OrderFood food);
	public int deleteFoodByFid(int fid);
}
