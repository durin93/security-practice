package me.gram.springsecuritypractice.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import sun.jvm.hotspot.debugger.posix.elf.ELFException;

@Retention(RetentionPolicy.RUNTIME) //이 어노테이션을 어디까지 유지하겟냐,
@Target(ElementType.PARAMETER) // 이 어노테이션을 어디에 붙일수잇느냐
@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : account")
public @interface CurrentUser {

}
