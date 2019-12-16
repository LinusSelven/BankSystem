package org.openjfx;

import javafx.scene.control.Alert;

import java.util.List;
import java.util.stream.Collectors;

public class Operations {

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
                balanceLessThanAmount();
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
                    balanceLessThanAmount();
                }
            }else {
                accountIdTo();
            }
        }else{
            sameIdBank();
        }
    }

    private void balanceLessThanAmount() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("The available balance is less than the required amount!");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    private void accountIdTo() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("The bank account to which it was sent does not exist!");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    private void sameIdBank() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("you can not transfer to to your account!");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
