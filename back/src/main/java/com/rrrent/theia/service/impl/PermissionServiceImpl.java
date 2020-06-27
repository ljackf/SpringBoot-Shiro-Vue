package com.rrrent.theia.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rrrent.theia.dao.PermissionDao;
import com.rrrent.theia.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author: jack
 * @date: 2020/06/18
 */
@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;

	/**
	 * 查询某用户的 角色  菜单列表   权限列表
	 */
	@Override
	public JSONObject getUserPermission(String mobile) {
		JSONObject userPermission = getUserPermissionFromDB(mobile);
		return userPermission;
	}

	/**
	 * 从数据库查询用户权限信息
	 */
	private JSONObject getUserPermissionFromDB(String mobile) {
		JSONObject userPermission = permissionDao.getUserPermission(mobile);
		//管理员角色ID为1
		int adminRoleId = 1;
		//如果是管理员
		String roleIdKey = "roleId";
		if (adminRoleId == userPermission.getIntValue(roleIdKey)) {
			//查询所有菜单  所有权限
			Set<String> menuList = permissionDao.getAllMenu();
			Set<String> permissionList = permissionDao.getAllPermission();
			userPermission.put("menuList", menuList);
			userPermission.put("permissionList", permissionList);
		}
		return userPermission;
	}
}
