package com.mkl.hengyimes.user.service;

import com.mkl.hengyimes.user.entity.RoleAuthorities;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色权限 服务类
 * </p>
 *
 * @author mkl
 * @since 2019-12-19
 */
public interface RoleAuthoritiesService extends IService<RoleAuthorities> {

    void deleteTrash();
}
