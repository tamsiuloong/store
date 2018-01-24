package com.yaorange.store.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Integer cPage = 1;//Ĭ�ϵ�һҳ
		if(currPage!=null)
		{
			cPage = Integer.valueOf(currPage);
		}
		
		Page result = new Page();
		result.setCurrPage(cPage);
		
		Map<String, Object> paramMap= new HashMap<>();
		paramMap.put("cid", cid);
		paramMap.put("beginRow", result.getBeginRow());
		paramMap.put("pageSize", result.getPageSize());
		paramMap.put("pflag", 0);
		
		Integer totalCount = dao.getTotalCountByCid(paramMap);
		result.setTotalCount(totalCount);
		
		
		
		List<Product> list = dao.findListByCid(paramMap);
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

	@Override
	public void update(Product product) {
		
		SqlSession sqlSession = MybatisUtils.openSession();
		ProductDao dao = sqlSession.getMapper(ProductDao.class);
		
		dao.update(product);
		sqlSession.commit();
		sqlSession.close();
	}
	
	
	
	
	

}
