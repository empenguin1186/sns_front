package jp.co.training.snsFront.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class RegistForm {

    @NotEmpty
    @Size(min=4, max=20)
    private String userId;

    @NotEmpty
    @Size(min=4, max=20)
    private String userName;

    @NotEmpty
    @Size(min=4, max=71)
    private String encodedPassword;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String place;

    @NotNull
    @Max(2019)
    private int year;

    @NotNull
    @Min(1)
    @Max(12)
    private int month;

    @NotNull
    @Min(1)
    @Max(31)
    private int day;

}
