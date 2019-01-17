package com.gnit.service;

import java.util.Map;

import com.gnit.pojo.PreschoolTest;
import com.gnit.utils.PageList;

public interface PreschoolTestService {
	PageList<PreschoolTest> selectList(Map<String, Object> parameter);
	void saveOrUpdate(PreschoolTest preschoolTest);
	void delete(String id);
	PreschoolTest findObjectById(String id);
	PreschoolTest findObjectByPhoneNum(String phoneNumber);
}
