package com.yaorange.store.dao;

import java.util.List;

import com.yaorange.store.entity.Order;
import com.yaorange.store.entity.OrderItem;

public interface OrderDao {

	void save(Order order);

	void saveOrderItem(OrderItem orderItem);

	Integer getTotalCount(String uid);

	List<Order> getOrderListByUid(String uid, Integer beginRow, Integer pageSize);

	void updateState(String oid, int state);

}
