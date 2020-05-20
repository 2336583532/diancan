package com.hnit.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document("orderList")
public class OrderList implements Serializable {
    @Id
    private String id;
    private String username;
    private String phone;
    private String orderList;
    private String date;
    private Integer type;

    @Override
    public String toString() {
        return "OrderList{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", orderList='" + orderList + '\'' +
                ", date=" + date +
                ", type=" + type +
                '}';
    }
}
