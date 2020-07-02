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
@ApiModel(value = "品牌公寓")
public class BrandResidenceVo {
    @ApiModelProperty(value = "公寓名称")
    String name;
    @ApiModelProperty(value = "公寓头图")
    String img;
    @ApiModelProperty(value = "公寓标签")
    String tag;
}
