package com.gnit.service;

import java.util.Map;

import com.gnit.pojo.Role;
import com.gnit.pojo.User;
import com.gnit.utils.PageList;

public interface RoleMemberService {
	
	PageList<User> queryRolePageList(Map<String, Object> parameter);

	Role getRoleById(String roleId);

	void saveRole(User role);

	void deleteRole(String roleId);

	int updateRoleMember(User oldAdmin);
}
