package com.mkl.hengyimes.user.mapper;

import com.mkl.hengyimes.user.entity.RoleAuthorities;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 角色权限 Mapper 接口
 * </p>
 *
 * @author mkl
 * @since 2019-12-19
 */
public interface RoleAuthoritiesMapper extends BaseMapper<RoleAuthorities> {

    void deleteTrash();
}
