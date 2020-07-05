package com.rrrent.theia.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rrrent.theia.dao.CommunityDao;
import com.rrrent.theia.service.CommunityService;
import com.rrrent.theia.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : jack
 * @date : 2020/6/27
 * @Description:
 */
@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityDao communityDao;

    @Override
    public JSONObject listCommunity(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        List<JSONObject> jsonObjectList = communityDao.findAllByPage(jsonObject);
        Integer c = communityDao.findAllCount();
        return CommonUtil.successPage(jsonObject, jsonObjectList, c.intValue());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject addCommunity(JSONObject requestJson) {
        JSONArray jsonArray = requestJson.getJSONArray("selectedOptions");
        requestJson.put("region",jsonArray.get(2).toString());
        communityDao.addCommunity(requestJson);
        return CommonUtil.successJson();
    }

    @Override
    public JSONObject updateCommunity(JSONObject requestJson) {
        return null;
    }

    @Override
    public JSONObject listAllCommunity() {
        return CommonUtil.successJson(communityDao.listAllCommunity());
    }
}
