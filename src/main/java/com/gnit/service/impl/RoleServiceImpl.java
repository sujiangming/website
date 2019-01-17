package com.gnit.service.impl;

import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gnit.dao.impl.RoleDaoImpl;
import com.gnit.pojo.Role;
import com.gnit.service.RoleService;
import com.gnit.utils.PageList;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Resource
	RoleDaoImpl roleDao;

	
	public PageList<Role> queryRolePageList(Map<String, Object> parameter) {
		return roleDao.queryRolePageList(parameter);
	}

	
	public Role getRoleById(String roleId) {
		return roleDao.getRoleById(roleId);
	}

	
	public void saveRole(Role role) {
		roleDao.saveRole(role);
	}

	
	public void deleteRole(String roleId) {
		roleDao.deleteRole(roleId);
	}

}
