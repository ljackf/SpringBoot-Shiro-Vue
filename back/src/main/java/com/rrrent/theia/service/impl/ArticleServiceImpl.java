package com.rrrent.theia.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rrrent.theia.dao.ArticleDao;
import com.rrrent.theia.service.ArticleService;
import com.rrrent.theia.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: jack
 * @date: 2020/06/18
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDao articleDao;

	/**
	 * 新增房源
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject addArticle(JSONObject jsonObject) {
		articleDao.addArticle(jsonObject);
		return CommonUtil.successJson();
	}

	/**
	 * 房源列表
	 */
	@Override
	public JSONObject listArticle(JSONObject jsonObject) {
		CommonUtil.fillPageParam(jsonObject);
		int count = articleDao.countArticle(jsonObject);
		List<JSONObject> list = articleDao.listArticle(jsonObject);
		return CommonUtil.successPage(jsonObject, list, count);
	}

	/**
	 * 更新房源
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject updateArticle(JSONObject jsonObject) {
		articleDao.updateArticle(jsonObject);
		return CommonUtil.successJson();
	}
}
