package me.gram.springsecuritypractice.form;

import java.security.Principal;
import me.gram.springsecuritypractice.account.Account;
import me.gram.springsecuritypractice.account.AccountContext;
import me.gram.springsecuritypractice.account.AccountRepository;
import me.gram.springsecuritypractice.account.UserAccount;
import me.gram.springsecuritypractice.book.BookRepository;
import me.gram.springsecuritypractice.common.CurrentUser;
import me.gram.springsecuritypractice.common.SecurityLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

    @Autowired SampleService sampleService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BookRepository bookRepository;

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
        model.addAttribute("books",bookRepository.findCurrentUserBooks());
        return "user";
    }

    @GetMapping("/")
    public String index(Model model, @CurrentUser Account account){
//    public String index(Model model, @AuthenticationPrincipal UserAccount userAccount){
//    public String index(Model model, Principal principal){
        if(account == null){
            model.addAttribute("message","Hello Spring Security");
        }
        else {
            model.addAttribute("message", "Hello "+account.getUsername());
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
