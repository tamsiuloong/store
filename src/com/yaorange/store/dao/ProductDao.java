package com.yaorange.store.dao;

import java.util.List;
import java.util.Map;

import com.yaorange.store.entity.Product;

public interface ProductDao {

	List<Product> findHotList();

	List<Product> findListByCid(Map<String, Object> paramMap);
	/**
	 * 获取该类商品总数量
	 * @param paramMap
	 * @return
	 */
	Integer getTotalCountByCid(Map<String, Object> paramMap);

	Product findById(String pid);

	void update(Product product);

}
