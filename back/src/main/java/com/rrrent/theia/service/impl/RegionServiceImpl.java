package com.rrrent.theia.service.impl;

import com.rrrent.theia.dao.RegionDao;
import com.rrrent.theia.dao.RegionStreetDao;
import com.rrrent.theia.service.RegionService;
import com.rrrent.theia.vo.RegionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : jack
 * @date : 2020/6/29
 * @Description:
 */
@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionDao regionDao;
    @Autowired
    private RegionStreetDao regionStreetDao;

    @Override
    public List<RegionVo> provinceCityAll() {
        List<RegionVo>regionVos = regionDao.provinceCityAll();
        List<RegionVo>provinces = new ArrayList<>();
        Map<Integer,List<RegionVo>> regionVoHashMap= new HashMap<>();
        regionVos.forEach(regionVo -> {
            if(regionVo.getCode()%10000==0){
                provinces.add(regionVo);
            }else {
                if(regionVoHashMap.get((regionVo.getCode()/10000)*10000)==null){
                    List<RegionVo>cities = new ArrayList<>();
                    cities.add(regionVo);
                    regionVoHashMap.put((regionVo.getCode()/10000)*10000,cities);
                }else {
                    regionVoHashMap.get((regionVo.getCode()/10000)*10000).add(regionVo);
                }
            }
        });
        provinces.forEach(province -> {
            List<RegionVo>citys = regionVoHashMap.get(province.getCode());
            province.setChildren(citys);
        });
        return provinces;
    }

    @Override
    public List<RegionVo> regionStreetAll(Integer regionCode) {
        List<RegionVo>regionVos = regionDao.regionStreetAll(regionCode);
        regionVos.forEach(regionVo -> {
            Integer code = regionVo.getCode();
            List<RegionVo>children = regionStreetDao.findByCode(code);
            regionVo.setChildren(children);
        });
        return regionVos;
    }
}
