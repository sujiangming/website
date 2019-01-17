package com.gnit.dao.impl;
import org.springframework.stereotype.Repository;
import com.gnit.dao.base.BaseDaoImpl;
import com.gnit.pojo.User;

@Repository
public class CommUserDao extends BaseDaoImpl{

	public User getUserByMobileAndPwd(String account, String pwd) {
		String hql = "from " + User.class.getName() + " where userMobile='" + account + "' and userPwd='"
				+ pwd + "'";
		return this.findObject(hql);
	}

	public User getUserById(String id) {
		String hql = "from " + User.class.getName() + " where userMobile='" + id + "'";
		return this.findObject(hql);
	}

}
