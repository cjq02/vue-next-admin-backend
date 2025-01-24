package com.jacquinc.admin.sys.web;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jacquinc.admin.application.annotation.HasPermission;
import com.jacquinc.admin.sys.vo.DepartmentVOExt;
import com.jacquinc.admin.utils.StringUtils;
import com.jiujie.framework.adapter.vo.ResponseResult;
import com.jacquinc.admin.application.vo.Page;
import com.jacquinc.admin.application.web.BaseController;
import com.jacquinc.admin.sys.service.IDepartmentService;
import com.jacquinc.admin.sys.vo.DepartmentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author cjq
 * created on  2021/3/10
 */
@SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection", "SpringJavaAutowiredFieldsWarningInspection"})
@RestController
@RequestMapping("sys/dept")
@Api(tags = "部门、学院")
@ApiSort(12)
public class DepartmentController extends BaseController {

    @Autowired
    private IDepartmentService departmentService;

    @HasPermission("user")
    @RequestMapping(value = "/findSelectList.json", method = RequestMethod.GET)
    @ApiOperation("下拉列表")
    @ApiOperationSupport(order = 1)
    public ResponseResult findSelectList(String corpId, String inputValue) {
        return new ResponseResult(departmentService.findSelectList(corpId, inputValue));
    }

    @HasPermission("Dept:view")
    @PostMapping("/getPage.json")
    @ApiOperation("分页")
    @ApiOperationSupport(order = 2)
    public Page<DepartmentVO, DepartmentVO> getDepartmentPage(@RequestBody Page<DepartmentVO, DepartmentVO> page) {
        if (StringUtils.isEmpty(page.getCondition().getCorpId()) && !isSuperAdmin()){
            page.getCondition().setCorpId(getCurrentUser().getCorpId());
        }
        return departmentService.getDepartmentPage(page);
    }

    @HasPermission("Dept:edit")
    @PostMapping("/saveDepartment.json")
    @ApiOperation("保存")
    @ApiOperationSupport(order = 3)
    public ResponseResult saveDepartment(@RequestBody DepartmentVOExt vo) {
        vo.setCorpId(StringUtils.ifEmpty(vo.getCorpId(), getCurrentCorpId()));
        return departmentService.saveDepartment(vo, getCurrentUserId());
    }

    @HasPermission("Dept:edit")
    @PostMapping("/saveDepartmentList.json")
    @ApiOperation("批量保存")
    @ApiOperationSupport(order = 3)
    public ResponseResult saveDepartmentList(@RequestBody List<DepartmentVOExt> list) {
        list.forEach(vo -> vo.setCorpId(StringUtils.ifEmpty(vo.getCorpId(), getCurrentCorpId())));
        return departmentService.saveDepartmentList(list, getCurrentUserId());
    }

    @HasPermission("Dept:delete")
    @RequestMapping(value = "/deleteDepartmentById.json", method = RequestMethod.POST)
    @ApiOperation("删除")
    @ApiOperationSupport(order = 4)
    public ResponseResult deleteDepartmentById(String id) {
        return departmentService.deleteDepartmentById(id);
    }

    @HasPermission("user")
    @RequestMapping(value = "/getDeptList.json", method = RequestMethod.GET)
    @ApiOperation("下拉部门列表")
    @ApiOperationSupport(order = 7)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "corpId", value = "企业id", dataType = "String", required = true),
            @ApiImplicitParam(name = "inputValue", value = "搜索字段", dataType = "String", required = true)
    })
    public List<DepartmentVO> getDeptList(String corpId, String inputValue) {
        return departmentService.getDeptList(corpId, inputValue);
    }

    @HasPermission("Dept:view")
    @RequestMapping(value = "/getDeptById.json", method = RequestMethod.GET)
    @ApiOperation("部门明细")
    @ApiOperationSupport(order = 8)
    @ApiImplicitParam(name = "deptId", value = "部门id", dataType = "String", required = true)
    public DepartmentVO getDeptById(String deptId) {
        return departmentService.getById(deptId);
    }

}
