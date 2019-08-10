package me.gram.springsecuritypractice.account;

import org.springframework.stereotype.Component;

public class AccountContext {

    private static final ThreadLocal<Account> ACCOUNT_THREAD_LOCAL = new ThreadLocal<>();

    public static void setAccount(Account account){
        ACCOUNT_THREAD_LOCAL.set(account);
    }

    public static Account getAccount(){
        return ACCOUNT_THREAD_LOCAL.get();
    }

}
