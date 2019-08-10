package me.gram.springsecuritypractice.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    //인프런 강좌예제임 이렇게하면안됨 ㅋㅋ
    @GetMapping("/account/{role}/{username}/{password}")
    public Account createAccount(@ModelAttribute Account account){
        return accountService.createNew(account);
    }

}
