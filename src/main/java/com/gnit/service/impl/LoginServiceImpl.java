package com.gnit.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gnit.dao.impl.LoginDaoImpl;
import com.gnit.pojo.User;
import com.gnit.service.LoginService;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Resource
	LoginDaoImpl loginDao;

	public User login(String account, String pwd) {
		return loginDao.login(account, pwd);
	}

}
