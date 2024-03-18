package Test.service.impl;

import Test.pojo.Provider;
import Test.mapper.ProviderDao;
import Test.service.IProviderService;
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
public class ProviderServiceImpl extends ServiceImpl<ProviderDao, Provider> implements IProviderService {

}
