package com.hnit.biz.Impl;

import com.hnit.bean.OrderList;
import com.hnit.biz.OrderListBiz;
import com.hnit.dao.MongoDBDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderListBizImpl implements OrderListBiz {
    @Autowired
    private MongoDBDao mongoDBDao;

    @Override
    public List<OrderList> getOrderByUsername(String userName) {
        List<OrderList> orderByUsername = mongoDBDao.getOrderByUsername(userName);
        return orderByUsername;
    }
}
