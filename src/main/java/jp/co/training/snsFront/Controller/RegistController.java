package jp.co.training.snsFront.Controller;

import jp.co.training.snsFront.Model.RegistForm;
import jp.co.training.snsFront.Model.UserProfile;
import jp.co.training.snsFront.Service.MailService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

@Data
@Controller
@RequestMapping(path = "/registration")
@ConfigurationProperties(prefix = "endpoint")
public class RegistController {

    private String uri;

    @Autowired
    MailService service;

    @ModelAttribute
    public RegistForm setUpForm(){
        return new RegistForm();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getForm(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", error);
        }
        return "registration";
    }

    /* RequestMapping でpathを指定していないとcssが読み込まれない */
    @RequestMapping(method = RequestMethod.POST)
    public String postForm(@Validated RegistForm form, BindingResult result, Model model) throws URISyntaxException {
        if (result.hasErrors()){
            return getForm("error", model);
        }

        RestTemplate restTemplate = new RestTemplate();

        String userId = form.getUserId();
        String userName = form.getUserName();
        String password = new BCryptPasswordEncoder().encode(form.getEncodedPassword());
        String email = form.getEmail();
        String place = form.getPlace();
        LocalDate birthDay = LocalDate.of(form.getYear(), form.getMonth(), form.getDay());

        UserProfile user = UserProfile.builder()
                .userId(userId)
                .userName(userName)
                .encodedPassword(password)
                .comment("hoge")
                .email(email)
                .place(place)
                .birthDay(birthDay)
                .imgUrl("hoge")
                .build();

        URI path = new URI(uri + "user/post");

        RequestEntity<UserProfile> entity = RequestEntity
                .post(path)
                .body(user);
        ResponseEntity<String> res = restTemplate.exchange(entity, String.class);

        service.send("ようこそSNSへ", "クリックしてね", email);

        return "login";
    }
}
