package com.rrrent.theia.controller.front;

import com.github.pagehelper.Page;
import com.rrrent.theia.service.HouseResourceService;
import com.rrrent.theia.util.PageBean;
import com.rrrent.theia.util.ResponseInfo;
import com.rrrent.theia.util.model.*;
import com.rrrent.theia.vo.HouseResourceDetailVo;
import com.rrrent.theia.vo.HouseResourceVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    @ApiImplicitParams({
            @ApiImplicitParam( name = "cityCode",value = "城市code"),
            @ApiImplicitParam( name = "selected",value = "是否是优选好房",dataType = "boolean"),
            @ApiImplicitParam( name = "area",value = "房屋面积：LESS50 少于50平米,F50T800 50-80平米,F80T200 80-200平米,F200T300 " +
                    "200-300平米, F300T500 300-500平米, MORE500 大于500平米"),
            @ApiImplicitParam( name = "rentSeg",value = "租金段：LESS800 少于800,F800T1500 800-1500,F1500T2000 1500-2000," +
                    "F2000T4000 2000-4000,F4000T5000 4000-5000,F5000T8000 5000-8000,MORE8000 8000以上"),
            @ApiImplicitParam( name = "rentStart",value = "租金大于多少"),
            @ApiImplicitParam( name = "rentEnd",value = "租金小于多少"),
            @ApiImplicitParam( name = "renovationType",value = "装修类型：HAUTECOUTURE 豪装 ,HARDCOVER 精装, MEDIUMDECORATION " +
                    "中装，PAPERBACK 简装，ROUGHCAST 毛坯"),
            @ApiImplicitParam( name = "orientation",value = "房屋朝向：EAST 东,SOUTH 南,WEST 西,NORTH 北,SOUTHEAST 东南," +
                    "NORTHEAST 东北, SOUTHWEST 西南,NORTHWEST 西北,NORTHSOUTHPENETRATION 南北通透"),
            @ApiImplicitParam( name = "commissionType",value = "佣金段：LESS30 0-30%,F30T50 30%-50%,F50T80 50%-80%," +
                    "F80T100 80%-100%,MORE100 大于100%"),
            @ApiImplicitParam( name = "payMode",value = "付款方式：ONEFORTHREE 押一付三 ,ONEFORONE 押一付一,TWOFORTHREE 押二付三，" +
                    "HARFYEAR 半年付 ,ONEYEAR 年付,OTHER 其他"),
            @ApiImplicitParam( name = "houseTags",value = "标签"),
            @ApiImplicitParam( name = "lift",value = "是否有电梯",dataType = "boolean"),
            @ApiImplicitParam( name = "key",value = "是否有钥匙",dataType = "boolean")
    })
    public ResponseInfo<PageBean<HouseResourceVo>> list(@RequestParam("cityCode")String code, @RequestParam("selected")Boolean selected,
                             @RequestParam("rentSeg")RentSeg rentSeg, @RequestParam("rentStart")BigDecimal rentStart,
                             @RequestParam("rentEnd")BigDecimal rentEnd,@RequestParam("renovationType")RenovationType
                                         renovationType,@RequestParam("payMode")PayMode payMode,
                             @RequestParam("orientation")Orientation orientation,@RequestParam("area")Area area,
                             @RequestParam("commissionType")CommissionType commissionType,
                             @RequestParam("houseTags")List<String> houseTags,
                             @RequestParam("lift")Boolean lift,@RequestParam("key")Boolean key){
        Page<HouseResourceVo> houseResourceVoPage = houseResourceService.findListPage(code,selected,rentStart,
                rentEnd,renovationType,payMode,orientation,area,commissionType,houseTags,lift,key);
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
}
