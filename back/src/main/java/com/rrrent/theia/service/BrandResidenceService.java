package com.rrrent.theia.service;

import com.alibaba.fastjson.JSONObject;
import com.rrrent.theia.vo.BrandResidenceVo;

import java.util.List;

/**
 * @author : jack
 * @date : 2020/6/21
 * @Description:
 */
public interface BrandResidenceService {
    List<BrandResidenceVo> findAllByCode(String code);
}
