package com.hnit.controller;

import com.hnit.Utils.OutputObject;
import com.hnit.bean.OrderList;
import com.hnit.biz.OrderListBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderListBiz orderListBiz;

    @RequestMapping("/getOrderByUsername")
    @ResponseBody
    public OutputObject getOrderByUsername(String userName){
        OutputObject out = new OutputObject();
        List<OrderList> orderByUsername = orderListBiz.getOrderByUsername(userName);
        out.setData(orderByUsername);
        return out;
    }

}
