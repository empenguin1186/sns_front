package jp.co.training.snsFront.Controller;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@Controller
@ConfigurationProperties(prefix = "path")
public class AuthenticationController {

    private String CERT_PROCCESSING_PATH;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", error);
        }
        return "login";
    }

    /* 動作せず */
//    @RequestMapping(path = "/login", method = RequestMethod.POST)
//    public String process(@Validated @ModelAttribute LoginForm form, BindingResult br, Bean model){
//        if (br.hasErrors()){
//            return "redirect:/login?error=InputValueIsInvalid";
//        }
//        model.addAttribute(form);
//        return "forward:" + CERT_PROCCESSING_PATH;
//    }

//    @RequestMapping(path="/authenticate", method = RequestMethod.POST)
//    public String loginSuccess(@ModelAttribute LoginForm form, RedirectAttributes attributes) {
//        attributes.addFlashAttribute("yes", "login sufccess");
//        return "redirect:/profile/" + form.getUsername();
//    }
}
