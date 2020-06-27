package com.rrrent.theia.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author : jack
 * @date : 2020/6/21
 * @Description:
 */
@Getter
@Setter
@ApiModel(value = "地理坐标")
public class LocationVo {
    @ApiModelProperty(value = "经度")
    BigDecimal longitude;
    @ApiModelProperty(value = "纬度")
    BigDecimal latitude;
}
