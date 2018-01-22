package com.yaorange.store.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yaorange.store.dao.OrderDao;
import com.yaorange.store.entity.Order;
import com.yaorange.store.entity.OrderItem;
import com.yaorange.store.entity.Page;
import com.yaorange.store.service.OrderService;
import com.yaorange.store.utils.MybatisUtils;

public class OrderServiceImpl implements OrderService {

	@Override
	public void save(Order order, List<OrderItem> orderItemList) {
		SqlSession session = MybatisUtils.openSession();
		OrderDao orderDao = session.getMapper(OrderDao.class);
		orderDao.save(order);
		for (OrderItem orderItem : orderItemList) {
			orderDao.saveOrderItem(orderItem);
		}
		
		session.commit();
		session.close();

	}

	@Override
	public Page getMyOrderList(String uid,String currPage) {
		SqlSession session = MybatisUtils.openSession();
		OrderDao orderDao = session.getMapper(OrderDao.class);
		
		Integer totalCount = orderDao.getTotalCount(uid);
		Page page = new Page(currPage,totalCount);
		
		List<Order> orderList = orderDao.getOrderListByUid(uid,page.getBeginRow(),page.getPageSize());
		page.setList(orderList);
		
		session.close();
		return page;
	}

	@Override
	public void updateState(String oid, int state) {
		SqlSession session = MybatisUtils.openSession();
		OrderDao orderDao = session.getMapper(OrderDao.class);
		orderDao.updateState(oid,state);
		session.commit();
		session.close();
	}

}
