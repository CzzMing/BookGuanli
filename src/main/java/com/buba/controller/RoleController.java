package com.buba.controller;

import com.buba.pojo.AdminRole;
import com.buba.result.Result;
import com.buba.result.ResultFactory;
import com.buba.service.AdminPermissionService;
import com.buba.service.AdminRoleMenuService;
import com.buba.service.AdminRolePermissionService;
import com.buba.service.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class RoleController {
    @Autowired
    AdminRoleService adminRoleService;
    @Autowired
    AdminPermissionService adminPermissionService;
    @Autowired
    AdminRolePermissionService adminRolePermissionService;
    @Autowired
    AdminRoleMenuService adminRoleMenuService;


    @GetMapping("/admin/role/select")
    public Result listRoles() {
        return ResultFactory.buildSuccessResult(adminRoleService.listWithPermsAndMenus());
    }
    @PutMapping("/admin/role/status")
    public Result updateRoleStatus(@RequestBody @Valid AdminRole adminRole) {
        AdminRole adminRole1 = adminRoleService.updateRoleStatus(adminRole);
        String message = "用户"+ adminRole1.getNameZh() +"状态更新成功";
        return ResultFactory.buildSuccessResult(message);
    }
    @PutMapping("/admin/role/select")
    public Result editRole(@RequestBody AdminRole adminRole){
        adminRoleService.addOrUpdate(adminRole);
        adminRolePermissionService.savePermChanges(adminRole.getId(), adminRole.getPerms());
        String message ="修改信息成功";
        return ResultFactory.buildSuccessResult(message);
    }
    @PostMapping("/admin/role/select")
    public Result addRole(@RequestBody AdminRole requestRole) {
        adminRoleService.editRole(requestRole);
        return ResultFactory.buildSuccessResult("修改用户成功");
    }
    @GetMapping("/admin/role/perm")
    public Result listPerms(){
        return ResultFactory.buildSuccessResult(adminPermissionService.list());
    }
    @PutMapping("/admin/role/menu")
    public Result updateRoleMenu(@RequestParam int rid, @RequestBody Map<String , List<Integer>> menuIds) {
        adminRoleMenuService.updateRoleMenu(rid,menuIds);
        return ResultFactory.buildSuccessResult("更新成功");
    }
}
