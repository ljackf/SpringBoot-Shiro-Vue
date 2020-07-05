package com.rrrent.theia.vo;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author : jack
 * @date : 2020/6/29
 * @Description:
 */
@Getter
@Setter
@ApiModel(value = "省份城市")
public class RegionVo {
    private String name;
    private Long code;
    List<RegionVo> children;
}
