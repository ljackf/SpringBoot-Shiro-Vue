package com.rrrent.theia.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author : jack
 * @date : 2020/6/21
 * @Description: 社区信息
 */
@Getter
@Setter
@ApiModel(value = "社区信息")
public class CommunityVo {
    @ApiModelProperty(value = "建筑年代")
    String architectureAge;
    @ApiModelProperty(value = "建筑类型：塔楼")
    String architectureType;
    @ApiModelProperty(value = "楼栋总数")
    Integer architectureSum;
    @ApiModelProperty(value = "房屋总户数")
    Integer houseSum;
    @ApiModelProperty(value = "物业公司")
    String serviceCompany;
    @ApiModelProperty(value = "物业费")
    BigDecimal serviceFee;
    @ApiModelProperty(value = "开发商")
    String developCompany;
    @ApiModelProperty(value = "小区地址")
    String address;
    @ApiModelProperty(value = "小区名称")
    String name;
    @ApiModelProperty(value = "二手房价")
    BigDecimal secondHandPrice;
}
