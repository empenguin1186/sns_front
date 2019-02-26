package jp.co.training.snsFront.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserProfile implements Serializable {

	private String userId;

	private String userName;

	private String comment;

	private String email;

	private String place;

	private LocalDate birthDay;

	private String imgUrl;

}
