package com.gnit.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gnit.dao.impl.OrderSignUpDaoImpl;
import com.gnit.pojo.OrderSignUp;
import com.gnit.service.OrderSignUpService;
import com.gnit.utils.PageList;

@Service
@Transactional
public class OrderSignUpServiceImpl implements OrderSignUpService {

	@Resource
	OrderSignUpDaoImpl orderSignUpDaoImpl;

	
	public PageList<OrderSignUp> selectList(Map<String, Object> parameter) {
		return orderSignUpDaoImpl.selectList(parameter);
	}

	
	public void delete(String id) {
		orderSignUpDaoImpl.delete(id);
	}

	
	public OrderSignUp findObjectById(String id) {
		return orderSignUpDaoImpl.findObject("from " + OrderSignUp.class.getName() + " where id='" + id + "'");
	}

	
	public void saveOrUpdate(OrderSignUp orderSignUp) {
		orderSignUpDaoImpl.saveOrUpdate(orderSignUp);
	}

	
	public OrderSignUp findObjectByPhoneNum(String phoneNumber) {
		return orderSignUpDaoImpl.getObjectByPhoneNum(phoneNumber);
	}

}
