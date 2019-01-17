package com.gnit.service;

import java.util.Map;

import com.gnit.pojo.FreeLearn;
import com.gnit.utils.PageList;

public interface FreeLearnService {
	PageList<FreeLearn> selectList(Map<String, Object> parameter);
	void saveOrUpdate(FreeLearn freeLearn);
	void delete(String id);
	FreeLearn findObjectById(String id);
	FreeLearn findObjectByPhoneNum(String phoneNumber);
}
