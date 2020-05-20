package com.hnit.dao;

import com.hnit.bean.OrderManagertype;
import com.hnit.bean.OrderManagertypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderManagertypeMapper {
    int countByExample(OrderManagertypeExample example);

    int deleteByExample(OrderManagertypeExample example);

    int deleteByPrimaryKey(Integer typeid);

    int insert(OrderManagertype record);

    int insertSelective(OrderManagertype record);

    List<OrderManagertype> selectByExample(OrderManagertypeExample example);

    OrderManagertype selectByPrimaryKey(Integer typeid);

    int updateByExampleSelective(@Param("record") OrderManagertype record, @Param("example") OrderManagertypeExample example);

    int updateByExample(@Param("record") OrderManagertype record, @Param("example") OrderManagertypeExample example);

    int updateByPrimaryKeySelective(OrderManagertype record);

    int updateByPrimaryKey(OrderManagertype record);
}