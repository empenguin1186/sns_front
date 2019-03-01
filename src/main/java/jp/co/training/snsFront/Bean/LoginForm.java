package jp.co.training.snsFront.Bean;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Setter
@Getter
public class LoginForm implements Serializable {

    private static final long serialVersionUID = -4165914705498462325L;

    @NotNull
    @Size(min = 1, max = 50)
    String username;

    @NotNull
    @Size(min = 4, max = 50)
    String password;

}
