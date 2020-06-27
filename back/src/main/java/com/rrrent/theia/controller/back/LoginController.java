package com.rrrent.theia.controller.back;

import com.alibaba.fastjson.JSONObject;
import com.rrrent.theia.service.LoginService;
import com.rrrent.theia.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: jack
 * @description: 登录相关Controller
 * @date: 2020/06/18
 */
@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService loginService;

	/**
	 * 登录
	 */
	@PostMapping("/auth")
	public JSONObject authLogin(@RequestBody JSONObject requestJson) {
		CommonUtil.hasAllRequired(requestJson, "mobile,password");
		return loginService.authLogin(requestJson);
	}

	/**
	 * 查询当前登录用户的信息
	 */
	@PostMapping("/getInfo")
	public JSONObject getInfo() {
		return loginService.getInfo();
	}

	/**
	 * 登出
	 */
	@PostMapping("/logout")
	public JSONObject logout() {
		return loginService.logout();
	}
}
