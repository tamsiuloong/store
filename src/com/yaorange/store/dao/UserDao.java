package com.yaorange.store.dao;

import com.yaorange.store.entity.User;

public interface UserDao {

	User findByUsername(String username);

	int save(User user);

	int updateStateByCode(String code);

	User findByUP(String username, String password);

}
