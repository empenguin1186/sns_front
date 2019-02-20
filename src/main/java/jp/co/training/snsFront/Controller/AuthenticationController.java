package jp.co.training.snsFront.Controller;

import jp.co.training.snsFront.Model.LoginForm;
import jp.co.training.snsFront.Model.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthenticationController {

    @RequestMapping(path={"/", "/login"}, method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", error);
        }
        return "login";
    }

    @RequestMapping(path={"/", "/login"}, method = RequestMethod.POST)
    public String process(@Validated @ModelAttribute LoginForm form, BindingResult br){
        if (br.hasErrors()){
            return "login";
        }
        return "forward:/authenticate";
    }

    @RequestMapping(path="/authenticate", method = RequestMethod.POST)
    public String loginSuccess(@ModelAttribute LoginForm form, RedirectAttributes attributes) {
        attributes.addFlashAttribute("yes", "login sufccess");
        return "redirect:/profile/" + form.getUsername();
    }
}
