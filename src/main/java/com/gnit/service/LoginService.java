package com.gnit.service;

import com.gnit.pojo.User;

public interface LoginService {
	public User login(String account,String pwd);
}
