package com.rrrent.theia.service.impl;

import com.github.pagehelper.Page;
import com.rrrent.theia.bo.PageBo;
import com.rrrent.theia.service.HouseResourceService;
import com.rrrent.theia.util.model.*;
import com.rrrent.theia.vo.HouseResourceDetailVo;
import com.rrrent.theia.vo.HouseResourceVo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : jack
 * @date : 2020/6/21
 * @Description:
 */
@Service
public class HouseResourceServiceImpl implements HouseResourceService {

    @Override
    public Page<HouseResourceVo> findListPage(String code, Boolean selected, BigDecimal rentStart, BigDecimal rentEnd, RenovationType renovationType, PayMode payMode, Orientation orientation, Area area, CommissionType commissionType, List<String> houseTags, Boolean lift, Boolean key, PageBo page) {
        Page<HouseResourceVo> pagination = new Page<>(page.getPage(), page.getSize());
        return pagination;
    }

    @Override
    public HouseResourceDetailVo findDetailById(Long id) {
        return null;
    }

    @Override
    public List<HouseResourceVo> findListByUserId() {
        return null;
    }
}
