package com.mkl.hengyimes.user.service;

import com.mkl.hengyimes.user.entity.Authorities;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author mkl
 * @since 2019-12-19
 */
public interface AuthoritiesService extends IService<Authorities> {
    List<String> listByUserId(Integer userId);

    List<String> listByRoleIds(List<Integer> roleId);

    List<String> listByRoleId(Integer roleId);

}
