package csnt.oauth_server.service;

import csnt.oauth_server.mapper.BaseUserMapper;
import csnt.oauth_server.pojo.BaseUser;
import csnt.oauth_server.exception.OauthException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BaseUserMapper userMapper;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String platform = httpServletRequest.getParameter("platform");

        BaseUser baseUser = userMapper.get(username);

        if (baseUser == null) {
            logger.debug("找不到该用户 用户名:{}", username);
            throw new OauthException("找不到该用户！");
        }

        if(baseUser.getStatus()==2)
        {
            logger.debug("用户被禁用，无法登陆 用户名:{}", username);
            throw new OauthException("用户被禁用！");
        }

        return new User(baseUser.getUserName(), baseUser.getUserPassword(), true, true, true, true, new ArrayList<GrantedAuthority>());
    }
}
