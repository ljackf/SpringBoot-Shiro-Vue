package com.rrrent.theia.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

/**
 * @author: jack
 * @description: 登录相关dao
 * @date: 2020/06/18
 */
public interface LoginDao {
	/**
	 * 根据用户名和密码查询对应的用户
	 */
	JSONObject getUser(@Param("mobile") String mobile, @Param("password") String password);
}
