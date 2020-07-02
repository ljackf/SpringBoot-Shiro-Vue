package com.rrrent.theia.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : jack
 * @date : 2020/7/1
 * @Description:
 */
@Getter
@Setter
@ApiModel(value = "标签")
public class TagVo {
    @ApiModelProperty(value = "标签名称")
    String name;
    @ApiModelProperty(value = "标签值")
    String value;
}
