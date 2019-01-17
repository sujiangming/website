package com.gnit.service;

import java.util.Map;

import com.gnit.pojo.OrderSignUp;
import com.gnit.utils.PageList;

public interface OrderSignUpService {
	PageList<OrderSignUp> selectList(Map<String, Object> parameter);
	void saveOrUpdate(OrderSignUp orderSignUp);
	void delete(String id);
	OrderSignUp findObjectById(String id);
	OrderSignUp findObjectByPhoneNum(String phoneNumber);
}
