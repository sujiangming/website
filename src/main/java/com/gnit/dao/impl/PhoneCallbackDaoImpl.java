package com.gnit.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.gnit.dao.base.BaseDaoImpl;
import com.gnit.pojo.OrderSignUp;
import com.gnit.pojo.PhoneCallback;
import com.gnit.utils.PageList;

@Repository
public class PhoneCallbackDaoImpl extends BaseDaoImpl {
	
	/**
	 * 分页查询免费试听列表
	 */
	public PageList<PhoneCallback> selectList(Map<String, Object> parameter) {
		String hql = "from " + PhoneCallback.class.getName();
		String whereCase = " a where 1=1 ";
		Map<String, Object> map = new HashMap<String, Object>();
		int currentPage = Integer.parseInt(parameter.get("currentPage").toString());
		int showCount = Integer.parseInt(parameter.get("showCount").toString());
		String search = parameter.get("search") == null ? "" : parameter.get("search").toString();
		if (!search.equals("")) {
			whereCase += " and a.phoneNumber like:search ";
			map.put("search", "%" + search + "%");
		}
		//最后的hql
		hql += whereCase;
		return this.findPageList(hql, currentPage, showCount, map);
	}
	
	/**
	 * 保存或更新
	 * @param freeLearn
	 */
	public void saveOrUpdate(PhoneCallback freeLearn) {
		this.saveOrUpdateObject(freeLearn);
	}
	
	/**
	 * 根据电话号码查询
	 * 
	 * @param id
	 * @return
	 */
	public PhoneCallback getObjectByPhoneNum(String phoneNumber) {
		return this.findObject("from " + PhoneCallback.class.getName() + " where phoneNumber='" + phoneNumber + "'");
	}
	
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public PhoneCallback getRoleById(String id) {
		return this.findObject("from "+PhoneCallback.class.getName()+" where id='" + id + "'");
	}
	
	/**
	 * 删除，根据id
	 * 
	 * @param news
	 */
	public void delete(String id) {
		String[] roleIds = id.split(",");
		Session session = this.getSession();
		for (int i = 0; i < roleIds.length; i++) {
			PhoneCallback role = this.getRoleById(roleIds[i]);
			if (role != null) {
				session.delete(role);
			}
		}
	}
}
