package me.gram.springsecuritypractice.form;

import java.util.Collection;
import me.gram.springsecuritypractice.account.Account;
import me.gram.springsecuritypractice.account.AccountContext;
import me.gram.springsecuritypractice.common.SecurityLogger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

    public void dashboard() {
        Account account = AccountContext.getAccount();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();//사용자
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();//권한
        Object credentials = authentication.getCredentials();
        boolean authenticated = authentication.isAuthenticated();
    }



    //새로운 스레드다
    //Async를 사용한곳에서는 시큐리티컨텍스트 공유가안됨.
    //이걸 해결하려면
    @Async
    public void asyncService() {
        SecurityLogger.log("Async Service");
        System.out.println("Asyn service called");

    }
}
