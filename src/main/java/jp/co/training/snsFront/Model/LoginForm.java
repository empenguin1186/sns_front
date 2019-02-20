package jp.co.training.snsFront.Model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@Setter
@Getter
public class LoginForm implements Serializable {

    private static final long serialVersionUID = -4165914705498462325L;

    @NotEmpty
    String username;

    @NotEmpty
    String password;


}
