package me.gram.springsecuritypractice.config;

import java.util.Arrays;
import java.util.List;
import me.gram.springsecuritypractice.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public SecurityExpressionHandler expressionHandler(){
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");

        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setRoleHierarchy(roleHierarchy); //롤 히어라키 설정을 위한.

        return handler;
    }


    @Autowired
    AccountService accountService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .mvcMatchers("/","/info", "/account/**").permitAll()
            .mvcMatchers("/admin").hasRole("ADMIN")
            .mvcMatchers("/user").hasRole("USER")
//            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() 이렇게 하는거보단 따로 시큐리티 적용제외하는방법을 추천한다.
            //필터체인을 다 거치기떄문에 더느리다.
            //
            .anyRequest().authenticated()
            //accessDecisionManager의 보터가사용하는 expressionHandler 커스터마이징
            .expressionHandler(expressionHandler());
        http.formLogin();
        http.httpBasic();

        //기본이 스레드로컬이라서 동일한 스레드로컬에서만공유

        //현재 스레드에서 하위 스레드로 생성하는 스레드에도 콘텍스트공유한다
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }


    //시큐리티를 적용 제외시키는법. 정적인애들 제외할떄 동적으로 처리하는 리소스들은 필터를 타야됨.
    //정적자원이지만 인가를 처리하고싶으면 저기다넣어주어라
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        //static resources 제외관련 이건 너무 별로다 .
//       web.ignoring().mvcMatchers("/favicion.ico");
    }

    /* UserDetailService 를 구현한 놈(AccountService)을 빈으로 등록해서 이렇게 직접등록 알아서 가져다씀.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService);
    }*/
}
