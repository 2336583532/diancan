package com.hnit.biz;

import com.hnit.bean.OrderFood;
import com.hnit.bean.OrderList;

import java.util.List;;

public interface FoodBiz {
    public List<OrderFood> getAllFoodMessage();

    public String addOrder(OrderList orderList);
}
