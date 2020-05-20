package com.hnit.biz;

import com.hnit.bean.OrderList;

import java.util.List;

public interface OrderListBiz {
    public List<OrderList> getOrderByUsername(String userName);
}
