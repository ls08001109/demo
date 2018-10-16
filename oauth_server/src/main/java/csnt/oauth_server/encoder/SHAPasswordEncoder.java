package csnt.oauth_server.encoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

public class SHAPasswordEncoder implements PasswordEncoder {

    /**
     * 对密码进行加密并返回
     */
    public String encode(CharSequence rawPassword) {

        String encPassword = rawPassword.toString();

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");

            digest.reset();

            byte password[] = digest.digest(encPassword.getBytes("UTF-8"));

            String hash = (new BASE64Encoder()).encode(password);

            return hash;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 验证密码是否正确
     */
    public boolean matches(CharSequence rawPassword, String encodedPassword) {

        return encode(rawPassword).equals(encodedPassword);
    }
}