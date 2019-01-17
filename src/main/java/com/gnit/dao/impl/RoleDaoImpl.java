package com.gnit.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.gnit.dao.base.BaseDaoImpl;
import com.gnit.pojo.Role;
import com.gnit.utils.PageList;

@Repository
public class RoleDaoImpl extends BaseDaoImpl {
	
	public PageList<Role> queryRolePageList(Map<String, Object> parameter) {
		String hql = " from " + Role.class.getName();
		String where = " where 1=1 ";
		String whereCase = " a " + where;
		Map<String, Object> map = new HashMap<String, Object>();
		int currentPage = Integer.parseInt(parameter.get("currentPage").toString());
		int showCount = Integer.parseInt(parameter.get("showCount").toString());
		String search = parameter.get("search") == null ? "" : parameter.get("search").toString();

		boolean isAdmin = (Boolean) parameter.get("isManager");
		String checkStationId = (String) parameter.get("checkStationId");

		if (!isAdmin) {
			whereCase += " and a.checkStationId = '" + checkStationId + "'";
		}

		if (!search.equals("")) {
			whereCase += " and a.roleName like:username ";
			map.put("username", "%" + search + "%");
		}
		hql += whereCase;
		return this.findPageList(hql, currentPage, showCount, map);
	}

	public Role getRoleById(String roleId) {
		return this.findObject("from Role where id='" + roleId + "'");
	}

	public void saveRole(Role role) {
		this.saveOrUpdateObject(role);
	}

	public void deleteRole(String roleId) {
		String[] roleIds = roleId.split(",");
		Session session = this.getSession();
		for (int i = 0; i < roleIds.length; i++) {
			Role role = this.getRoleById(roleIds[i]);
			if (role != null) {
				session.delete(role);
			}
		}
	}

}
