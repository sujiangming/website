package com.gnit.service;

import java.util.Map;

import com.gnit.pojo.PhoneCallback;
import com.gnit.utils.PageList;

public interface PhoneCallbackService {
	PageList<PhoneCallback> selectList(Map<String, Object> parameter);
	void saveOrUpdate(PhoneCallback phoneCallback);
	void delete(String id);
	PhoneCallback findObjectById(String id);
	PhoneCallback findObjectByPhoneNum(String phoneNumber);
}
