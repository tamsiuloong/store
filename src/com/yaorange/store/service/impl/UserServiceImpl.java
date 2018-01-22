package com.yaorange.store.service.impl;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;

import com.yaorange.store.dao.UserDao;
import com.yaorange.store.entity.User;
import com.yaorange.store.service.UserService;
import com.yaorange.store.utils.MailUtils;
import com.yaorange.store.utils.MybatisUtils;

public class UserServiceImpl implements UserService {

	private UserDao userDao;
	@Override
	public Boolean checkUsername(String username) {
		SqlSession sqlSession = MybatisUtils.openSession();
		
		userDao = sqlSession.getMapper(UserDao.class);
		User user = userDao.findByUsername(username);
		
		sqlSession.close();
		return user==null?false:true;
	}
	@Override
	public boolean register(User user) {
		boolean result = false;
		
		//生成激活码
		user.setCode(UUID.randomUUID().toString());
		//生成id
		user.setUid(UUID.randomUUID().toString().replaceAll("-", ""));
		//默认为未激活
		user.setState(0);
		//保存数据
		SqlSession sqlSession = MybatisUtils.openSession();
		
		userDao = sqlSession.getMapper(UserDao.class);
		int count = userDao.save(user);
		if(count > 0)
		{
			result= true;
		}
		//发送邮件
		MailUtils.sendMail(user.getEmail(), user.getCode());
		
		sqlSession.commit();
		sqlSession.close();
		
		return result;
	}
	@Override
	public boolean active(String code) {
		boolean result = false;
		
		SqlSession sqlSession = MybatisUtils.openSession();
		
		userDao = sqlSession.getMapper(UserDao.class);
		
		int count = userDao.updateStateByCode(code);
		if(count>0)
		{
			result = true;
		}
		
		sqlSession.commit();
		sqlSession.close();
		return result;
	}
	@Override
	public User login(String username, String password) {
		SqlSession sqlSession = MybatisUtils.openSession();
		userDao = sqlSession.getMapper(UserDao.class);
		User result = userDao.findByUP(username,password);
		sqlSession.close();
		return result;
	}
	
	
	

}
