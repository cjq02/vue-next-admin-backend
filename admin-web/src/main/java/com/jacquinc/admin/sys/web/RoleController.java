package com.jacquinc.admin.sys.web;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.jiujie.framework.adapter.vo.ResponseResult;
import com.jacquinc.admin.application.annotation.HasPermission;
import com.jacquinc.admin.application.vo.Page;
import com.jacquinc.admin.application.web.BaseController;
import com.jacquinc.admin.sys.service.IRolePermissionService;
import com.jacquinc.admin.sys.service.IRoleService;
import com.jacquinc.admin.sys.vo.RoleVO;
import com.jacquinc.admin.sys.vo.RoleVOExt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings({"SpringJavaAutowiredFieldsWarningInspection", "SpringJavaInjectionPointsAutowiringInspection"})
@RestController
@RequestMapping("sys/role")
@Api(tags = "角色相关")
@ApiSort(930)
public class RoleController extends BaseController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IRolePermissionService rolePermissionService;

    /**
     * 列表
     *
     * @param page      页面参数
     * @return 页面信息
     */
    @HasPermission("Role:view")
    @RequestMapping(value = "/getRolePage.json", method = RequestMethod.POST)
    @ApiOperation(value = "角色分页")
    @ApiOperationSupport(order = 1)
    public Page<RoleVOExt, RoleVOExt> getRolePage(@RequestBody Page<RoleVOExt, RoleVOExt> page) {
        return roleService.getRolePage(page);
    }

    /**
     * 下拉列表
     *
     * @param inputValue 查询条件
     * @return 页面信息
     */
    @HasPermission("Role:view")
    @RequestMapping(value = "/findRoleSelectList.json", method = RequestMethod.GET)
    @ApiOperation(value = "角色下拉列表")
    @ApiOperationSupport(order = 2)
    public List<RoleVOExt> findSelectList(String inputValue) {
        return roleService.findSelectList(inputValue, getCurrentUser().getRoleTypes());
    }

    @HasPermission("Permission:view")
    @GetMapping(value = "/findPermissionIdsByRoleId.json")
    @ApiOperation(value = "角色权限列表")
    @ApiOperationSupport(order = 3)
    public List<String> findPermissionIdsByRoleId(String roleId) {
        return rolePermissionService.findPermissionIdsByRoleId(roleId);
    }

    @HasPermission("Role:view")
    @RequestMapping(value = "/getRoleById.json", method = RequestMethod.GET)
    @ApiOperation(value = "角色详情")
    @ApiOperationSupport(order = 4)
    public RoleVO getRoleById(String roleId) {
        return roleService.getRoleById(roleId);
    }

    @HasPermission("Role:edit")
    @RequestMapping(value = "/save.json", method = RequestMethod.POST)
    @ApiOperation(value = "保存角色")
    @ApiOperationSupport(order = 5)
    public ResponseResult save(@RequestBody RoleVOExt vo) {
        return roleService.save(vo, getCurrentUserId());
    }

    @HasPermission("Role:delete")
    @RequestMapping(value = "/deleteRole.json", method = RequestMethod.POST)
    @ApiOperation(value = "删除角色")
    @ApiOperationSupport(order = 6)
    public ResponseResult delete(String id) {
        return roleService.delete(id);
    }

}
