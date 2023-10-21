package com.buba.service;

import com.buba.dao.AdminMenuDao;
import com.buba.pojo.AdminMenu;
import com.buba.pojo.AdminRoleMenu;
import com.buba.pojo.AdminUserRole;
import com.buba.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminMenuService {
    @Autowired
    AdminMenuDao adminMenuDao;
    @Autowired
    UserService userService;
    @Autowired
    AdminUserRoleService adminUserRoleService;
    @Autowired
    AdminRoleMenuService adminRoleMenuService;

    public List<AdminMenu> getAllByParentId(int parentId) {
        return adminMenuDao.findAllByParentId(parentId);
    }

    public List<AdminMenu> getMenusByCurrentUser() {

        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.findByUsername(username);


        List<Integer> rids = adminUserRoleService.listAllByUid(user.getId())
                .stream().map(AdminUserRole::getRid).collect(Collectors.toList());


        List<Integer> menuIds = adminRoleMenuService.findAllByRid(rids)
                .stream().map(AdminRoleMenu::getMid).collect(Collectors.toList());
        List<AdminMenu> menus = adminMenuDao.findAllById(menuIds).stream().distinct().collect(Collectors.toList());

        handleMenus(menus);
        return menus;
    }

    public List<AdminMenu> getMenusByRoleId(int rid) {
        List<Integer> menuIds = adminRoleMenuService.findAllByRid(rid)
                .stream().map(AdminRoleMenu::getMid).collect(Collectors.toList());
        List<AdminMenu> menus = adminMenuDao.findAllById(menuIds);

        handleMenus(menus);
        return menus;
    }

    public void handleMenus(List<AdminMenu> menus) {
//            for (AdminMenu menu : menus) {
//                List<AdminMenu> children = getAllByParentId(menu.getId());
//                menu.setChildren(children);
//            }
//            Iterator<AdminMenu> iterator = menus.iterator();
//            while (iterator.hasNext()) {
//                AdminMenu menu = iterator.next();
//                if (menu.getParentId() != 0) {
//                    iterator.remove();
//                }
//            }

        menus.forEach(m -> {
            List<AdminMenu> children = getAllByParentId(m.getId());
            m.setChildren(children);
        });

        menus.removeIf(m -> m.getParentId() != 0);
    }
}
