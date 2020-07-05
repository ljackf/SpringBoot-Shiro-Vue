package com.rrrent.theia.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rrrent.theia.dto.HouseResourceDetailDto;
import com.rrrent.theia.bo.PageBo;
import com.rrrent.theia.dao.HouseResourceDao;
import com.rrrent.theia.service.HouseResourceService;
import com.rrrent.theia.util.CommonUtil;
import com.rrrent.theia.util.constants.Constants;
import com.rrrent.theia.util.model.*;
import com.rrrent.theia.vo.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : jack
 * @date : 2020/6/21
 * @Description:
 */
@Service
public class HouseResourceServiceImpl implements HouseResourceService {

    @Autowired
    private HouseResourceDao houseResourceDao;

    @Override
    public PageInfo<HouseResourceVo> findListPage(String code, Boolean selected, BigDecimal rentStart, HouseLayout houseLayout, BigDecimal rentEnd, RenovationType renovationType, PayMode payMode, Orientation orientation, Area area, CommissionType commissionType, List<String> houseTags, Boolean lift, Boolean key, PageBo page) {
        PageHelper.startPage(page.getPage(), page.getSize());
        List<HouseResourceVo>houseResourceVos = houseResourceDao.findListPage(code, selected, houseLayout,rentStart,
                rentEnd,
                renovationType, payMode, orientation, area, commissionType, houseTags, lift, key);
        houseResourceVos.forEach(houseResourceVo -> {
            List<TagVo>tagVos = houseResourceDao.findTagsByHouseId(houseResourceVo.getId());
            houseResourceVo.setTags(tagVos);
        });
        PageInfo<HouseResourceVo> pageInfo = new PageInfo<>(houseResourceVos);
        return pageInfo;
    }

    @Override
    public HouseResourceDetailVo findDetailById(Long id,Long userId) {
        HouseResourceDetailDto houseResourceDetailDto = houseResourceDao.findById(id);
        HouseResourceDetailVo houseResourceDetailVo = JSON.parseObject(JSON.toJSONString(houseResourceDetailDto),
                HouseResourceDetailVo.class);
        int c = houseResourceDao.findCountByUserIdHouseId(userId,houseResourceDetailDto.getId());
        List<TagVo>tagVos = houseResourceDao.findTagsByHouseId(id);
        houseResourceDetailVo.setTags(tagVos);
        LocationVo locationVo = new LocationVo();
        locationVo.setLatitude(houseResourceDetailDto.getLatitude());
        locationVo.setLongitude(houseResourceDetailDto.getLongitude());
        CommunityVo communityVo = JSON.parseObject(JSON.toJSONString(houseResourceDetailDto),
                CommunityVo.class);
        houseResourceDetailVo.setLocation(locationVo);
        houseResourceDetailVo.setCommunity(communityVo);
        houseResourceDetailVo.setCol(c>0?true:false);
        houseResourceDetailVo.setHouseImgs(JSON.parseArray(houseResourceDetailDto.getHouseImg(), String.class));
        List<HouseSetting>houseSettings = JSON.parseArray(houseResourceDetailDto.getSettings(), HouseSetting.class);
        houseResourceDetailVo.setSettings(houseSettings);
        houseResourceDetailVo.setUpdateTime(houseResourceDetailDto.getUpdateDate().getTime());
        return houseResourceDetailVo;
    }

    @Override
    public PageInfo<HouseResourceVo> findListByUserId(Long userId, PageBo pageBo) {
        PageHelper.startPage(pageBo.getPage(), pageBo.getSize());
        List<HouseResourceVo>houseResourceVos = houseResourceDao.findListPageByUserId(userId);
        houseResourceVos.forEach(houseResourceVo -> {
            List<TagVo>tagVos = houseResourceDao.findTagsByHouseId(houseResourceVo.getId());
            houseResourceVo.setTags(tagVos);
        });
        PageInfo<HouseResourceVo> pageInfo = new PageInfo<>(houseResourceVos);
        return pageInfo;
    }

    @Override
    public void collect(Long userId, Long id) {
        houseResourceDao.collect(userId,id);
    }

    @Override
    public void cancelCollect(Long userId, Long id) {
        houseResourceDao.cancelCollect(userId,id);
    }

    @Override
    public List<TagVo> findAllTags() {
        return houseResourceDao.findAllTags();
    }

    @Override
    public JSONObject addHouseResource(JSONObject requestJson) {
        requestJson.put("preImg1",requestJson.getJSONArray("preImg").getJSONObject(0).getString("url"));
        List<String>houseImgs=new ArrayList<>();
        requestJson.getJSONArray("houseImg").forEach(o->{
            JSONObject o1 = JSONObject.parseObject(JSON.toJSONString(o));
            houseImgs.add(o1.getString("url"));
        });
        requestJson.put("houseImgs",JSON.toJSONString(houseImgs));
        requestJson.put("settings",JSON.toJSONString(requestJson.getJSONArray("requestJson")));
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        Integer userId = userInfo.getInteger("id");
        requestJson.put("maintainer",userId);
        houseResourceDao.addHouseResource(requestJson);
        return CommonUtil.successJson();
    }

    @Override
    public JSONObject listHouseResource(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = houseResourceDao.countHouseResource(jsonObject);
        List<JSONObject> list = houseResourceDao.listHouseResource(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }
}
