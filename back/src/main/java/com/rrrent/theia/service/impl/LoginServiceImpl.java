package com.rrrent.theia.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rrrent.theia.dao.LoginDao;
import com.rrrent.theia.service.LoginService;
import com.rrrent.theia.service.PermissionService;
import com.rrrent.theia.util.CommonUtil;
import com.rrrent.theia.util.constants.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: jack
 * @description: 登录service实现类
 * @date: 2020/06/18
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;
	@Autowired
	private PermissionService permissionService;

	/**
	 * 登录表单提交
	 */
	@Override
	public JSONObject authLogin(JSONObject jsonObject) {
		String mobile = jsonObject.getString("mobile");
		String password = jsonObject.getString("password");
		JSONObject info = new JSONObject();
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(mobile, password);
		try {
			currentUser.login(token);
			info.put("result", "success");
		} catch (AuthenticationException e) {
			info.put("result", "fail");
		}
		return CommonUtil.successJson(info);
	}

	/**
	 * 根据用户名和密码查询对应的用户
	 */
	@Override
	public JSONObject getUser(String mobile, String password) {
		return loginDao.getUser(mobile, password);
	}

	/**
	 * 查询当前登录用户的权限等信息
	 */
	@Override
	public JSONObject getInfo() {
		//从session获取用户信息
		Session session = SecurityUtils.getSubject().getSession();
		JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
		String mobile = userInfo.getString("mobile");
		JSONObject info = new JSONObject();
		JSONObject userPermission = permissionService.getUserPermission(mobile);
		session.setAttribute(Constants.SESSION_USER_PERMISSION, userPermission);
		info.put("userPermission", userPermission);
		return CommonUtil.successJson(info);
	}

	/**
	 * 退出登录
	 */
	@Override
	public JSONObject logout() {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.logout();
		} catch (Exception e) {
		}
		return CommonUtil.successJson();
	}
}
