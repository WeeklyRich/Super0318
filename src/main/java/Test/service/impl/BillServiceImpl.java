package Test.service.impl;

import Test.pojo.Bill;
import Test.mapper.BillDao;
import Test.service.IBillService;
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
