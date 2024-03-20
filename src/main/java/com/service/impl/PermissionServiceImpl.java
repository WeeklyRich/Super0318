package com.service.impl;

import com.pojo.Permission;
import com.mapper.PermissionDao;
import com.service.IPermissionService;
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
public class PermissionServiceImpl extends ServiceImpl< PermissionDao, Permission> implements IPermissionService {

}
