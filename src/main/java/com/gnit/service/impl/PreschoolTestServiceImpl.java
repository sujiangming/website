package com.gnit.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gnit.dao.impl.PreschoolTestDaoImpl;
import com.gnit.pojo.PreschoolTest;
import com.gnit.service.PreschoolTestService;
import com.gnit.utils.PageList;

@Service
@Transactional
public class PreschoolTestServiceImpl implements PreschoolTestService {

	@Resource
	PreschoolTestDaoImpl preschoolTestDaoImpl;

	
	public PageList<PreschoolTest> selectList(Map<String, Object> parameter) {
		return preschoolTestDaoImpl.selectList(parameter);
	}

	
	public void delete(String id) {
		preschoolTestDaoImpl.delete(id);
	}

	
	public PreschoolTest findObjectById(String id) {
		return preschoolTestDaoImpl.findObject("from " + PreschoolTest.class.getName() + " where id='" + id + "'");
	}

	
	public void saveOrUpdate(PreschoolTest preschoolTest) {
		preschoolTestDaoImpl.saveOrUpdate(preschoolTest);
	}

	
	public PreschoolTest findObjectByPhoneNum(String phoneNumber) {
		return preschoolTestDaoImpl.getObjectByPhoneNum(phoneNumber);
	}

}
