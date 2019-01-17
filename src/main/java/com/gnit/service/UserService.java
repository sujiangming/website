package com.gnit.service;

import java.util.Map;

import com.gnit.pojo.User;
import com.gnit.utils.PageList;

public interface UserService {
	PageList<User> selectList(Map<String, Object> parameter);
	User getUserById(String id);
	void saveOrUpdate(User user);
	void delete(String id);
	void updateNewPwd(String newpwd, String mobel);
	User getUserByPhone(String customerMobile);
	void reset(String id);
}
