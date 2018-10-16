package csnt.oauth_server.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("csnt.oauth_server.mapper")
public class MybatisPlusConfig {

}