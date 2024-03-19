package com.service.impl;

import com.pojo.UserRole;
import com.mapper.UserRoleDao;
import com.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
