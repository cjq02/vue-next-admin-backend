package com.jacquinc.admin.sys.web;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.google.common.collect.Lists;
import com.jacquinc.admin.application.annotation.HasPermission;
import com.jacquinc.admin.application.constants.Constants;
import com.jacquinc.admin.application.utils.TreeUtils;
import com.jacquinc.admin.application.vo.Page;
import com.jacquinc.admin.application.vo.TreeSelectNode;
import com.jacquinc.admin.application.web.BaseController;
import com.jacquinc.admin.sys.service.IMenuService;
import com.jacquinc.admin.sys.service.IPermissionService;
import com.jacquinc.admin.sys.vo.MenuVOExt;
import com.jacquinc.admin.sys.vo.RouterMenu;
import com.jiujie.framework.adapter.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection", "SpringJavaAutowiredFieldsWarningInspection"})
@RestController
@RequestMapping("sys/menu")
@Api(tags = "菜单管理")
@ApiSort(920)
public class MenuController extends BaseController {

    @Autowired
    private IMenuService menuService;
    @Autowired
    private IPermissionService permissionService;

    @HasPermission("Menu:view")
    @RequestMapping(method = RequestMethod.POST, value = "/getMenuPage.json")
    @ApiOperationSupport(order = 1)
    @ApiOperation("列表")
    public Page<MenuVOExt, MenuVOExt> getMenuPage(@RequestBody Page<MenuVOExt, MenuVOExt> page) {
        return menuService.getMenuPage(page);
    }

    @HasPermission("user")
    @GetMapping(value = "/findSideMenuList.json")
    public List<RouterMenu> findSideMenuList() {
        return menuService.findSideMenuList(getCurrentUser());
    }

    @HasPermission("Menu:view")
    @GetMapping(value = "/findMenuListWithPermission.json")
    public List<MenuVOExt> findMenuListWithPermission() {
        return menuService.findMenuListWithPermission();
    }

    @HasPermission("Menu:view")
    @GetMapping(value = "/getMenuTree.json")
    public List<TreeSelectNode> getMenuTree() {
        List<TreeSelectNode> tree = TreeUtils.getTreeForSelect(menuService.getMenuTree());
        TreeSelectNode root = new TreeSelectNode();
        root.setId(Constants.MENU_ROOT_ID);
        root.setValue(Constants.MENU_ROOT_ID);
        root.setLabel(Constants.MENU_ROOT_NAME);
        root.setChildren(tree);
        return Lists.newArrayList(root);
    }

    @HasPermission("Menu:view")
    @GetMapping(value = "/getRootMenuList.json")
    public List<MenuVOExt> getMenuListWithRoot() {
        List<MenuVOExt> list = menuService.getMenuList();
        MenuVOExt root = new MenuVOExt();
        root.setId(Constants.MENU_ROOT_ID);
        root.setName(Constants.MENU_ROOT_NAME);
        root.setUrl("/");
        list.add(root);
        return list;
    }

    @HasPermission("Menu:view")
    @RequestMapping(value = "/getMenuById.json", method = RequestMethod.GET)
    public MenuVOExt getMenuById(String id) {
        return menuService.getMenuById(id);
    }

    @HasPermission("Menu:edit")
    @RequestMapping(value = "/saveMenu.json", method = RequestMethod.POST)
    public ResponseResult save(@RequestBody MenuVOExt vo) {
        return menuService.save(vo);
    }

    @HasPermission("Menu:delete")
    @PostMapping(value = "/deleteMenu.json")
    public ResponseResult deleteMenu(String id) {
        return menuService.deleteMenu(id);
    }

    @HasPermission("Permission:delete")
    @PostMapping(value = "/deletePermission.json")
    public ResponseResult deletePermission(String id) {
        return permissionService.delete(id);
    }

}
