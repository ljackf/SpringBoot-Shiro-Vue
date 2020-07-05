package com.rrrent.theia.dao;

import com.rrrent.theia.vo.RegionVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : jack
 * @date : 2020/6/29
 * @Description:
 */
public interface RegionStreetDao {
    List<RegionVo> findByCode(@Param("code") Long code);
}
