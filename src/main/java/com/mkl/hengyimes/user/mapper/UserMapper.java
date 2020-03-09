package com.mkl.hengyimes.user.mapper;

import com.mkl.hengyimes.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author mkl
 * @since 2019-12-19
 */
public interface UserMapper extends BaseMapper<User> {

    User getByUsername(String username);
}
