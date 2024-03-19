package com.service.impl;

import com.pojo.Bill;
import com.mapper.BillDao;
import com.service.IBillService;
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
public class BillServiceImpl extends ServiceImpl<BillDao, Bill> implements IBillService {

}
