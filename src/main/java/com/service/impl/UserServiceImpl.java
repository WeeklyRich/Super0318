package com.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mapper.UserDao;
import com.pojo.User;
import com.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhou
 * @since 2024-03-18
 */
@Service
public class UserServiceImpl extends ServiceImpl< UserDao, User> implements IUserService {

    @Override
    public User getUserById ( Long id ) {
        return null;
    }


}
