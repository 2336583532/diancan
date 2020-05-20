package com.hnit.controller;

import com.hnit.bean.OrderFood;
import com.hnit.bean.OrderList;
import com.hnit.biz.FoodBiz;
import com.hnit.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class FoodController {
    @Autowired
    private FoodBiz foodBiz;
    @Autowired
    private KafkaProducer producer;

    @RequestMapping("/getAllFoodMessage")
    @ResponseBody
    public List getAllFoodMessage(){
        List<OrderFood> allFoodMessage = foodBiz.getAllFoodMessage();
        return allFoodMessage;

    }

    @RequestMapping("/addOrder")
    @ResponseBody
    public int addOrder(OrderList orderList){
        String result = foodBiz.addOrder(orderList);
        if(result != null){
            return 1;
        }else{
            return -1;
        }

    }

}
