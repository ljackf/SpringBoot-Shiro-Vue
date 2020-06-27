package com.rrrent.theia.service;

import com.github.pagehelper.Page;
import com.rrrent.theia.util.model.*;
import com.rrrent.theia.vo.HouseResourceDetailVo;
import com.rrrent.theia.vo.HouseResourceVo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : jack
 * @date : 2020/6/21
 * @Description:
 */
public interface HouseResourceService {
    Page<HouseResourceVo> findListPage(String code, Boolean selected, BigDecimal rentStart, BigDecimal rentEnd, RenovationType renovationType, PayMode payMode, Orientation orientation, Area area, CommissionType commissionType, List<String> houseTags, Boolean lift, Boolean key);

    HouseResourceDetailVo findDetailById(Long id);
}
