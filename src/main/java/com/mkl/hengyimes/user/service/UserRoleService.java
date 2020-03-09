package com.mkl.hengyimes.user.service;

import com.mkl.hengyimes.user.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户角色 服务类
 * </p>
 *
 * @author mkl
 * @since 2019-12-19
 */
public interface UserRoleService extends IService<UserRole> {
    Integer[] getRoleIds(Integer userId);

}
