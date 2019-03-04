package jp.co.training.snsFront.Service;

import jp.co.training.snsFront.Bean.UserCredentials;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "endpoint")
public class AuthenticationRealm implements UserDetailsService {

    private String uri;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            UserCredentials result = restTemplate.getForObject(uri + "user/" + username + "/credentials", UserCredentials.class);
            return User.builder()
                    .username(username)
                    .password(result.getEncodedPassword())
                    .roles(result.getRoles().toArray(new String[0]))
                    .authorities(AuthorityUtils.createAuthorityList(result.getAuthorities().toArray(new String[0])))
                    .build();

        } catch (Exception e){
            throw new UsernameNotFoundException("not found : " + username);
        }
    }
}
