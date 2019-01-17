package com.gnit.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gnit.dao.impl.UserDaoImpl;
import com.gnit.pojo.User;
import com.gnit.service.UserService;
import com.gnit.utils.PageList;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	UserDaoImpl userDaoImpl;
	
	
	public PageList<User> selectList(Map<String, Object> parameter) {
		return userDaoImpl.selectList(parameter);
	}

	
	public User getUserById(String id) {
		return userDaoImpl.getUserById(id);
	}

	
	public void saveOrUpdate(User user) {
		userDaoImpl.saveOrUpdate(user);
	}

	
	public void delete(String id) {
		userDaoImpl.delete(id);
	}

	
	public void updateNewPwd(String newpwd, String mobel) {
		userDaoImpl.updateNewPwd(newpwd, mobel);
	}
	
	public User getUserByPhone(String customerMobile) {
		return userDaoImpl.getUserByPhone(customerMobile);
	}

	public void reset(String id) {
		userDaoImpl.reset(id);
	}

}
