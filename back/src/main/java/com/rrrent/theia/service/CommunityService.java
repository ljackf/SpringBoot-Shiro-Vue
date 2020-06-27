package com.rrrent.theia.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author : jack
 * @date : 2020/6/27
 * @Description:
 */
public interface CommunityService {
    JSONObject listCommunity(JSONObject jsonObject);

    JSONObject addCommunity(JSONObject requestJson);

    JSONObject updateCommunity(JSONObject requestJson);
}
