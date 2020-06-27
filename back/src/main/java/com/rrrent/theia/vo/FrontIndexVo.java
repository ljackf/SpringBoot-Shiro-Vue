package com.rrrent.theia.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author : jack
 * @date : 2020/6/21
 * @Description:
 */
@Getter
@Setter
@ApiModel(value = "首页轮播数据和品牌公寓")
public class FrontIndexVo {
    @ApiModelProperty(value = "品牌公寓")
    List<BrandResidenceVo> brandResidences;
    @ApiModelProperty(value = "轮播图")
    List<String>carousels;
}
