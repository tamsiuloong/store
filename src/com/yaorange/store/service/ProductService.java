package com.yaorange.store.service;

import java.util.List;

import com.yaorange.store.entity.Page;
import com.yaorange.store.entity.Product;

public interface ProductService {

	List<Product> findHotList();

	/**
	 * ������Ʒ����ȡ��Ʒ�б�
	 * @param cid
	 * @param currPage TODO
	 * @return
	 */
	Page findListByCid(String cid, String currPage);

	Product findById(String pid);

}
