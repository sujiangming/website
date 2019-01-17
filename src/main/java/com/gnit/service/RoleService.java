package com.gnit.service;

import java.util.Map;

import com.gnit.pojo.Role;
import com.gnit.utils.PageList;

public interface RoleService {
	
	PageList<Role> queryRolePageList(Map<String, Object> parameter);

	Role getRoleById(String roleId);

	void saveRole(Role role);

	void deleteRole(String roleId);
}
