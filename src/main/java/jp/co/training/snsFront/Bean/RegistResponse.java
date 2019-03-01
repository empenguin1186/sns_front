package jp.co.training.snsFront.Bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegistResponse {

    @JsonProperty("status_code")
    private int statusCode;

    @JsonProperty("message")
    private String message;
}