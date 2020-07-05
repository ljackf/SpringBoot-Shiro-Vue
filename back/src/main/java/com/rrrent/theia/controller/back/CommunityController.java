package com.rrrent.theia.controller.back;

import com.alibaba.fastjson.JSONObject;
import com.rrrent.theia.service.CommunityService;
import com.rrrent.theia.service.CommunityService;
import com.rrrent.theia.util.CommonUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: jack
 * @description: 社区相关Controller
 * @date: 2020/06/18
 */
@RestController
@RequestMapping("/community")
public class CommunityController {

	@Autowired
	private CommunityService communityService;

	/**
	 * 查询房源列表
	 */
	@RequiresPermissions("community:list")
	@GetMapping("/listCommunity")
	public JSONObject listCommunity(HttpServletRequest request) {
		return communityService.listCommunity(CommonUtil.request2Json(request));
	}

	/**
	 * 查询所有房源
	 */
	@GetMapping("/all")
	public JSONObject all(HttpServletRequest request) {
		return communityService.listAllCommunity();
	}

	/**
	 * 新增社区
	 */
	@RequiresPermissions("community:add")
	@PostMapping("/addCommunity")
	public JSONObject addCommunity(@RequestBody JSONObject requestJson) {
		CommonUtil.hasAllRequired(requestJson, "name");
		return communityService.addCommunity(requestJson);
	}

	/**
	 * 修改社区
	 */
	@RequiresPermissions("community:update")
	@PostMapping("/updateCommunity")
	public JSONObject updateCommunity(@RequestBody JSONObject requestJson) {
		CommonUtil.hasAllRequired(requestJson, "id,content");
		return communityService.updateCommunity(requestJson);
	}
}
