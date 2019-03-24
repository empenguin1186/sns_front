package jp.co.training.snsFront.Bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PassReq {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("encoded_password")
    private String encodedPassword;
}
