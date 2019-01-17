package com.gnit.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gnit.dao.impl.PhoneCallbackDaoImpl;
import com.gnit.pojo.PhoneCallback;
import com.gnit.service.PhoneCallbackService;
import com.gnit.utils.PageList;

@Service
@Transactional
public class PhoneCallbackServiceImpl implements PhoneCallbackService {

	@Resource
	PhoneCallbackDaoImpl phoneCallbackDaoImpl;

	
	public PageList<PhoneCallback> selectList(Map<String, Object> parameter) {
		return phoneCallbackDaoImpl.selectList(parameter);
	}

	
	public void delete(String id) {
		phoneCallbackDaoImpl.delete(id);
	}

	
	public PhoneCallback findObjectById(String id) {
		return phoneCallbackDaoImpl.findObject("from " + PhoneCallback.class.getName() + " where id='" + id + "'");
	}

	
	public void saveOrUpdate(PhoneCallback phoneCallback) {
		phoneCallbackDaoImpl.saveOrUpdate(phoneCallback);
	}

	
	public PhoneCallback findObjectByPhoneNum(String phoneNumber) {
		return phoneCallbackDaoImpl.getObjectByPhoneNum(phoneNumber);
	}

}
