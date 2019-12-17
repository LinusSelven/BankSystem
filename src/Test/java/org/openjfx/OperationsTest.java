package org.openjfx;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;
class OperationsTest {
    //private Account account = new Account(1, 100, new Person("Java", "Java", 35), new Bank("Swedbank"));
    //private Account account2 = new Account(2, 200, new Person("Python", "Python", 25), new Bank("HSBC"));
    private List<Account> accountList= new ArrayList<>();
    private List<String> operationList= new ArrayList<>();
    private Operations operations;
    private int index_Id_1;
    private int index_Id_2;

    @BeforeEach
    void init() {
        //accountList.add(account);
        //accountList.add(account2);
        operations= new Operations();
        Account account = new Account(1, 100, new Bank("Swedbank"), new Person("Java", "Java", 35));
        Account account2 = new Account(2, 200, new Bank("HSBC"), new Person("Python", "Python", 25));
        accountList.add(account);
        accountList.add(account2);
        int index_Id_1= accountList.indexOf(accountList.stream()
                .filter(e -> e.getId() == 1)
                .collect(Collectors.toList()).get(0));
        int index_Id_2= accountList.indexOf(accountList.stream()
                .filter(e -> e.getId() == 2)
                .collect(Collectors.toList()).get(0));
    }
    @Test
    @Order(2)
    @DisplayName("Deposit test")
    void deposit(){
        operations.deposit(1, 500, accountList, "500 sek is added", operationList);
        assertEquals(600, accountList.get(index_Id_1).getBalance() );
        System.out.println("Balance after deposit = "+ accountList.get(index_Id_1).getBalance());
    }
    @Test
    @Order(3)
    @DisplayName("Withdraw test")
    void withdraw(){
        operations.withdraw(1, 20, accountList, "20 sek witdrawn", operationList);
        assertEquals(80, accountList.get(index_Id_1).getBalance());
        System.out.println(" Balance after withdrawal = "+accountList.get(index_Id_1).getBalance());
    }
    @Test
    @Order(4)
    @DisplayName("Transfer test")
    void transfer(){

        operations.transfer(1, 2 , 50, accountList, "50 sek transfered from acc. 1 to acc. 2", operationList);
        assertAll(
                ()-> assertEquals(50,accountList.get(index_Id_1).getBalance() ),
                ()-> assertEquals(250,accountList.get(index_Id_2).getBalance() )
        );
        System.out.println(" Balance1 after transfer = " + accountList.get(index_Id_1).getBalance());
        System.out.println(" Balance2 after transfer = " + accountList.get(index_Id_2).getBalance());
    }
}