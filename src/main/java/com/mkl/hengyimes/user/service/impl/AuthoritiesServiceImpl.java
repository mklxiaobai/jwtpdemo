package com.mkl.hengyimes.user.service.impl;

import com.mkl.hengyimes.user.entity.Authorities;
import com.mkl.hengyimes.user.mapper.AuthoritiesMapper;
import com.mkl.hengyimes.user.service.AuthoritiesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author mkl
 * @since 2019-12-19
 */
@Service
public class AuthoritiesServiceImpl extends ServiceImpl<AuthoritiesMapper, Authorities> implements AuthoritiesService {

    @Override
    public List<String> listByUserId(Integer userId) {
        return baseMapper.listByUserId(userId);
    }

    @Override
    public List<String> listByRoleIds(List<Integer> roleIds) {
        if (roleIds == null || roleIds.size() == 0) {
            return new ArrayList<>();
        }
        return baseMapper.listByRoleIds(roleIds);
    }

    @Override
    public List<String> listByRoleId(Integer roleId) {
        return baseMapper.listByRoleId(roleId);
    }
}
