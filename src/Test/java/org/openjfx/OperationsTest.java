package org.openjfx;

import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.List;

class OperationsTest {
    private List<Account> accountList= new ArrayList<>();
<<<<<<< Updated upstream
    private List<String> operationList= new ArrayList<>();
=======
    private List<String> stringList = new ArrayList<>();
>>>>>>> Stashed changes
    private  Operations operations;

    @BeforeEach
    void init() {
<<<<<<< Updated upstream
        //accountList.add(account);
        //accountList.add(account2);
        operations= new Operations();
=======
        operations = new Operations();
        accountList.add(new Account(1, 100, new Bank("Swedbank"), new Person("Java", "Java", 35)));
        accountList.add( new Account(2, 200, new Bank("HSBC"), new Person("Python", "Python", 25)));
>>>>>>> Stashed changes
    }


    @Test
    @Order(2)
    @DisplayName("Deposit test")
<<<<<<< Updated upstream
    void deposit(){
        Account account = new Account(1, 100, new Bank("Swedbank"),  new Person("Java", "Java", 35));
        Account account2 = new Account(2, 200,  new Bank("HSBC"), new Person("Python", "Python", 25));
        accountList.add(account);
        accountList.add(account2);
        int index= accountList.indexOf(accountList.stream()
                .filter(e -> e.getId() == 1)
                .collect(Collectors.toList()).get(0));

        operations.deposit(1, 500, accountList, "500 sek is added", operationList);
        assertEquals(600, accountList.get(index).getBalance() );
        System.out.println("Balance after deposit = "+ accountList.get(index).getBalance());
=======
        void depositTest(){
        operations.deposit(1, 500, accountList, "Deposit 500", stringList );
        Assertions.assertEquals(600, accountList.get(0).getBalance());
>>>>>>> Stashed changes
    }

    @Test
    @Order(3)
    @DisplayName("Withdraw test")
<<<<<<< Updated upstream
    void withdraw(){
        Account account = new Account(1, 100, new Bank("Swedbank"),  new Person("Java", "Java", 35));
        Account account2 = new Account(2, 200,  new Bank("HSBC"), new Person("Python", "Python", 25));
        accountList.add(account);
        accountList.add(account2);

        int index= accountList.indexOf(accountList.stream()
                .filter(e -> e.getId() == 1)
                .collect(Collectors.toList()).get(0));

        operations.withdraw(1, 20, accountList, "20 sek witdrawn", operationList);
        assertEquals(80, accountList.get(index).getBalance());
        System.out.println(" Balance after withdrawal = "+accountList.get(index).getBalance());
=======
    void withdrawTest(){
            operations.withdraw(2, 100, accountList, "WithDraw 100", stringList );
            Assertions.assertEquals(100, accountList.get(1).getBalance());
>>>>>>> Stashed changes
    }


    @Test
    @Order(4)
    @DisplayName("Transfer test")
    void transfer(){
<<<<<<< Updated upstream
        Account account = new Account(1, 100, new Bank("Swedbank"),  new Person("Java", "Java", 35));
        Account account2 = new Account(2, 200,  new Bank("HSBC"), new Person("Python", "Python", 25));
        accountList.add(account);
        accountList.add(account2);
        int indexAccount1= accountList.indexOf(accountList.stream()
                .filter(e -> e.getId() == 1)
                .collect(Collectors.toList()).get(0));
        int indexAccount2= accountList.indexOf(accountList.stream()
                .filter(e -> e.getId() == 2)
                .collect(Collectors.toList()).get(0));

        operations.transfer(1, 2 , 50, accountList, "50 sek transfer from acc. 1 to acc. 2", operationList);
        assertAll(
                ()-> assertEquals(50,accountList.get(indexAccount1).getBalance() ),
                ()-> assertEquals(250,accountList.get(indexAccount2).getBalance() )
        );
        System.out.println(" Balance1 after transfer = " + accountList.get(indexAccount1).getBalance());
        System.out.println(" Balance2 after transfer = " + accountList.get(indexAccount2).getBalance());
=======
        operations.transfer(1, 2,100, accountList, "WithDraw 100", stringList );
        Assertions.assertEquals(0, accountList.get(0).getBalance());
        Assertions.assertEquals(300, accountList.get(1).getBalance());
>>>>>>> Stashed changes
    }

}
