package com.rrrent.theia.service;

import com.rrrent.theia.vo.RegionVo;

import java.util.List;

/**
 * @author : jack
 * @date : 2020/6/29
 * @Description:
 */
public interface RegionService {
    List<RegionVo> provinceCityAll();

    List<RegionVo> regionStreetAll(Integer regionCode);
}
