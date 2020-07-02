package com.rrrent.theia.controller.front;

import com.rrrent.theia.service.RegionService;
import com.rrrent.theia.util.ResponseInfo;
import com.rrrent.theia.vo.HouseResourceDetailVo;
import com.rrrent.theia.vo.RegionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : jack
 * @date : 2020/6/29
 * @Description:
 */
@Api(description = "省市区相关接口")
@RestController
@RequestMapping("/front/region")
public class RegionContoller {

    @Autowired
    private RegionService regionService;

    @ApiOperation(value = "省市")
    @GetMapping(value = "/province/city")
    public ResponseInfo<List<RegionVo>> provinceCityAll(){
        List<RegionVo> regionVos = regionService.provinceCityAll();
        return ResponseInfo.success(regionVos);
    }

    @ApiOperation(value = "区/街道")
    @GetMapping(value = "/region/street")
    public ResponseInfo<List<RegionVo>> regionStreetAll(@RequestParam("cityCode")Integer cityCode){
        List<RegionVo> regionVos = regionService.regionStreetAll(cityCode);
        return ResponseInfo.success(regionVos);
    }
}
