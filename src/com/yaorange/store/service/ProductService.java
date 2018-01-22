package com.yaorange.store.service;

import java.util.List;

import com.yaorange.store.entity.Page;
import com.yaorange.store.entity.Product;

public interface ProductService {

	List<Product> findHotList();

	/**
	 * 根据商品类别获取商品列表
	 * @param cid
	 * @param currPage TODO
	 * @return
	 */
	Page findListByCid(String cid, String currPage);

	Product findById(String pid);

}
