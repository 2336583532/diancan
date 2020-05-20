package com.hnit.dao;

import com.hnit.bean.OrderUser;
import com.hnit.bean.OrderUserExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OrderUserMapper {
    int countByExample(OrderUserExample example);

    int deleteByExample(OrderUserExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(OrderUser record);

    int insertSelective(OrderUser record);

    List<OrderUser> selectByExample(OrderUserExample example);

    OrderUser selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") OrderUser record, @Param("example") OrderUserExample example);

    int updateByExample(@Param("record") OrderUser record, @Param("example") OrderUserExample example);

    int updateByPrimaryKeySelective(OrderUser record);

    int updateByPrimaryKey(OrderUser record);
    
    //自定义的sql语句
    List<Map<String,Object >> selectManager();
    int updatePassword(@Param("phone")String phone,@Param("password")String password);
}