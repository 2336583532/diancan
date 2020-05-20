package com.hnit.dao;

import com.hnit.bean.OrderFood;
import com.hnit.bean.OrderFoodExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderFoodMapper {
    int countByExample(OrderFoodExample example);

    int deleteByExample(OrderFoodExample example);

    int deleteByPrimaryKey(Integer fid);

    int insert(OrderFood record);

    int insertSelective(OrderFood record);

    List<OrderFood> selectByExample(OrderFoodExample example);

    OrderFood selectByPrimaryKey(Integer fid);

    int updateByExampleSelective(@Param("record") OrderFood record, @Param("example") OrderFoodExample example);

    int updateByExample(@Param("record") OrderFood record, @Param("example") OrderFoodExample example);

    int updateByPrimaryKeySelective(OrderFood record);

    int updateByPrimaryKey(OrderFood record);
}