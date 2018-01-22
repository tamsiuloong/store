package com.yaorange.store.service;

import com.yaorange.store.entity.User;

public interface UserService {

	Boolean checkUsername(String username);

	boolean register(User user);

	boolean active(String code);

	User login(String username, String password);

}
