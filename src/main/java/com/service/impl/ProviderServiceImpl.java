package com.service.impl;

import com.pojo.Provider;
import com.mapper.ProviderDao;
import com.service.IProviderService;
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
public class ProviderServiceImpl extends ServiceImpl< ProviderDao, Provider> implements IProviderService {

}
