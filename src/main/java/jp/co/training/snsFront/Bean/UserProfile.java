package jp.co.training.snsFront.Bean;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
public class UserProfile implements Serializable {

	private String userId;

	private String userName;

	private String encodedPassword;

	private String comment;

	private String email;

	private String place;

	private LocalDate birthDay;

	private String imgUrl;

}
