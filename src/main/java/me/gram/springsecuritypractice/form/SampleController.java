package me.gram.springsecuritypractice.form;

import java.security.Principal;
import me.gram.springsecuritypractice.account.AccountContext;
import me.gram.springsecuritypractice.account.AccountRepository;
import me.gram.springsecuritypractice.common.SecurityLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

    @Autowired SampleService sampleService;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/info")
    public String info(Model model){
        model.addAttribute("message","Info");
        return "info";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal){
        AccountContext.setAccount(accountRepository.findByUsername(principal.getName()));
        sampleService.dashboard();
        model.addAttribute("message","Hello " + principal.getName());
        return "dashboard";
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal){
        model.addAttribute("message","Hello Admin "+ principal.getName());
        return "admin";
    }

    @GetMapping("/user")
    public String user(Model model, Principal principal){
        model.addAttribute("message","Hello user "+ principal.getName());
        return "user";
    }

    @GetMapping("/")
    public String index(Model model, Principal principal){
        if(principal == null){
            model.addAttribute("message","Hello Spring Security");
        }
        else {
            model.addAttribute("message", "Hello "+principal.getName());
        }
        return "index";
    }

    @GetMapping("/async-service")
    @ResponseBody
    public String asyncService(){
        SecurityLogger.log("Mvc, before async service");
        sampleService.asyncService();
        SecurityLogger.log("Mvc, after async service");
        return "Async Serivce";
    }

}
