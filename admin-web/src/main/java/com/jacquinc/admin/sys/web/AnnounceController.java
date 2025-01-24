package com.jacquinc.admin.sys.web;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jiujie.framework.adapter.vo.ResponseResult;
import com.jacquinc.admin.application.annotation.HasPermission;
import com.jacquinc.admin.application.vo.Page;
import com.jacquinc.admin.application.web.BaseController;
import com.jacquinc.admin.sys.service.IAnnounceService;
import com.jacquinc.admin.sys.vo.AnnounceVOExt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings({"SpringJavaAutowiredFieldsWarningInspection", "SpringJavaInjectionPointsAutowiringInspection"})
@RestController
@RequestMapping("/announce")
@Api(tags = "公告管理")
@ApiSort(10)
public class AnnounceController extends BaseController {

    @Autowired
    private IAnnounceService announceService;

    @HasPermission("Announce:view")
    @PostMapping("/getPage.json")
    @ApiOperationSupport(order = 1)
    @ApiOperation("公告列表")
    public Page<AnnounceVOExt, AnnounceVOExt> getList(@RequestBody Page<AnnounceVOExt, AnnounceVOExt> page) {
        return announceService.getPage(page);
    }

    @HasPermission("Announce:edit")
    @RequestMapping(method = RequestMethod.POST, value = "/save.json")
    @ApiOperationSupport(order = 2)
    @ApiOperation("保存/提交公告")
    public ResponseResult save(@RequestBody AnnounceVOExt vo) {
        return announceService.save(vo, getCurrentUserId());
    }

    @HasPermission("Announce:view")
    @RequestMapping(method = RequestMethod.GET, value = "/getById.json")
    @ApiOperationSupport(order = 3)
    @ApiImplicitParam(name = "id", value = "id", dataType = "String", required = true)
    @ApiOperation("根据id获取公告")
    public AnnounceVOExt getById(String id) {
        return announceService.getById(id);
    }

    @HasPermission("Announce:delete")
    @RequestMapping(method = RequestMethod.POST, value = "/del.json")
    @ApiOperationSupport(order = 4)
    @ApiImplicitParam(name = "id", value = "id", dataType = "String", required = true)
    @ApiOperation("删除公告")
    public ResponseResult delete(String id) {
        return announceService.delete(id);
    }

    @HasPermission("user")
    @RequestMapping(method = RequestMethod.GET, value = "/getPublicList.json")
    @ApiOperationSupport(order = 5)
    @ApiOperation("公告列表")
    public List<AnnounceVOExt> getList() {
        return announceService.getPublicAnnounce();
    }
}
