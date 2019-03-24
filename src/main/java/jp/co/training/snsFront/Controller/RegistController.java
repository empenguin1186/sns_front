package jp.co.training.snsFront.Controller;

import jp.co.training.snsFront.Bean.RegistForm;
import jp.co.training.snsFront.Service.MailService;
import jp.co.training.snsFront.Service.RegistService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;

@Data
@Controller
@RequestMapping(path = "/registration")
public class RegistController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RegistService registService;

    @Autowired
    MailService mailService;

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
        registService.registUser(form);
        mailService.send("ようこそSNSへ", "クリックしてね", form.getEmail());
        return "login";
    }
}
