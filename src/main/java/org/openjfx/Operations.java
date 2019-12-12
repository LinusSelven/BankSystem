package org.openjfx;

import java.util.List;
import java.util.stream.Collectors;

public class Operations {
    private List<Account> accountList;

    public Operations(List<Account> accountList) {
        this.accountList = accountList;
    }

    public void deposit(int id,  float amount)
    {List<Account> filteredList = accountList.stream()
                .filter(e -> e.getId() == id)
                .collect(Collectors.toList());
        if (filteredList.size() == 1)
        {
            if (amount > 0)
            {
                int index = accountList.indexOf(filteredList.get(0));
                float balance = accountList.get(index).getBalance();
                balance= balance + amount;
                accountList.get(index).setBalance(balance);
            }
        }
    }

    public void withdraw(int id, float amount) {

        List<Account> filteredList = accountList.stream()
                .filter(e -> e.getId() == id)
                .collect(Collectors.toList());
        if (filteredList.size() == 1) {
            int index = accountList.indexOf(filteredList.get(0));
            float balance = accountList.get(index).getBalance();
            if (amount<=balance) {
                balance= balance - amount;
                accountList.get(index).setBalance(balance);

            }

        }
    }

    public void transfer(int idFrom , int idTo, float amount) {
        List<Account> accountFrom = accountList.stream()
                .filter(e -> e.getId() == idFrom)
                .collect(Collectors.toList());
        List<Account> accountTo = accountList.stream()
                .filter(e -> e.getId() == idTo)
                .collect(Collectors.toList());

        if (accountFrom.size()==1 || accountTo.size() == 1) {
            if (accountFrom.get(0).getBalance() >= amount) {
                int index1 = accountList.indexOf(accountFrom.get(0));
                float balance1 = accountList.get(index1).getBalance();
                accountList.get(index1).setBalance(balance1- amount);
                int index2 = accountList.indexOf(accountTo.get(0));
                float balance2 = accountList.get(index1).getBalance();
                accountList.get(index2).setBalance(balance2 + amount);
            }
        }
    }
}
