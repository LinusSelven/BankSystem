package org.openjfx;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Operations {
    private StringMessage stringMessage = new StringMessage();
    private static Operations operations = null;

    private Operations() {
    }

    public static Operations getInstance(){
        if (operations == null)
        {
            operations = new Operations();
        }
        return operations;
    }


    public void deposit(int id,  float amount, List<Account> accountList, String message, Map<String, Integer> operationList){
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
                operationList.put("[ Deposit ] - Amount :"+amount+"  "+"  Message :"+message, id);
            }
        }
    }

    public void withdraw(int id, float amount, List<Account> accountList, String message, Map<String, Integer> operationList){
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
                operationList.put("[ Withdraw ] - Amount :"+amount+"  "+"  Message :"+message, id);
            }else {
                stringMessage.balanceLessThanAmount();
            }
        }
    }

    public void transfer(int idFrom , int idTo, float amount, List<Account> accountList, String message, Map<String, Integer> operationList){
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
                    operationList.put("[ Transfer ] - Amount :"+amount+"  "+"To AccountID :"+idTo+"  "+" Message :"+ message, idFrom);
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
