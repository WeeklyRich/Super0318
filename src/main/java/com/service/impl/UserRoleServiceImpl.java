package com.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mapper.UserRoleDao;
import com.pojo.UserRole;
import com.service.IUserRoleService;
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
public class UserRoleServiceImpl extends ServiceImpl< UserRoleDao, UserRole> implements IUserRoleService {

}
