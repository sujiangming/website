package com.gnit.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.gnit.dao.base.BaseDaoImpl;
import com.gnit.pojo.User;

@Repository
public class LoginDaoImpl extends BaseDaoImpl {

	public User login(String account, String pwd) {
		Session session = sessionFactory.openSession();
		String hql = "from " + User.class.getName() + " where userMobile='" + account +  "' and userPwd='" + pwd + "'";
		Query query = session.createQuery(hql);		
		return (User) query.uniqueResult();
	}

}
