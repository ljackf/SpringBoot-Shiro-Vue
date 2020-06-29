package com.rrrent.theia.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : jack
 * @date : 2020/6/29
 * @Description:
 */
@Getter
@Setter
@ApiModel(value = "分页参数")
public class PageBo {
    @ApiModelProperty("页码，起始1")
    Integer page = 1;
    @ApiModelProperty("分页大小，默认10")
    Integer size = 10;
}
