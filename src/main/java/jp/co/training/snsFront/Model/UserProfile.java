package jp.co.training.snsFront.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserProfile implements Serializable {

	private String userId;

	private String userName;

	private String comment;

	private String email;

	private String place;

	private long birthDay;

	private String imgUrl;

}
