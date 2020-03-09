package com.mkl.hengyimes.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mkl.hengyimes.common.BaseController;
import com.mkl.hengyimes.common.CommonResult;
import com.mkl.hengyimes.common.JsonResult;
import com.mkl.hengyimes.common.utils.StringUtil;
import com.mkl.hengyimes.user.entity.Authorities;
import com.mkl.hengyimes.user.entity.Menu;
import com.mkl.hengyimes.user.entity.User;
import com.mkl.hengyimes.user.service.AuthoritiesService;
import com.mkl.hengyimes.user.service.MenuService;
import com.mkl.hengyimes.user.service.UserRoleService;
import com.mkl.hengyimes.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wf.jwtp.provider.Token;
import org.wf.jwtp.provider.TokenStore;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

//import org.apache.catalina.servlet4preview.http.HttpServletRequest;

@Api(value = "个人信息", tags = "main")
@RequestMapping("/")
@Controller
public class MainController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private AuthoritiesService authoritiesService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserRoleService userRoleService;

    /*@ApiIgnore
    @RequestMapping({"/", "/index"})
    public String index() {
        return "redirect:index.html";
    }*/

    @ResponseBody
    @ApiOperation(value = "获取个人信息")
    @ApiImplicitParam(name = "access_token", value = "令牌", required = true, dataType = "String", paramType = "query")
    @GetMapping("user/info")
    public JsonResult userInfo(HttpServletRequest request) {
        User user = userService.getById(getLoginUserId(request));
        List<Authorities> auths = new ArrayList<>();
        for (String auth : getLoginToken(request).getPermissions()) {
            Authorities t = new Authorities();
            t.setAuthority(auth);
            auths.add(t);
        }
        user.setAuthorities(auths);
        return JsonResult.ok().put("user", user);
    }

    @ResponseBody
    @ApiOperation(value = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "账号", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "form")
    })
    @PostMapping("user/login")
    public CommonResult login(String username, String password) {
        User user = userService.getByUsername(username);
        if (user == null) {
            return new CommonResult().failed("账号不存在");
        } else if (!user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
            return new CommonResult().failed("密码错误");
        } else if (user.getState() != 0) {
            return new CommonResult().failed("账号被锁定");
        }
        Integer[] ints=userRoleService.getRoleIds(user.getUserId());
        String[] roles = arrayToString(ints);
        String[] permissions = listToArray(authoritiesService.listByUserId(user.getUserId()));
        Token token = tokenStore.createNewToken(String.valueOf(user.getUserId()), permissions, roles);
        HashMap<String, String> map = new HashMap<>();
        map.put("token",token.getAccessToken());
        return new CommonResult().success(map);
//        return JsonResult.ok("登录成功").put("access_token", token.getAccessToken());
//        return JsonResult.ok();
    }

    @ResponseBody
    @ApiOperation(value = "获取所有菜单")
    @ApiImplicitParam(name = "access_token", value = "令牌", required = true, dataType = "String", paramType = "query")
    @GetMapping("user/menu")
    public JsonResult userMenu(HttpServletRequest request) {
        // 获取当前用户的权限
        Token token = getLoginToken(request);
        String[] auths = token.getPermissions();
        // 查询所有的菜单
        List<Menu> menus = menuService.list(new QueryWrapper<Menu>().orderBy(true, true,"sort_number"));
        // 移除没有权限的菜单
        Iterator<Menu> iterator = menus.iterator();
        while (iterator.hasNext()) {
            Menu next = iterator.next();
            boolean haveAuth = false;
            for (String auth : auths) {
                if (StringUtil.isBlank(next.getAuthority()) || next.getAuthority().equals(auth)) {
                    haveAuth = true;
                }
            }
            if (!haveAuth) {
                iterator.remove();
            }
        }
        // 去除空的目录
        iterator = menus.iterator();
        while (iterator.hasNext()) {
            Menu next = iterator.next();
            if (StringUtil.isBlank(next.getMenuUrl())) {
                boolean haveSub = false;
                for (Menu t : menus) {
                    if (t.getParentId() == next.getMenuId()) {
                        haveSub = true;
                        break;
                    }
                }
                if (!haveSub) {
                    iterator.remove();
                }
            }
        }
        return JsonResult.ok().put("data", getMenuTree(menus, -1));
    }

    // 递归转化树形菜单
    private List<Map<String, Object>> getMenuTree(List<Menu> menus, Integer parentId) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < menus.size(); i++) {
            Menu temp = menus.get(i);
            if (parentId.intValue() == temp.getParentId().intValue()) {
                Map<String, Object> map = new HashMap<>();
                map.put("name", temp.getMenuName());
                map.put("icon", temp.getMenuIcon());
                map.put("url", StringUtil.isBlank(temp.getMenuUrl()) ? "javascript:;" : temp.getMenuUrl());
                map.put("subMenus", getMenuTree(menus, menus.get(i).getMenuId()));
                list.add(map);
            }
        }
        return list;
    }

    private String[] listToArray(List<String> list) {
        String[] strs = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strs[i] = list.get(i);
        }
        return strs;
    }


    private String[] arrayToString(Object[] objs) {
        String[] strs = new String[objs.length];
        for (int i = 0; i < objs.length; i++) {
            strs[i] = String.valueOf(objs[i]);
        }
        return strs;
    }

}
