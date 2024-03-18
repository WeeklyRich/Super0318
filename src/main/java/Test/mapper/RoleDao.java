package Test.mapper;

import Test.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhou
 * @since 2024-03-18
 */
@Mapper
public interface RoleDao extends BaseMapper<Role> {

}
