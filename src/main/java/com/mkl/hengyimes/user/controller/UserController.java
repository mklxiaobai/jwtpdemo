package com.mkl.hengyimes.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mkl.hengyimes.common.BaseController;
import com.mkl.hengyimes.common.JsonResult;
import com.mkl.hengyimes.common.PageResult;
import com.mkl.hengyimes.common.exception.BusinessException;
import com.mkl.hengyimes.user.entity.Role;
import com.mkl.hengyimes.user.entity.User;
import com.mkl.hengyimes.user.entity.UserRole;
import com.mkl.hengyimes.user.service.RoleService;
import com.mkl.hengyimes.user.service.UserRoleService;
import com.mkl.hengyimes.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.wf.jwtp.annotation.RequiresPermissions;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

//import org.apache.catalina.servlet4preview.http.HttpServletRequest;

@Api(value = "用户管理", tags = "user")
@RestController
@RequestMapping("${api.version}/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;

    @RequiresPermissions("get:/v1/user")
    @ApiOperation(value = "查询所有用户", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "Integer", paramType = "form"),
            @ApiImplicitParam(name = "limit", value = "每页多少条", required = true, dataType = "Integer", paramType = "form"),
            @ApiImplicitParam(name = "searchKey", value = "筛选条件字段", dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "searchValue", value = "筛选条件关键字", dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "access_token", value = "令牌", required = true, dataType = "String", paramType = "form")
    })
    @GetMapping()
    public PageResult<User> list(Integer page, Integer limit, String searchKey, String searchValue) {
        if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 10;
        }
        Page<User> userPage = new Page<>(page, limit);
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        if (searchKey != null && !searchKey.trim().isEmpty() && searchValue != null && !searchValue.trim().isEmpty()) {
            wrapper.eq(searchKey, searchValue);
        }
        wrapper.orderBy(true, true,"create_time");
        userService.page(userPage, wrapper);
        List<User> userList = userPage.getRecords();
        // 关联查询role
        List<Integer> userIds = new ArrayList<>();
        for (User one : userList) {
            userIds.add(one.getUserId());
        }
        List<UserRole> userRoles = userRoleService.list(new QueryWrapper<UserRole>().in("user_id", userIds));
        List<Role> roles = roleService.list(null);
        for (User one : userList) {
            List<Role> tempUrs = new ArrayList<>();
            for (UserRole ur : userRoles) {
                if (one.getUserId().equals(ur.getUserId())) {
                    for (Role r : roles) {
                        if (ur.getRoleId().equals(r.getRoleId())) {
                            tempUrs.add(r);
                        }
                    }
                }
            }
            one.setRoles(tempUrs);
        }
        return new PageResult<>(userList, userPage.getTotal());
    }

    @RequiresPermissions("post:/v1/user")
    @ApiOperation(value = "添加用户", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "User", paramType = "form"),
            @ApiImplicitParam(name = "roleId", value = "用户角色id，多个用','分割", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "access_token", value = "令牌", required = true, dataType = "String", paramType = "form")
    })
    @PostMapping()
    public JsonResult add(User user, String roleIds) {
        String[] split = roleIds.split(",");
        user.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        user.setState(null);
        user.setEmailVerified(null);
        if (userService.save(user)) {
            List<UserRole> userRoles = new ArrayList<>();
            for (String roleId : split) {
                UserRole userRole = new UserRole();
                userRole.setRoleId(Integer.parseInt(roleId));
                userRole.setUserId(user.getUserId());
                userRoles.add(userRole);
            }
            if (!userRoleService.saveBatch(userRoles)) {
                throw new BusinessException("添加失败");
            }
            return JsonResult.ok("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    @RequiresPermissions("put:/v1/user")
    @ApiOperation(value = "修改用户", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "User", paramType = "form"),
            @ApiImplicitParam(name = "roleId", value = "用户角色id", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "access_token", value = "令牌", required = true, dataType = "String", paramType = "form")
    })
    @PutMapping()
    public JsonResult update(User user, String roleIds) {
        String[] split = roleIds.split(",");
        user.setPassword(null);
        user.setState(null);
        user.setEmailVerified(null);
        if (userService.updateById(user)) {
            List<UserRole> userRoles = new ArrayList<>();
            for (String roleId : split) {
                UserRole userRole = new UserRole();
                userRole.setRoleId(Integer.parseInt(roleId));
                userRole.setUserId(user.getUserId());
                userRoles.add(userRole);
            }
            userRoleService.remove(new QueryWrapper<UserRole>().eq("user_id", user.getUserId()));
            if (!userRoleService.saveBatch(userRoles)) {
                throw new BusinessException("修改失败");
            }
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    @RequiresPermissions("put:/v1/user/state")
    @ApiOperation(value = "修改用户状态", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "state", value = "状态：0正常，1冻结", required = true, dataType = "Integer", paramType = "form"),
            @ApiImplicitParam(name = "access_token", value = "令牌", required = true, dataType = "String", paramType = "form")
    })
    @PutMapping("/state")
    public JsonResult updateState(Integer userId, Integer state) {
        if (state == null || (state != 0 && state != 1)) {
            return JsonResult.error("state值需要在[0,1]中");
        }
        User user = new User();
        user.setUserId(userId);
        user.setState(state);
        if (userService.updateById(user)) {
            return JsonResult.ok();
        }
        return JsonResult.error();
    }

    @RequiresPermissions("put:/v1/user/psw")
    @ApiOperation(value = "修改自己密码", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldPsw", value = "原密码", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "newPsw", value = "新密码", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "access_token", value = "令牌", required = true, dataType = "String", paramType = "form")
    })
    @PutMapping("/psw")
    public JsonResult updatePsw(String oldPsw, String newPsw, HttpServletRequest request) {
        if (!DigestUtils.md5DigestAsHex(oldPsw.getBytes()).equals(userService.getById(getLoginUserId(request)).getPassword())) {
            return JsonResult.error("原密码不正确");
        }
        User user = new User();
        user.setUserId(getLoginUserId(request));
        user.setPassword(DigestUtils.md5DigestAsHex(newPsw.getBytes()));
        if (userService.updateById(user)) {
            return JsonResult.ok("修改成功");
        }
        return JsonResult.error("修改失败");
    }

    @RequiresPermissions("put:/v1/user/psw/{id}")
    @ApiOperation(value = "重置密码", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "access_token", value = "令牌", required = true, dataType = "String", paramType = "form")
    })
    @PutMapping("/psw/{id}")
    public JsonResult resetPsw(@PathVariable("id") Integer userId) {
        User user = new User();
        user.setUserId(userId);
        user.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        if (userService.updateById(user)) {
            return JsonResult.ok("重置密码成功");
        }
        return JsonResult.error("重置密码失败");
    }

    @RequiresPermissions("delete:/v1/user/{id}")
    @ApiOperation(value = "删除用户", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "access_token", value = "令牌", required = true, dataType = "String", paramType = "form")
    })
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable("id") Integer userId) {
        if (userService.removeById(userId)) {
            return JsonResult.ok("删除成功");
        }
        return JsonResult.error("删除失败");
    }
}
