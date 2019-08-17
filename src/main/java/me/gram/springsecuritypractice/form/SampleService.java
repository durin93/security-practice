package me.gram.springsecuritypractice.form;

import java.util.Collection;
import javax.annotation.security.RolesAllowed;
import me.gram.springsecuritypractice.account.Account;
import me.gram.springsecuritypractice.account.AccountContext;
import me.gram.springsecuritypractice.common.SecurityLogger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

    @Secured("ROLE_USER")
//    @Secured("ROLE_USER","ROLE_ADMIN")
//    @RolesAllowed({"ROLE_USER","ROLE_ADMIN}) //
    //@PreAuthorize("hasRole('USER') or hasRole('ADMIN')") //이 세개는 이 메서드를 호출하기 이전에 검사를한다.
    //@PostAuthorize() //메서드 실행이후
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
