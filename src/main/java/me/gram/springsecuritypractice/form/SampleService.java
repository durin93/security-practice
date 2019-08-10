package me.gram.springsecuritypractice.form;

import java.util.Collection;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class SampleService {

    public void dashboard() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();//사용자
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();//권한
        Object credentials = authentication.getCredentials();
        boolean authenticated = authentication.isAuthenticated();
    }
}
