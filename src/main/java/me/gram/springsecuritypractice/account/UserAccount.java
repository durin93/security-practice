package me.gram.springsecuritypractice.account;

import com.sun.tools.javac.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserAccount extends User {

    private Account account;

    public UserAccount(Account account){
        super(account.getUsername(), account.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_"+ account.getRole())));
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
