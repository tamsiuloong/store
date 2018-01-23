package com.yaorange.store.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yaorange.store.dao.ProductDao;
import com.yaorange.store.entity.Page;
import com.yaorange.store.entity.Product;
import com.yaorange.store.service.ProductService;
import com.yaorange.store.utils.MybatisUtils;

public class ProductServiceImpl implements ProductService {

	@Override
	public List<Product> findHotList() {
		SqlSession sqlSession = MybatisUtils.openSession();
		ProductDao dao = sqlSession.getMapper(ProductDao.class);
		List<Product> result = dao.findHotList();
		sqlSession.close();
		return result;
	}

	@Override
	public Page findListByCid(String cid, String currPage) {
		SqlSession sqlSession = MybatisUtils.openSession();
		ProductDao dao = sqlSession.getMapper(ProductDao.class);

		Integer totalCount = dao.getTotalCountByCid(cid);
		Page result = new Page(currPage,totalCount);


		List<Product> list = dao.findListByCid(cid,result.getBeginRow(),result.getPageSize());
		result.setList(list);
		
		sqlSession.close();
		return result;
	}

	@Override
	public Product findById(String pid) {
		SqlSession sqlSession = MybatisUtils.openSession();
		ProductDao dao = sqlSession.getMapper(ProductDao.class);
		
		Product result = dao.findById(pid);
		sqlSession.close();
		return result;
	}
	
	
	
	
	

}
