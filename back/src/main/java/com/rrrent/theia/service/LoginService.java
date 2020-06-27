package com.rrrent.theia.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: jack
 * @description: 登录Service
 * @date: 2020/06/18
 */
public interface LoginService {
	/**
	 * 登录表单提交
	 */
	JSONObject authLogin(JSONObject jsonObject);

	/**
	 * 根据用户名和密码查询对应的用户
	 *
	 * @param mobile 用户名
	 * @param password 密码
	 */
	JSONObject getUser(String mobile, String password);

	/**
	 * 查询当前登录用户的权限等信息
	 */
	JSONObject getInfo();

	/**
	 * 退出登录
	 */
	JSONObject logout();
}
