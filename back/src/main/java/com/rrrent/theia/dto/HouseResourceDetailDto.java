package com.rrrent.theia.dto;

import com.rrrent.theia.util.model.HouseLayout;
import com.rrrent.theia.util.model.HouseSetting;
import com.rrrent.theia.util.model.Orientation;
import com.rrrent.theia.util.model.RenovationType;
import com.rrrent.theia.vo.CommunityVo;
import com.rrrent.theia.vo.LocationVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author : jack
 * @date : 2020/6/21
 * @Description:
 */
@Getter
@Setter
public class HouseResourceDetailDto {
    Long id;
    HouseLayout houseLayout;
    String name;
    boolean selected;
    BigDecimal rent;
    int room;
    int hall;
    int bathroom;
    BigDecimal area;
    String houseImg;
    String floorType;
    Date updateDate;
    String payMode;
    boolean lift;
    String settings;
    String architectureAge;
    String architectureType;
    Integer architectureSum;
    Integer houseSum;
    String serviceCompany;
    BigDecimal serviceFee;
    String developCompany;
    String address;
    String cName;
    BigDecimal secondHandPrice;
    BigDecimal longitude;
    BigDecimal latitude;
    String maintainer;
    String maintainerMobile;
    int floor;
    Orientation orientation;
    RenovationType renovationType;
    String des;
    String storeName;
    String maintainerIcon;
}
