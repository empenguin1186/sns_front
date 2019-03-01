package jp.co.training.snsFront.Controller;

import jp.co.training.snsFront.Service.TimelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;

@RequestMapping("timeline")
@Controller
public class TimelineController {

	@Autowired
  RestTemplate restTemplate;

	@Autowired
	TimelineService timelineService;

	@RequestMapping(method=RequestMethod.GET)
	String showTimeline(@AuthenticationPrincipal User user, Model model) throws URISyntaxException {
		model.addAttribute("tweets", timelineService.getTimeline(user));
		return "timeline";
	}

}
