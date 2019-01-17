package com.gnit.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.gnit.dao.base.BaseDaoImpl;
import com.gnit.pojo.User;
import com.gnit.utils.PageList;

@Repository
public class UserDaoImpl extends BaseDaoImpl {

	public PageList<User> selectList(Map<String, Object> parameter) {
		String hql = "from " + User.class.getName();
		String whereCase = " a where 1=1 "; // and a.isManager = 0
		Map<String, Object> map = new HashMap<String, Object>();
		int currentPage = Integer.parseInt(parameter.get("currentPage").toString());
		int showCount = Integer.parseInt(parameter.get("showCount").toString());
		String search = parameter.get("search") == null ? "" : parameter.get("search").toString();
		if (!search.equals("")) {
			whereCase += " and a.userName like:username " + " or a.userMobile like:mobile ";
			map.put("username", "%" + search + "%");
			map.put("mobile", "%" + search + "%");
		}
		// 最后的hql
		hql += whereCase;
		return this.findPageList(hql, currentPage, showCount, map);
	}

	public User getUserById(String id) {
		return this.findObject("from " + User.class.getName() + " where id='" + id + "'");
	}

	/**
	 * 保存用户
	 */
	public void saveOrUpdate(User user) {
		this.saveOrUpdateObject(user);
	}

	/**
	 * 删除用户
	 */
	public void delete(String id) {
		String[] ids = id.split(",");
		Session session = this.getSession();
		for (int i = 0; i < ids.length; i++) {
			User user = this.getUserById(ids[i]);
			if (user != null) {
				session.delete(user);
			}
		}
	}

	/**
	 * 修改用户密码
	 */
	public void updateNewPwd(String newpwd, String mobile) {
		Session session = this.getSession();
		String sql = "update t_gnit_user set userPwd=" + newpwd + " where userMobile='" + mobile + "'";
		Query query = session.createSQLQuery(sql);
		query.executeUpdate();
		session.flush();
		session.close();
	}

	/**
	 * 重置用户密码为123456
	 */
	public void reset(String id) {
		Session session = this.getSession();
		String sql = "update t_gnit_user set userPwd='123456' where id='" + id + "'";
		Query query = session.createSQLQuery(sql);
		query.executeUpdate();
		session.flush();
		session.close();
	}

	/**
	 * 根据手机号查询用户
	 */
	public User getUserByPhone(String customerMobile) {
		return this.findObject("from " + User.class.getName() + " where userMobile='" + customerMobile + "'");
	}
}
