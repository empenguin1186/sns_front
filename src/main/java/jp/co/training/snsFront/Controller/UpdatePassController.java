package jp.co.training.snsFront.Controller;

import jp.co.training.snsFront.Bean.UpdatePassForm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@Controller
@RequestMapping(path = "/update")
@ConfigurationProperties(prefix = "endpoint")
public class UpdatePassController {

    private String uri;

    @ModelAttribute
    public UpdatePassForm setUpForm() {
        return new UpdatePassForm();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getForm(@RequestParam(value = "message", required = false) String msg, Model model) {
        if (msg != null){
            model.addAttribute("sendMessage", msg);
        }
        return "updatePass";
    }

//    @RequestMapping(method = RequestMethod.POST)
//    public String postForm(@Validated UpdatePassForm form, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return getForm("error", model);
//        }
//    }
}
