package com.rrrent.theia.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: jack
 * @date: 2020/06/18
 */
public interface PermissionService {
	/**
	 * 查询某用户的 角色  菜单列表   权限列表
	 */
	JSONObject getUserPermission(String mobile);
}
