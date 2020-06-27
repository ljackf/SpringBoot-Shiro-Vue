package com.rrrent.theia.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rrrent.theia.dao.CarouselDao;
import com.rrrent.theia.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : jack
 * @date : 2020/6/20
 * @Description:
 */
@Service
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselDao carouselDao;

    @Override
    public List<String> findAll() {
        return carouselDao.findAll();
    }
}
