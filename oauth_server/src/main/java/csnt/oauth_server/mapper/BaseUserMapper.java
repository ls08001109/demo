
package csnt.oauth_server.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import csnt.oauth_server.pojo.BaseUser;
import org.apache.ibatis.annotations.*;

public interface BaseUserMapper extends BaseMapper<BaseUser> {

    @Select("select * from base_user where user_name = #{username}")
    BaseUser get(@Param("username") String username);

}