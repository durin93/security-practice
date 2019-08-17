package me.gram.springsecuritypractice.form;

import static org.junit.Assert.*;

import me.gram.springsecuritypractice.account.Account;
import me.gram.springsecuritypractice.account.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleServiceTest {

    @Autowired
    SampleService sampleService;

    @Autowired
    AccountService accountService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Test
    @WithMockUser
    public void dashboard(){
        /*Account account = new Account();
        account.setRole("USER");
        account.setUsername("durin");
        account.setPassword("123");
        accountService.createNew(account);
        UserDetails userDetails  = accountService.loadUserByUsername(account.getUsername());

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, "123");
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);*/
        sampleService.dashboard();
    }

}