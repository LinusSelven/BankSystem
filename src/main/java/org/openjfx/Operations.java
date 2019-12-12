package org.openjfx;

import java.util.List;
import java.util.stream.Collectors;

public class Operations {
    private List<Account> accountList;

    public Operations(List<Account> accountList) {
        this.accountList = accountList;
    }

    public void deposit(int id,  float amount)    {
        float balance=0.0f;
        List<Account> filteredList = accountList.stream()
                .filter(e -> e.getId() == id)
                .collect(Collectors.toList());
        if (filteredList.size() == 1)
        {
            if (amount > 0)
            {
                int index = accountList.indexOf(filteredList.get(0));
                balance = accountList.get(index).getBalance();
                balance= balance + amount;
                accountList.get(index).setBalance(balance);
            }
        }
    }

    public void withdraw(int id, float amount)
    {
        List<Account> filteredList = accountList.stream()
                .filter(e -> e.getId() == id)
                .collect(Collectors.toList());
        if (filteredList.size() == 1)
        {
            int index = accountList.indexOf(filteredList.get(0));
            float balance = accountList.get(index).getBalance();
            if (amount<=balance)
            {
                balance= balance - amount;
                accountList.get(index).setBalance(balance);
            }
        }
    }

    public void transfer(int idFrom , int idTo, float amount)
    {
        int indexIdFrom= accountList.indexOf(accountList.stream()
                .filter(e -> e.getId() == idFrom)
                .collect(Collectors.toList()).get(0));

        int indexIdTo= accountList.indexOf(accountList.stream()
                .filter(e -> e.getId() == idTo)
                .collect(Collectors.toList()).get(0));

        float balance1=0.0f;
        float balance2=0.0f;
        if (accountList.get(indexIdFrom)!= null && accountList.get(indexIdTo)!=null)
        {
            if (accountList.get(indexIdFrom).getBalance() >= amount)
            {
                balance1 = accountList.get(indexIdFrom).getBalance();
                accountList.get(indexIdFrom).setBalance(balance1- amount);

                balance2 = accountList.get(indexIdTo).getBalance();
                accountList.get(indexIdTo).setBalance(balance2 + amount);
            }
        }
    }
}
