package org.openjfx;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openjfx.Account;
import org.openjfx.Bank;

public class AccountTest {
    @Test
    public void gettersTest(){
        Account a = new Account(99, 120, new Bank("b1"), new Person("fn", "ln", 30));

        Assertions.assertEquals(99, a.getId());
        Assertions.assertEquals("b1", a.getBank().getName());
        Assertions.assertEquals("fn", a.getPerson().getFirstName());
        Assertions.assertEquals(120, a.getBalance());
    }

    @Test
    public void settersTest(){
        Account a = new Account(99, 120, new Bank("b1"), new Person("fn", "ln", 30));
        a.setBalance(130);

        Assertions.assertEquals(130, a.getBalance());
    }

}
