package com.rrrent.theia.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author: jack
 * @description: 房源Dao层
 * @date: 2020/06/18
 */
public interface ArticleDao {
	/**
	 * 新增房源
	 */
	int addArticle(JSONObject jsonObject);

	/**
	 * 统计房源总数
	 */
	int countArticle(JSONObject jsonObject);

	/**
	 * 房源列表
	 */
	List<JSONObject> listArticle(JSONObject jsonObject);

	/**
	 * 更新房源
	 */
	int updateArticle(JSONObject jsonObject);
}
