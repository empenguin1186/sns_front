package jp.co.training.snsFront.Controller;

import jp.co.training.snsFront.Model.Tweet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RequestMapping("timeline")
@Controller
public class TimelineController {

	private static RestTemplate restTemplate = new RestTemplate();

	@Value("${endpoint.uri}")
  String URI;

	@RequestMapping(method=RequestMethod.GET)
	String showTimeline(@AuthenticationPrincipal User user, Model model) throws URISyntaxException {
		String userId = user.getUsername();

		RequestEntity requestEntity = RequestEntity
						.get(new URI(URI + "tweets/"+userId+"/follow"))
						.build();
		ResponseEntity<List<Tweet>> responseEntity = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<List<Tweet>>(){});
		List<Tweet> result = responseEntity.getBody();
		model.addAttribute("tweets", result);
		return "timeline";
	}

}
