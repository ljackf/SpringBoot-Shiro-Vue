package com.rrrent.theia.vo;

import com.rrrent.theia.util.model.HouseLayout;
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
@ApiModel(value = "房源")
public class HouseResourceVo {
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
    String houseImg;
    @ApiModelProperty(value = "标签")
    List<String> tags;
}
