package com.jacquinc.admin.sys.service;

import com.jacquinc.admin.application.vo.Page;
import com.jacquinc.admin.sys.vo.MenuVOExt;
import com.jacquinc.admin.sys.vo.RouterMenu;
import com.jacquinc.admin.sys.vo.UserVOExt;
import com.jiujie.framework.adapter.vo.ResponseResult;
import com.jiujie.framework.exception.BusinessException;

import java.util.List;

/**
 * @author xiezm
 */
public interface IMenuService {

    /**
     * 获取单位页面信息
     *
     * @param page 页面信息
     * @return 页面信息
     */
    Page<MenuVOExt, MenuVOExt> getMenuPage(Page<MenuVOExt, MenuVOExt> page);

    /**
     * @return 获取菜单列表
     */
    List<MenuVOExt> getMenuList();

    /**
     * 菜单树
     *
     * @return 列表
     */
    List<MenuVOExt> getMenuTree();

    /**
     * 根据ID获取菜单对象
     *
     * @param id 主键
     * @return 对象
     */
    MenuVOExt getMenuById(String id);

    /**
     * 菜单列表包含权限
     *
     * @return 列表
     */
    List<MenuVOExt> findMenuListWithPermission();

    /**
     * 首页左侧菜单栏
     *
     * @param user 当前用户
     * @return 菜单树
     */
    List<RouterMenu> findSideMenuList(UserVOExt user);

    /**
     * 保存菜单
     *
     * @param menu 菜单
     * @return 响应结果
     */
    ResponseResult save(MenuVOExt menu) throws BusinessException;

    ResponseResult deleteMenu(String id);

}
