package me.gram.springsecuritypractice.account;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.security.test.context.support.WithMockUser;

@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(username = "durin",roles = "ADMIN")
public @interface WithAdmin {

}
