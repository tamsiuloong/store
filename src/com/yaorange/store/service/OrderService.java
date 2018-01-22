package com.yaorange.store.service;

import java.util.List;

import com.yaorange.store.entity.Order;
import com.yaorange.store.entity.OrderItem;
import com.yaorange.store.entity.Page;

public interface OrderService {

	void save(Order order, List<OrderItem> orderItemList);

	/**
	 * 根据用户id查询订单列表
	 * @param uid
	 * @param currPage 
	 * @return
	 */
	Page getMyOrderList(String uid, String currPage);

	/**
	 * 根据订单编号修改订单状态
	 * @param oid
	 * @param state 1:已确认  2:已支付
	 */
	void updateState(String oid, int state);

}
