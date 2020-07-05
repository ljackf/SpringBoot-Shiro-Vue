package com.rrrent.theia.controller.back;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.rrrent.theia.bo.HouseResourceBo;
import com.rrrent.theia.bo.PageBo;
import com.rrrent.theia.service.HouseResourceService;
import com.rrrent.theia.service.UserService;
import com.rrrent.theia.util.CommonUtil;
import com.rrrent.theia.util.PageBean;
import com.rrrent.theia.util.ResponseInfo;
import com.rrrent.theia.vo.HouseResourceDetailVo;
import com.rrrent.theia.vo.HouseResourceVo;
import com.rrrent.theia.vo.TagVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author : jack
 * @date : 2020/6/21
 * @Description:
 */
@RestController
@RequestMapping("/houseResource")
public class BackHouseResourceController {

    @Autowired
    private HouseResourceService houseResourceService;
    @Autowired
    private UserService userService;


    /**
     * 新增房源
     */
    @PostMapping("/addHouseResource")
    public JSONObject addHouseResource(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "name");
        return houseResourceService.addHouseResource(requestJson);
    }

    @GetMapping("/listHouseResource")
    public JSONObject listArticle(HttpServletRequest request) {
        return houseResourceService.listHouseResource(CommonUtil.request2Json(request));
    }

}
