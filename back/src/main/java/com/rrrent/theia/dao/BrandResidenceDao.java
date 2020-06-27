package com.rrrent.theia.dao;

import com.alibaba.fastjson.JSONObject;
import com.rrrent.theia.vo.BrandResidenceVo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author : jack
 * @date : 2020/6/21
 * @Description:
 */
public interface BrandResidenceDao {
    List<BrandResidenceVo> findAllByCode(@RequestParam("code") String code);
}
