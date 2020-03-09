package com.mkl.hengyimes.user.service.impl;

import com.mkl.hengyimes.user.entity.RoleAuthorities;
import com.mkl.hengyimes.user.mapper.RoleAuthoritiesMapper;
import com.mkl.hengyimes.user.service.RoleAuthoritiesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色权限 服务实现类
 * </p>
 *
 * @author mkl
 * @since 2019-12-19
 */
@Service
public class RoleAuthoritiesServiceImpl extends ServiceImpl<RoleAuthoritiesMapper, RoleAuthorities> implements RoleAuthoritiesService {

    @Autowired
    RoleAuthoritiesMapper roleAuthoritiesMapper;
    @Override
    public void deleteTrash() {
        roleAuthoritiesMapper.deleteTrash();
    }
}
