package com.hnit.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.hnit.bean.OrderList;
import com.mongodb.WriteResult;
import com.mongodb.client.result.UpdateResult;

@Repository
public class MongodbDao {
	@Autowired
    private MongoTemplate mongoTemplate;
	
	public Map<String,Object> getData(String collectionName,int pageNumber,int pageSize) {
		
		
		Map<String,Object> map = new HashMap<>();
		Query query = new Query();
        int totalCount = (int) mongoTemplate.count(new Query(),OrderList.class,collectionName);
        if(pageNumber != 1) {
        	
    		query.with(Sort.by(Sort.DEFAULT_DIRECTION.DESC,"date"));
    		int number = (pageNumber-1)*pageSize;
    		query.limit(number);
    		List<OrderList> temp = mongoTemplate.find(query, OrderList.class,collectionName);
    		OrderList order = temp.get(temp.size()-1);
    		String date = order.getDate();
    		query.addCriteria(Criteria.where("date").lt(date));
    		query.limit(pageSize);
        }else {
        	query.with(Sort.by(Sort.DEFAULT_DIRECTION.DESC,"date"));
        	int number = (pageNumber-1)*pageSize;
    		query.limit(number);
        }
		
        List<OrderList> orderList = mongoTemplate.find(query, OrderList.class,collectionName);
        map.put("count",totalCount);
        map.put("data", orderList);
        return map;
	}

	public long orders(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		Update update = Update.update("type", "2");
		UpdateResult    result = mongoTemplate.updateFirst(query, update, OrderList.class);
		return result.getModifiedCount();
	}
}
