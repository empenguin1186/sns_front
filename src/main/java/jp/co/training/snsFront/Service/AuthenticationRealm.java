package jp.co.training.snsFront.Service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationRealm implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("kodaira".equals(username)){
            return new User(username, "hogehoge", AuthorityUtils.createAuthorityList("ROLE_USER"));
        }
        throw new UsernameNotFoundException("not found : " + username);
    }
}
