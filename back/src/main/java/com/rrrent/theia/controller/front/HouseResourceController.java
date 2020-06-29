package com.rrrent.theia.controller.front;

import com.github.pagehelper.Page;
import com.rrrent.theia.bo.HouseResourceBo;
import com.rrrent.theia.bo.PageBo;
import com.rrrent.theia.service.HouseResourceService;
import com.rrrent.theia.util.PageBean;
import com.rrrent.theia.util.ResponseInfo;
import com.rrrent.theia.vo.HouseResourceDetailVo;
import com.rrrent.theia.vo.HouseResourceVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : jack
 * @date : 2020/6/21
 * @Description:
 */
@Api(description = "房源相关接口")
@RestController
@RequestMapping("/front/houseResource")
public class HouseResourceController {

    @Autowired
    private HouseResourceService houseResourceService;

    @ApiOperation(value = "房源列表")
    @GetMapping(value = "/list")
    public ResponseInfo<PageBean<HouseResourceVo>> list(HouseResourceBo houseResourceBo, PageBo pageBo){
        Page<HouseResourceVo> houseResourceVoPage = houseResourceService.findListPage(houseResourceBo.getCode(),
                houseResourceBo.getSelected(),
                houseResourceBo.getRentStart(),
                houseResourceBo.getRentEnd(),houseResourceBo.getRenovationType(),houseResourceBo.getPayMode(),
                houseResourceBo.getOrientation(),houseResourceBo.getArea(),
                houseResourceBo.getCommissionType(),houseResourceBo.getHouseTags(),houseResourceBo.getLift(),
                houseResourceBo.getKey(),pageBo);
        PageBean<HouseResourceVo> pageBean = new PageBean(houseResourceVoPage.getPageNum(),houseResourceVoPage.getPageSize(),
                houseResourceVoPage.getTotal(),houseResourceVoPage.getResult());
        return ResponseInfo.success(pageBean);
    }

    @ApiOperation(value = "房源详情")
    @GetMapping(value = "/detail")
    public ResponseInfo<HouseResourceDetailVo> detail(@RequestParam("id")Long id){
        HouseResourceDetailVo houseResourceVoPage = houseResourceService.findDetailById(id);
        return ResponseInfo.success(houseResourceVoPage);
    }

    @ApiOperation(value = "房源收藏")
    @PostMapping(value = "/collect/{id}")
    public ResponseInfo collect(@PathVariable("id")Long id){

        return ResponseInfo.success();
    }

    @ApiOperation(value = "房源取消收藏")
    @PostMapping(value = "/cancelCollect/{id}")
    public ResponseInfo cancelCollect(@PathVariable("id")Long id){

        return ResponseInfo.success();
    }

    @ApiOperation(value = "房源收藏列表")
    @GetMapping(value = "/collect/list")
    public ResponseInfo<List<HouseResourceVo>> collectList(){
        List<HouseResourceVo> houseResourceVoPage = houseResourceService.findListByUserId();
        return ResponseInfo.success(houseResourceVoPage);
    }
}
