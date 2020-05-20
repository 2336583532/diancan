package com.hnit.biz.Impl;

import com.hnit.bean.OrderFood;
import com.hnit.bean.OrderList;
import com.hnit.biz.FoodBiz;
import com.hnit.dao.MongoDBDao;
import com.hnit.dao.OrderFoodMapper;
import com.hnit.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodBizImpl implements FoodBiz {
    @Autowired
    private OrderFoodMapper orderFoodMapper;
    @Autowired
    private MongoDBDao mongoDB;
    @Autowired
    private KafkaProducer produder;
    @Override
    public List<OrderFood> getAllFoodMessage() {
        List<OrderFood> result = orderFoodMapper.selectByExample(null);
        return result;
    }

    @Override
    public String addOrder(OrderList orderList) {
        orderList.setOrderList(orderList.getOrderList().replace("null,",""));
        String result = mongoDB.saveObj(orderList);
        if(result != null){
            produder.send("orderList",result);
        }
        return result;
    }
}
