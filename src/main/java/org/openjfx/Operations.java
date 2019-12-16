package org.openjfx;

import java.util.List;
import java.util.stream.Collectors;

public class Operations {
    private StringMessage stringMessage = new StringMessage();

    public Operations() {
    }

    public void deposit(int id,  float amount, List<Account> accountList, String message, List<String> operationList){
        float balance;
        List<Account> filteredList = accountList.stream()
                .filter(e -> e.getId() == id)
                .collect(Collectors.toList());
        if (filteredList.size() == 1){
            if (amount > 0) {
                int index = accountList.indexOf(filteredList.get(0));
                balance = accountList.get(index).getBalance();
                balance= balance + amount;
                accountList.get(index).setBalance(balance);
                operationList.add("[ Deposit ] - Amount :"+amount+"  "+"  Message :"+message);
            }
        }
    }

    public void withdraw(int id, float amount, List<Account> accountList, String message, List<String> operationList){
        List<Account> filteredList = accountList.stream()
                .filter(e -> e.getId() == id)
                .collect(Collectors.toList());
        if (filteredList.size() == 1)
        {
            int index = accountList.indexOf(filteredList.get(0));
            float balance = accountList.get(index).getBalance();
            if (amount<=balance) {
                balance= balance - amount;
                accountList.get(index).setBalance(balance);
                operationList.add("[ Withdraw ] - Amount :"+amount+"  "+"  Message :"+message);
            }else {
                stringMessage.balanceLessThanAmount();
            }
        }
    }

    public void transfer(int idFrom , int idTo, float amount, List<Account> accountList, String message, List<String> operationList){
        if (idFrom != idTo){
            List<Account> filterList = accountList.stream()
                .filter(e -> e.getId() == idFrom)
                .collect(Collectors.toList());
            List<Account> filterList2 = accountList.stream()
                .filter(e -> e.getId() == idTo)
                .collect(Collectors.toList());
            if (filterList.size() == 1 && filterList2.size() == 1){
                int indexIdFrom= accountList.indexOf(filterList.get(0));
                int indexIdTo= accountList.indexOf(filterList2.get(0));
                if (accountList.get(indexIdFrom).getBalance() >= amount){
                    float balance1 = accountList.get(indexIdFrom).getBalance();
                    accountList.get(indexIdFrom).setBalance(balance1- amount);
                    float balance2 = accountList.get(indexIdTo).getBalance();
                    accountList.get(indexIdTo).setBalance(balance2 + amount);
                    operationList.add("[ Transfer ] - Amount :"+amount+"  "+"To AccountID :"+idTo+"  "+" Message :"+ message);
                }else {
                    stringMessage.balanceLessThanAmount();
                }
            }else {
                stringMessage.accountIdTo();
            }
        }else{
            stringMessage.sameIdBank();
        }
    }

}
