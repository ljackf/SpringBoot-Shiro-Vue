package com.rrrent.theia.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rrrent.theia.dao.CommunityDao;
import com.rrrent.theia.service.CommunityService;
import com.rrrent.theia.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        return CommonUtil.successPage(jsonObject, null, 0);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject addCommunity(JSONObject requestJson) {
        communityDao.addCommunity(requestJson);
        return CommonUtil.successJson();
    }

    @Override
    public JSONObject updateCommunity(JSONObject requestJson) {
        return null;
    }
}
