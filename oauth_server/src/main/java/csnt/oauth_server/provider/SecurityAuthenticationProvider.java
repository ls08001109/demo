package csnt.oauth_server.provider;

import csnt.oauth_server.exception.OauthException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;


public class SecurityAuthenticationProvider extends DaoAuthenticationProvider {

    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) {

        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");

            throw new OauthException("没有输入密码");
        }

        String presentedPassword = authentication.getCredentials().toString();

        if (!super.getPasswordEncoder().matches(presentedPassword, userDetails.getPassword())) {
            logger.debug("Authentication failed: password does not match stored value");

            throw new OauthException("密码错误");
        }




    }
}