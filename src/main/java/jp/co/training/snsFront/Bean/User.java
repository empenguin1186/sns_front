package jp.co.training.snsFront.Bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class User {

	@JsonProperty("user")
	private UserProfile userProfile;

	@JsonProperty("tweets")
	private List<Tweet> tweets;

}
