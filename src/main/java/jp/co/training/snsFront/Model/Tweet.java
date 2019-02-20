package jp.co.training.snsFront.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Tweet implements Serializable {

	private int tweetId;

	private String content;

	private String tweetUser;

	private String attachment;

	private int favorite;

	private int retweet;

	private int replyTo;

	private int time;
}
