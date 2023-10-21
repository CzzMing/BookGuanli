package com.buba.controller;

import com.buba.pojo.User;
import com.buba.result.Result;
import com.buba.result.ResultFactory;
import com.buba.service.AdminUserRoleService;
import com.buba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UsersController {

    @Autowired
    UserService userService;
    @Autowired
    AdminUserRoleService adminUserRoleService;


    @GetMapping("/admin/user/select")
    public Result listUsers() {
        return ResultFactory.buildSuccessResult(userService.list());
    }
    @PutMapping("/admin/user/status")
    public Result updateUserStatus(@RequestBody @Valid User requestUser) {
        userService.updateUserStatus(requestUser);
        return ResultFactory.buildSuccessResult("用户状态更新成功");
    }

    @PutMapping("/admin/user/password")
    public Result resetPassword(@RequestBody @Validated User requestUser) {
        userService.resetPassword(requestUser);
        return ResultFactory.buildSuccessResult("重置密码成功");
    }

    @PutMapping("/admin/user/select")
    public Result editUser(@RequestBody @Valid User requestUser) {
        userService.editUser(requestUser);
        return ResultFactory.buildSuccessResult("修改用户信息成功");
    }
}
