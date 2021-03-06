package jp.co.training.snsFront;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableOAuth2Sso
public class SnsFrontApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnsFrontApplication.class, args);
	}

}

