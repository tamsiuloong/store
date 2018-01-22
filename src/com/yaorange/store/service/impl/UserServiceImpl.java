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
		
		//���ɼ�����
		user.setCode(UUID.randomUUID().toString());
		//����id
		user.setUid(UUID.randomUUID().toString().replaceAll("-", ""));
		//Ĭ��Ϊδ����
		user.setState(0);
		//��������
		SqlSession sqlSession = MybatisUtils.openSession();
		
		userDao = sqlSession.getMapper(UserDao.class);
		int count = userDao.save(user);
		if(count > 0)
		{
			result= true;
		}
		//�����ʼ�
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
