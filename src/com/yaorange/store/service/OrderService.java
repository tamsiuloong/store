package com.yaorange.store.service;

import java.util.List;

import com.yaorange.store.entity.Order;
import com.yaorange.store.entity.OrderItem;
import com.yaorange.store.entity.Page;

public interface OrderService {

	void save(Order order, List<OrderItem> orderItemList);

	/**
	 * �����û�id��ѯ�����б�
	 * @param uid
	 * @param currPage 
	 * @return
	 */
	Page getMyOrderList(String uid, String currPage);

	/**
	 * ���ݶ�������޸Ķ���״̬
	 * @param oid
	 * @param state 1:��ȷ��  2:��֧��
	 */
	void updateState(String oid, int state);

}
