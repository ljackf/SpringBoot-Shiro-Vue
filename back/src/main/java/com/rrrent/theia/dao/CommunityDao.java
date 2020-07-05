package com.rrrent.theia.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author : jack
 * @date : 2020/6/27
 * @Description:
 */
public interface CommunityDao {
    void addCommunity(JSONObject requestJson);

    Integer findAllCount();

    List<JSONObject> findAllByPage(JSONObject jsonObject);

    List<JSONObject> listAllCommunity();
}
