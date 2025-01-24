package com.jacquinc.admin.sys.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jacquinc.admin.application.constants.Constants;
import com.jacquinc.admin.application.vo.Page;
import com.jacquinc.admin.sys.entity.MenuEntity;
import com.jacquinc.admin.sys.entity.MenuEntityCondition;
import com.jacquinc.admin.sys.vo.MenuMeta;
import com.jacquinc.admin.sys.vo.MenuVOExt;
import com.jacquinc.admin.sys.vo.RouterMenu;
import com.jacquinc.admin.sys.vo.UserVOExt;
import com.jacquinc.admin.utils.ListUtils;
import com.jiujie.framework.adapter.vo.ResponseResult;
import com.jiujie.framework.base.utils.BeanUtils;
import com.jiujie.framework.base.utils.ReflectionUtils;
import com.jiujie.framework.base.utils.StringUtils;
import com.jiujie.framework.exception.BusinessException;
import com.jiujie.framework.mybatis.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author cjq
 */
@SuppressWarnings({"unchecked", "rawtypes", "SpringJavaAutowiredFieldsWarningInspection"})
@Service
@Transactional
public class MenuService extends BaseServiceImpl implements IMenuService {

    private static final String MAPPER_NAMESPACE = "com.jacquinc.admin.sys.sqlmapper.MenuMapper";
    private static final String MENU_ROOT_ID = "1";
    private static final String FIRST_LEVEL_MENU = "layout";
    private static final String VUE_SUFFIX = ".vue";
    private static final String PARENT_URL = "/#";

    @Autowired
    IRolePermissionService rolePermissionService;

    @Autowired
    IPermissionService permissionService;

    private List findMenuList(MenuVOExt condition) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("condition", condition);
        List<MenuVOExt> list = this.getMyBatisDao().selectListBySql(MAPPER_NAMESPACE + ".findAll", params);

        if (Constants.YES.equals(condition.getHasPermission())) {
            for (MenuVOExt vo : list) {
                vo.setPermissions(permissionService.findByMenuId(vo.getId()));
            }
        }

        if (Constants.YES.equals(condition.getIsSideMenu())) {
            return transferRouteMenu(list);
        }

        return list;
    }

    @Override
    public Page<MenuVOExt, MenuVOExt> getMenuPage(Page<MenuVOExt, MenuVOExt> page) {
        List<MenuVOExt> records = findMenuList(page.getCondition());
        page.setTotalRecord(records.size());
        page.setRecords(records);
        return page;
    }

    @Override
    public List<MenuVOExt> getMenuList() {
        MenuVOExt condition = new MenuVOExt();
        condition.setHasRoot(Constants.YES);
        return findMenuList(condition);
    }

    @Override
    public List<MenuVOExt> getMenuTree() {
        MenuVOExt condition = new MenuVOExt();
        condition.setHasRoot(Constants.YES);
        return findMenuTree(condition);
    }

    @Override
    public MenuVOExt getMenuById(String id) {
        MenuEntityCondition condition = new MenuEntityCondition();
        condition.createCriteria().andIdEqualTo(id);
        MenuVOExt menuVO = BeanUtils.copyToNewBean(this.getDao().selectOneByCondition(condition), MenuVOExt.class);
        menuVO.setPermissions(permissionService.findByMenuId(menuVO.getId()));
        return menuVO;
    }

    @Override
    public List<MenuVOExt> findMenuListWithPermission() {
        return this.getMyBatisDao().selectListBySql(MAPPER_NAMESPACE + ".findMenuListWithPermission");
    }

    @Override
    public List<RouterMenu> findSideMenuList(UserVOExt user) {
        MenuVOExt condition = new MenuVOExt();
        condition.setIsSideMenu(Constants.YES);
        condition.setRoleIds(user.getRoleIds());
        return findMenuTree(condition);
    }

    private <MenuNode> List<MenuNode> findMenuTree(MenuVOExt condition) {
        List<MenuNode> list = (List<MenuNode>) findMenuList(condition);
        List<MenuNode> result = generateTree(list);
        for (MenuNode menuNode : result) {
            List<MenuNode> children = (List<MenuNode>) ReflectionUtils.getFieldValue(menuNode, "children");
            if (!children.isEmpty()) {
                sortTree(children);
                for (MenuNode menuChildNode : children) {
                    List<MenuNode> grandchildren = (List<MenuNode>) ReflectionUtils.getFieldValue(menuChildNode, "children");
                    if (!grandchildren.isEmpty()) {
                        sortTree(grandchildren);
                    }
                }
            }
        }
        sortTree(result);
        return result;
    }

    private <MenuNode> void sortTree(List<MenuNode> list) {
        list.sort(Comparator.comparingInt(menuNode -> (int) ReflectionUtils.getFieldValue(menuNode, "priority")));
    }

    private List<RouterMenu> transferRouteMenu(List<MenuVOExt> menus) {
        List<RouterMenu> routerMenus = new ArrayList<>();
        try {
            for (MenuVOExt menuVO : menus) {
                RouterMenu menu = new RouterMenu();
                menu.setId(menuVO.getId());
                menu.setPath(menuVO.getUrl().replace(VUE_SUFFIX, ""));
                menu.setName(menuVO.getPermissionPrefixCode());
                MenuMeta meta = new MenuMeta();
                meta.setIcon(menuVO.getPermissionPrefixCode());
                //如果使用前端vue-i18n对菜单进行国际化，则title設置为code，且code需要与国际化资源文件中的key值一致
                meta.setTitle(menuVO.getName());
                menu.setPriority(menuVO.getPriority());
                menu.setParentId(menuVO.getParentId());
                menu.setComponent(getComponentByPath(menuVO.getUrl(), isParent(menuVO)));
                menu.setMeta(meta);
                menu.setHidden(false);
                routerMenus.add(menu);

            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return routerMenus;
    }

    private boolean isParent(MenuVOExt menuVO) {
        return menuVO.getUrl().contains(PARENT_URL);
    }

    private String getComponentByPath(String path, boolean isParent) {
        if (isParent) {
            return FIRST_LEVEL_MENU;
        }
        return "views" + path;
    }

    @SuppressWarnings("unchecked")
    private <MenuNode> List<MenuNode> generateTree(List<MenuNode> list) {
        List<MenuNode> result = Lists.newArrayList();
        Map<String, MenuNode> map = ListUtils.toMap(list, "id");
        for (Map.Entry<String, MenuNode> entry : map.entrySet()) {
            MenuNode menuNode = entry.getValue();
            String parentId = Optional.ofNullable(ReflectionUtils.getFieldValue(menuNode, "parentId")).orElse("").toString();
            if (!parentId.equals(MENU_ROOT_ID)) {
                MenuNode parentNode = map.get(parentId);
                if (parentNode != null) {
                    List<MenuNode> children = (List<MenuNode>) ReflectionUtils.getFieldValue(parentNode, "children");
                    children.add(menuNode);
                }
            } else {
                result.add(menuNode);
            }
        }
        return result;
    }

    @Override
    public ResponseResult save(MenuVOExt menu) {
        MenuEntity saveEntity;
        if (StringUtils.isEmpty(menu.getParentId())) {
            throw new BusinessException("保存失败，上级菜单不能为空！");
        }
        if (countByPermissionPrefixCode(menu.getId(), menu.getPermissionPrefixCode()) > 0) {
            throw new BusinessException("保存失败，权限码" + menu.getPermissionPrefixCode() + " 已经存在！");
        }
        if (menu.getId() == null) {
            MenuEntity entity = BeanUtils.copyToNewBean(menu, MenuEntity.class);
            saveEntity = this.saveWithQuery(entity);
        } else {
            if (menu.getId().equals(menu.getParentId())) {
                throw new BusinessException("保存失败，当前菜单的上级不能是自己");
            }
            //保存
            MenuEntity entity = BeanUtils.copyToNewBean(menu, MenuEntity.class);
            saveEntity = this.saveWithQuery(entity);
        }
        //
        if (saveEntity == null) {
            throw new BusinessException("保存失败！未知系统异常，请联系管理员！");
        }

        //保存(新增、修改、删除)权限
        if (menu.getPermissions() != null && !menu.getPermissions().isEmpty()) {
            permissionService.saveList(saveEntity.getId(), menu.getPermissions());
        }

        return new ResponseResult("保存成功！");
    }

    @Override
    public ResponseResult deleteMenu(String id) {
        //判断子
        if (countChildrenByParentId(id) > 0) {
            throw new BusinessException("删除失败，存在子菜单关联，不允许删除！");
        }
        //查询菜单权限Id集合
        Set<String> permissionIds = permissionService.findIdsByMenuId(id);
        //判断角色权限表是否关联
        ResponseResult responseResult = permissionService.countByPermissionIds(permissionIds);
        if (!responseResult.isSuccess()) {
            throw new BusinessException(responseResult.getMessage() + "，不允许删除！");
        }
        //删除菜单权限
        permissionService.deleteByMenuId(id);
        List<String> permissionIdList = new ArrayList<>(permissionIds);
        //删除已配置的菜单权限的角色
        rolePermissionService.deleteByPermissionIds(permissionIdList);
        this.deleteByPrimaryKey(MenuEntity.class, id);
        return new ResponseResult("删除成功");
    }

    private long countByPermissionPrefixCode(String id, String permissionPrefixCode) {
        MenuEntityCondition condition = new MenuEntityCondition();
        if (StringUtils.isNotBlank(id)) {
            condition.createCriteria().andIdNotEqualTo(id).andPermissionPrefixCodeEqualTo(permissionPrefixCode);
        } else {
            condition.createCriteria().andPermissionPrefixCodeEqualTo(permissionPrefixCode);
        }
        return this.countByCondition(condition);
    }

    private long countChildrenByParentId(String parentId) {
        MenuEntityCondition condition = new MenuEntityCondition();
        condition.createCriteria().andParentIdEqualTo(parentId);
        return this.countByCondition(condition);
    }

}
