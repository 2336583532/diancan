package com.hnit.dao;

import com.hnit.bean.OrderList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MongoDBDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public String saveObj(OrderList orderList) {

        OrderList save = mongoTemplate.insert(orderList);
        if(save != null && save.getId() != null){
            return save.getId();
        }else{
            return null;
        }
    }

    public List<OrderList> getOrderByUsername(String userName){
        Query query = new Query(Criteria.where("username").is(userName));
        List<OrderList> orderLists = mongoTemplate.find(query,OrderList.class,"orderList");
        return orderLists;
    }
}
