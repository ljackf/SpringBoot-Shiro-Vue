package com.rrrent.theia.bo;

import com.rrrent.theia.util.model.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import springfox.documentation.service.Tags;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : jack
 * @date : 2020/6/29
 * @Description:
 */
@Getter
@Setter
@ApiModel(value = "房源列表参数")
public class HouseResourceBo {
    @ApiModelProperty(value = "关键字搜索")
    String keyword;
    @ApiModelProperty(value = "排序方式:PRICEDESC：价格倒序,PRICEASC：价格正序,CREATEDESC:最新发布")
    String orderMode;
    @ApiModelProperty(value = "城市code",required = true)
    String code;
    @ApiModelProperty("是否是优选好房")
    Boolean selected;
    @ApiModelProperty("租金段：LESS800 少于800,F800T1500 800-1500,F1500T2000 1500-2000,F2000T4000 2000-4000,F4000T5000 4000-5000,F5000T8000 5000-8000,MORE8000 8000以上")
    RentSeg rentSeg;
    @ApiModelProperty(value = "户型：COTENANCY 合租 ,WHOLERENT 整租，SHOPOFFICE 商铺办公")
    HouseLayout houseLayout;
    @ApiModelProperty("租金大于多少")
    BigDecimal rentStart;
    @ApiModelProperty("租金小于多少")
    BigDecimal rentEnd;
    @ApiModelProperty("装修类型：HAUTECOUTURE 豪装 ,HARDCOVER 精装, MEDIUMDECORATION 中装，PAPERBACK 简装，ROUGHCAST 毛坯")
    RenovationType renovationType;
    @ApiModelProperty("付款方式：ONEFORTHREE 押一付三 ,ONEFORONE 押一付一,TWOFORTHREE 押二付三，HARFYEAR 半年付 ,ONEYEAR 年付,OTHER 其他")
    PayMode payMode;
    @ApiModelProperty("房屋朝向：EAST 东,SOUTH 南,WEST 西,NORTH 北,SOUTHEAST 东南,NORTHEAST 东北, SOUTHWEST 西南,NORTHWEST 西北,NORTHSOUTHPENETRATION 南北通透")
    Orientation orientation;
    @ApiModelProperty("房屋面积：LESS50 少于50平米,F50T800 50-80平米,F80T200 80-200平米,F200T300 200-300平米, F300T500 300-500平米, MORE500 大于500平米")
    Area area;
    @ApiModelProperty("佣金段：LESS30 0-30%,F30T50 30%-50%,F50T80 50%-80%,F80T100 80%-100%,MORE100 大于100%")
    CommissionType commissionType;
    @ApiModelProperty("标签")
    List<String> houseTags;
    @ApiModelProperty("是否有电梯")
    Boolean lift;
    @ApiModelProperty("是否有钥匙")
    Boolean key;
}
