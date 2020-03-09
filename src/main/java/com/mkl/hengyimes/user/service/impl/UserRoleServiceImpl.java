package com.mkl.hengyimes.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mkl.hengyimes.user.entity.UserRole;
import com.mkl.hengyimes.user.mapper.UserRoleMapper;
import com.mkl.hengyimes.user.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author mkl
 * @since 2019-12-19
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Autowired
    UserRoleMapper userRoleMapper;
    @Override
    public Integer[] getRoleIds(Integer userId) {
        Integer roleIds = userRoleMapper.getRoleIds(userId);
        Integer[] integers = new Integer[1];
        integers[0]=roleIds;
        return integers;
//        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
//        wrapper.eq("user_id",userId);
//        UserRole userRole = userRoleMapper.selectOne(wrapper);
//        Integer[] integers = new Integer[1];
//        integers[0]=userRole.getRoleId();
//        return integers;
//        List<UserRole> userRoles = userRoleMapper.selectList(wrapper);
//        Integer[] roleIds = new Integer[userRoles.size()];
//        for (int i = 0; i < userRoles.size(); i++) {
//            roleIds[i] = userRoles.get(i).getRoleId();
//        }
//        return roleIds;
    }
}
