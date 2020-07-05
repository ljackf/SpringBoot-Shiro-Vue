package com.rrrent.theia.dao;

import com.alibaba.fastjson.JSONObject;
import com.rrrent.theia.dto.HouseResourceDetailDto;
import com.rrrent.theia.util.model.*;
import com.rrrent.theia.vo.HouseResourceVo;
import com.rrrent.theia.vo.TagVo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : jack
 * @date : 2020/6/21
 * @Description:
 */
public interface HouseResourceDao {
    List<HouseResourceVo> findListPage(@Param("code") String code, @Param("selected") Boolean selected, @Param("houseLayout")HouseLayout houseLayout, @Param("rentStart")
            BigDecimal
            rentStart, @Param("rentEnd") BigDecimal rentEnd,
                                       @Param("renovationType") RenovationType renovationType, @Param("payMode") PayMode payMode, @Param("orientation") Orientation orientation, @Param("area") Area area,
                                       @Param("commissionType") CommissionType commissionType, @Param("houseTags") List<String> houseTags, @Param("lift") Boolean lift, @Param("key") Boolean key);

    HouseResourceDetailDto findById(@Param("id") Long id);

    List<TagVo> findTagsByHouseId(@Param("id")Long id);

    int findCountByUserIdHouseId(@Param("userId")Long userId, @Param("id")Long id);

    List<HouseResourceVo> findListPageByUserId(@Param("userId")Long userId);

    void collect(@Param("userId") Long userId, @Param("id") Long id);

    void cancelCollect(@Param("userId") Long userId, @Param("id") Long id);

    List<TagVo> findAllTags();

    int countHouseResource(JSONObject jsonObject);

    List<JSONObject> listHouseResource(JSONObject jsonObject);

    void addHouseResource(JSONObject requestJson);
}
