package com.gnit.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gnit.dao.impl.FreeLearnDaoImpl;
import com.gnit.pojo.FreeLearn;
import com.gnit.service.FreeLearnService;
import com.gnit.utils.PageList;

@Service
@Transactional
public class FreeLearnServiceImpl implements FreeLearnService {

	@Resource
	FreeLearnDaoImpl freeLearnDaoImpl;

	
	public PageList<FreeLearn> selectList(Map<String, Object> parameter) {
		return freeLearnDaoImpl.selectList(parameter);
	}

	
	public void delete(String id) {
		freeLearnDaoImpl.delete(id);
	}

	
	public FreeLearn findObjectById(String id) {
		return freeLearnDaoImpl.findObject("from " + FreeLearn.class.getName() + " where id='" + id + "'");
	}

	
	public void saveOrUpdate(FreeLearn freeLearn) {
		freeLearnDaoImpl.saveOrUpdate(freeLearn);
	}

	
	public FreeLearn findObjectByPhoneNum(String phoneNumber) {
		return freeLearnDaoImpl.getObjectByPhoneNum(phoneNumber);
	}

}
