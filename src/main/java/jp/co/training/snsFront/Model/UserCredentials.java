package jp.co.training.snsFront.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class UserCredentials {

    private String userName;

    private String encodedPassword;

    private Set<String> roles;

    private Set<String> aithorities;
}
