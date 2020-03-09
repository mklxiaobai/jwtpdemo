package com.mkl.hengyimes.user.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.mkl.hengyimes.user.entity.User;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author mkl
 * @since 2019-12-19
 */
public interface UserService extends IService<User> {

    User getByUsername(String username);
}
