package com.rrrent.theia.vo;

import com.rrrent.theia.util.model.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : jack
 * @date : 2020/6/21
 * @Description:
 */
@Getter
@Setter
@ApiModel(value = "房源详情")
public class HouseResourceDetailVo {
    @ApiModelProperty(value = "房源ID")
    Long id;
    @ApiModelProperty(value = "户型：COTENANCY 合租 ,WHOLERENT 整租，SHOPOFFICE 商铺办公")
    HouseLayout houseLayout;
    @ApiModelProperty(value = "小区名称")
    String name;
    @ApiModelProperty(value = "是否是优选房源：true是  false否")
    boolean selected;
    @ApiModelProperty(value = "租金")
    BigDecimal rent;
    @ApiModelProperty(value = "几室")
    int room;
    @ApiModelProperty(value = "几厅")
    int hall;
    @ApiModelProperty(value = "几卫")
    int bathroom;
    @ApiModelProperty(value = "面积")
    BigDecimal area;
    @ApiModelProperty(value = "房屋主图")
    List<String> houseImgs;
    @ApiModelProperty(value = "标签")
    List<TagVo> tags;
    @ApiModelProperty(value = "楼层类型：LOW低楼层，MEDIUM中楼层、HIGH高楼层...")
    String floorType;
    @ApiModelProperty(value = "楼层")
    int floor;
    @ApiModelProperty("房屋朝向：EAST 东,SOUTH 南,WEST 西,NORTH 北,SOUTHEAST 东南,NORTHEAST 东北, SOUTHWEST 西南,NORTHWEST 西北,NORTHSOUTHPENETRATION 南北通透")
    Orientation orientation;
    @ApiModelProperty("装修类型：HAUTECOUTURE 豪装 ,HARDCOVER 精装, MEDIUMDECORATION 中装，PAPERBACK 简装，ROUGHCAST 毛坯")
    RenovationType renovationType;
    @ApiModelProperty(value = "描述")
    String des;
    @ApiModelProperty(value = "是否是收藏，true 是，false否")
    boolean col;
    @ApiModelProperty(value = "店名")
    String storeName;
    @ApiModelProperty(value = "维护人头像")
    String maintainerIcon;
    @ApiModelProperty(value = "更新时间,时间戳")
    Long updateTime;
    @ApiModelProperty(value = "付款方式：付款方式：ONEFORTHREE 押一付三 ,ONEFORONE 押一付一,TWOFORTHREE 押二付三，HARFYEAR 半年付 ,ONEYEAR 年付," +
            "OTHER 其他")
    String payMode;
    @ApiModelProperty(value = "是否有电梯:true 有， false 无")
    boolean lift;
    @ApiModelProperty(value = "房屋配置：TV 电视 ,ICEBOX 冰箱,WASHING 洗衣机,AIRCONDITIONER 空调,HEATER 热水器, BED床,STEAM暖气,  " +
            "BROADBAND 宽带,WARDROBE 衣柜,GASSTOVE 燃气灶,SOFA 沙发,TABLESCHAIRS 桌椅,LAMPBLACKMACHINE 油烟机, " +
            "ELECTROMAGNETICFURNACE 电磁炉,MICROWAVEOVEN 微波炉")
    List<HouseSetting>settings;
    @ApiModelProperty(value = "小区信息")
    CommunityVo community;
    @ApiModelProperty(value = "经纬度")
    LocationVo location;
    @ApiModelProperty(value = "房源维护人")
    String maintainer;
    @ApiModelProperty(value = "房源维护人电话")
    String maintainerMobile;
}
