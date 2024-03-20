package com.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mapper.BillDao;
import com.pojo.Bill;
import com.service.IBillService;
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
public class BillServiceImpl extends ServiceImpl<BillDao, Bill> implements IBillService {

}
