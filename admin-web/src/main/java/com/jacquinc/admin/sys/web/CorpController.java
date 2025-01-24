package com.jacquinc.admin.sys.web;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jiujie.framework.adapter.vo.ResponseResult;
import com.jacquinc.admin.application.annotation.HasPermission;
import com.jacquinc.admin.application.vo.Page;
import com.jacquinc.admin.application.web.BaseController;
import com.jacquinc.admin.sys.enumerate.RoleTypeEnum;
import com.jacquinc.admin.sys.service.ICorpService;
import com.jacquinc.admin.sys.vo.CorpVOExt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings({"SpringJavaAutowiredFieldsWarningInspection", "SpringJavaInjectionPointsAutowiringInspection"})
@RestController
@RequestMapping("/sys/corp")
@Api(tags = "单位")
@ApiSort(11)
public class CorpController extends BaseController {

    @Autowired
    private ICorpService corpService;

    @HasPermission("Corp:view")
    @RequestMapping(method = RequestMethod.POST, value = "/getCorpPage.json")
    @ApiOperation("单位分页")
    @ApiOperationSupport(order = 1)
    public Page<CorpVOExt, CorpVOExt> getCorpPage(@RequestBody Page<CorpVOExt, CorpVOExt> page) {
        if (!getCurrentUser().getRoleTypes().contains(RoleTypeEnum._01.getCode())) {
            page.getCondition().setCorpId(getCurrentCorpId());
        }
        return corpService.getPage(page);
    }

    @HasPermission("user")
    @PostMapping("/findCorpList.json")
    @ApiOperation("单位列表")
    @ApiOperationSupport(order = 2)
    public List<CorpVOExt> findList(@RequestBody CorpVOExt condition) {
        if (!getCurrentUser().getRoleTypes().contains(RoleTypeEnum._01.getCode())) {
            condition.setCorpId(getCurrentCorpId());
        }
        return corpService.findList(condition);
    }

    @HasPermission("Corp:view")
    @RequestMapping(value = "/getCorpById.json", method = RequestMethod.GET)
    @ApiOperation("单位详情")
    @ApiOperationSupport(order = 3)
    public CorpVOExt getRoleById(String id) {
        return corpService.getCorpInfoById(id);
    }

    @HasPermission("Corp:edit")
    @PostMapping("/saveCorp.json")
    @ApiOperation("保存单位")
    @ApiOperationSupport(order = 4)
    public ResponseResult addAndModify(@RequestBody CorpVOExt vo) {
        return corpService.addAndModify(vo, getCurrentUserId());
    }

    @HasPermission("Corp:delete")
    @PostMapping("/deleteCorp.json")
    @ApiOperation("删除单位")
    @ApiOperationSupport(order = 5)
    public ResponseResult deleteCorpById(String id) {
        return corpService.deleteCorpById(id);
    }

}

