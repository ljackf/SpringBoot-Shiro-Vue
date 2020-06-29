package com.rrrent.theia.controller.front;

import com.alibaba.fastjson.JSONObject;
import com.rrrent.theia.service.BrandResidenceService;
import com.rrrent.theia.service.CarouselService;
import com.rrrent.theia.util.CommonUtil;
import com.rrrent.theia.util.Resp;
import com.rrrent.theia.util.ResponseInfo;
import com.rrrent.theia.vo.BrandResidenceVo;
import com.rrrent.theia.vo.FrontIndexVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : jack
 * @date : 2020/6/20
 * @Description: 首页
 */
@Api(description = "首页相关接口")
@RestController
@RequestMapping("/front")
public class FrontIndexController {

    @Autowired
    private CarouselService carouselService;
    @Autowired
    private BrandResidenceService brandResidenceService;

    @ApiOperation(value = "首页轮播")
    @GetMapping(value = "/index")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "cityCode",value = "城市code",required = true)
    })
    public ResponseInfo<FrontIndexVo> index(@RequestParam("cityCode")String code){
        List<String> carousels = carouselService.findAll();
        List<BrandResidenceVo> brandResidences = brandResidenceService.findAllByCode(code);
        FrontIndexVo frontIndexVo = new FrontIndexVo();
        carousels.add("https://cn.bing.com/th?id=OHR.ArganGoats_ZH-CN5346845518_UHD.jpg&rf=LaDigue_UHD.jpg&pid=hp&w=1920&h=1080&rs=1&c=4");
        carousels.add("https://cn.bing.com/th?id=OHR.FoggyCastle_ZH-CN6799694629_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp");
        BrandResidenceVo brandResidenceVo = new BrandResidenceVo();
        brandResidenceVo.setName("武汉中建公馆");
        brandResidenceVo.setImg("https://cn.bing.com/th?id=OHR.FoggyCastle_ZH-CN6799694629_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp");
        frontIndexVo.setCarousels(carousels);
        brandResidences.add(brandResidenceVo);
        frontIndexVo.setBrandResidences(brandResidences);
        return ResponseInfo.success(frontIndexVo);
    }

}
