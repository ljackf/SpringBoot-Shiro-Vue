package com.rrrent.theia.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.Set;

/**
 * @author: jack
 * @date: 2020/06/18
 */
public interface PermissionDao {
	/**
	 * 查询用户的角色 菜单 权限
	 */
	JSONObject getUserPermission(String mobile);

	/**
	 * 查询所有的菜单
	 */
	Set<String> getAllMenu();

	/**
	 * 查询所有的权限
	 */
	Set<String> getAllPermission();
}
