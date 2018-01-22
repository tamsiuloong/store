package com.yaorange.store.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yaorange.store.dao.CategoryDao;
import com.yaorange.store.dao.UserDao;
import com.yaorange.store.entity.Category;
import com.yaorange.store.service.CategoryService;
import com.yaorange.store.utils.MybatisUtils;

public class CategoryServiceImpl implements CategoryService {

	@Override
	public List<Category> findAll() {
		SqlSession sqlSession = MybatisUtils.openSession();

		CategoryDao dao = sqlSession.getMapper(CategoryDao.class);

		List<Category> result = dao.findAll();
		
		sqlSession.close();

		return result;
	}

}
