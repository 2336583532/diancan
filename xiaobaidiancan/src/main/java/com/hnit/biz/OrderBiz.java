package com.hnit.biz;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.hnit.bean.OrderList;

public interface OrderBiz {
	public Map<String, Object> queryOrderList(int pageNumber,int pageSize);

	public long orders(String id);
}
