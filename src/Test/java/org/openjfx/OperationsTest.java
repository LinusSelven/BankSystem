package org.openjfx;

import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertAll;

class OperationsTest {
    private List<Account> accountList= new ArrayList<>();
    private List<String> operationList= new ArrayList<>();
    private  Operations operations;

    @BeforeEach
    void init() {
        operations= new Operations();
        accountList.add(new Account(1, 100, new Bank("Swedbank"),  new Person("Java", "Java", 35)));
        accountList.add(new Account(2, 200,  new Bank("HSBC"), new Person("Python", "Python", 25)));
    }


    @Test
    @Order(2)
    @DisplayName("Deposit test")
    void deposit(){
        operations.deposit(1, 500, accountList, "500 sek is added", operationList);
        Assertions.assertEquals(600, accountList.get(0).getBalance() );
        System.out.println("Balance after deposit = "+ accountList.get(0).getBalance());
    }

    @Test
    @Order(3)
    @DisplayName("Withdraw test")
    void withdraw(){
        operations.withdraw(2, 20, accountList, "20 sek witdrawn", operationList);
            Assertions.assertEquals(180, accountList.get(1).getBalance());
        System.out.println(" Balance after withdrawal = "+accountList.get(1).getBalance());
    }


    @Test
    @Order(4)
    @DisplayName("Transfer test")
    void transfer(){
        operations.transfer(1, 2 , 50, accountList, "50 sek transfer from acc. 1 to acc. 2", operationList);
        assertAll(
                ()-> Assertions.assertEquals(50,accountList.get(0).getBalance() ),
                ()-> Assertions.assertEquals(250.0f,accountList.get(1).getBalance() )
        );
        System.out.println(" Balance1 after transfer = " + accountList.get(0).getBalance());
        System.out.println(" Balance2 after transfer = " + accountList.get(1).getBalance());

    }

}
