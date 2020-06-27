package com.rrrent.theia.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: jack
 * @date: 2020/06/18
 */
public interface ArticleService {
	/**
	 * 新增房源
	 */
	JSONObject addArticle(JSONObject jsonObject);

	/**
	 * 房源列表
	 */
	JSONObject listArticle(JSONObject jsonObject);

	/**
	 * 更新房源
	 */
	JSONObject updateArticle(JSONObject jsonObject);
}
