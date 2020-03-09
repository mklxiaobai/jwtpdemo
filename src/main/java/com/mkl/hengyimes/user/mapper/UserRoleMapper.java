package com.mkl.hengyimes.user.mapper;

import com.mkl.hengyimes.user.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户角色 Mapper 接口
 * </p>
 *
 * @author mkl
 * @since 2019-12-19
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {
    Integer getRoleIds(Integer userId);
}
