package jp.co.training.snsFront.Controller;

import jp.co.training.snsFront.Model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@RequestMapping("profile")
@Controller
public class UserProfileController {

	private static RestTemplate restTemplate = new RestTemplate();

	@RequestMapping(value="{userId}", method=RequestMethod.GET)
	String getUserProfile(@PathVariable String userId, Model model){
		User user = restTemplate.getForObject("http://localhost:8080/user/"+ userId +"/details", User.class);
		model.addAttribute("user", user);
		return "profile";
	}

}
