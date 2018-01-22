package com.yaorange.store.dao;

import java.util.List;

import com.yaorange.store.entity.Product;

public interface ProductDao {

	List<Product> findHotList();

	List<Product> findListByCid(String cid,Integer beginRow,Integer pageSize);
	/**
	 * 获取该类商品总数量
	 * @param cid
	 * @return
	 */
	Integer getTotalCountByCid(String cid);

	Product findById(String pid);

}
