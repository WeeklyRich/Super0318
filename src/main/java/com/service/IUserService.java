package com.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pojo.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhou
 * @since 2024-03-18
 */
public interface IUserService extends IService<User> {

    User getUserById ( Long id );


}
