package com.rrrent.theia.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rrrent.theia.dao.BrandResidenceDao;
import com.rrrent.theia.service.BrandResidenceService;
import com.rrrent.theia.vo.BrandResidenceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : jack
 * @date : 2020/6/21
 * @Description:
 */
@Service
public class BrandResidenceServiceImpl implements BrandResidenceService {

    @Autowired
    private BrandResidenceDao brandResidenceDao;

    @Override
    public List<BrandResidenceVo> findAllByCode(String code) {
        return brandResidenceDao.findAllByCode(code);
    }
}
