package com.mkl.hengyimes.user.mapper;

import com.mkl.hengyimes.user.entity.Authorities;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author mkl
 * @since 2019-12-19
 */
public interface AuthoritiesMapper extends BaseMapper<Authorities> {

    List<String> listByUserId(Integer userId);

    List<String> listByRoleId(Integer roleId);

    List<String> listByRoleIds(List<Integer> roleIds);
}
