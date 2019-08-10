package me.gram.springsecuritypractice.form;

import java.security.Principal;
import me.gram.springsecuritypractice.account.AccountContext;
import me.gram.springsecuritypractice.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

}
