package com.rrrent.theia.controller.front;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.rrrent.theia.bo.HouseResourceBo;
import com.rrrent.theia.bo.PageBo;
import com.rrrent.theia.config.jwt.JwtConfig;
import com.rrrent.theia.service.HouseResourceService;
import com.rrrent.theia.service.UserService;
import com.rrrent.theia.util.PageBean;
import com.rrrent.theia.util.ResponseInfo;
import com.rrrent.theia.vo.HouseResourceDetailVo;
import com.rrrent.theia.vo.HouseResourceVo;
import com.rrrent.theia.vo.TagVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private UserService userService;


    @ApiOperation(value = "房源列表")
    @GetMapping(value = "/list")
    public ResponseInfo<PageBean<HouseResourceVo>> list(HouseResourceBo houseResourceBo, PageBo pageBo){
        PageInfo<HouseResourceVo> houseResourceVoPage = houseResourceService.findListPage(houseResourceBo.getCode(),
                houseResourceBo.getSelected(),
                houseResourceBo.getRentStart(),
                houseResourceBo.getHouseLayout(),
                houseResourceBo.getRentEnd(),houseResourceBo.getRenovationType(),houseResourceBo.getPayMode(),
                houseResourceBo.getOrientation(),houseResourceBo.getArea(),
                houseResourceBo.getCommissionType(),houseResourceBo.getHouseTags(),houseResourceBo.getLift(),
                houseResourceBo.getKey(),pageBo);
        PageBean<HouseResourceVo> pageBean = new PageBean(houseResourceVoPage.getPageNum(),houseResourceVoPage.getPageSize(),
                houseResourceVoPage.getTotal(),houseResourceVoPage.getList());
        return ResponseInfo.success(pageBean);
    }

    @ApiOperation(value = "全部标签")
    @GetMapping(value = "/tags")
    public ResponseInfo<List<TagVo>> tags(){
       List<TagVo>tagVos = houseResourceService.findAllTags();
        return ResponseInfo.success(tagVos);
    }

    @ApiOperation(value = "房源详情")
    @GetMapping(value = "/detail")
    public ResponseInfo<HouseResourceDetailVo> detail(HttpServletRequest request,@RequestParam("id")Long id){
        JSONObject jsonObject = userService.getJsonByToken(request);
        Long userId= null;
        if(jsonObject != null){
            userId = jsonObject.getLong("id");
        }
        HouseResourceDetailVo houseResourceVoPage = houseResourceService.findDetailById(id,userId);
        return ResponseInfo.success(houseResourceVoPage);
    }

    @ApiOperation(value = "房源收藏")
    @PostMapping(value = "/collect/{id}")
    public ResponseInfo collect(HttpServletRequest request,@PathVariable("id")Long id){
        JSONObject jsonObject = userService.getJsonByToken(request);
        houseResourceService.collect(jsonObject.getLong("id"),id);
        return ResponseInfo.success();
    }

    @ApiOperation(value = "房源取消收藏")
    @PostMapping(value = "/cancelCollect/{id}")
    public ResponseInfo cancelCollect(HttpServletRequest request,@PathVariable("id")Long id){
        JSONObject jsonObject = userService.getJsonByToken(request);
        houseResourceService.cancelCollect(jsonObject.getLong("id"),id);
        return ResponseInfo.success();
    }

    @ApiOperation(value = "房源收藏列表")
    @GetMapping(value = "/collect/list")
    public ResponseInfo<PageBean<HouseResourceVo>> collectList(HttpServletRequest request,PageBo pageBo){

        JSONObject jsonObject = userService.getJsonByToken(request);
        PageInfo<HouseResourceVo> houseResourceVoPage = houseResourceService.findListByUserId(jsonObject.getLong("id"),
                pageBo);
        PageBean<HouseResourceVo> pageBean = new PageBean(houseResourceVoPage.getPageNum(),houseResourceVoPage.getPageSize(),
                houseResourceVoPage.getTotal(),houseResourceVoPage.getList());
        return ResponseInfo.success(pageBean);
    }
}
