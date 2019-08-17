package me.gram.springsecuritypractice.common;

import me.gram.springsecuritypractice.account.Account;
import me.gram.springsecuritypractice.account.AccountService;
import me.gram.springsecuritypractice.book.Book;
import me.gram.springsecuritypractice.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DefaultDataGenerator implements ApplicationRunner {

    @Autowired
    AccountService accountService;

    @Autowired
    BookRepository bookRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //durin - spring
        //gram - hibernate

        Account durin = createUser("durin");
        Account gram = createUser("gram");

        createBook("spring",durin);
        createBook("hibernate",gram);

    }

    private void createBook(String title, Account durin) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(durin);
        bookRepository.save(book);
    }

    private Account createUser(String username) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword("123");
        account.setRole("USER");
        return accountService.createNew(account);
    }
}
