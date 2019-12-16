package org.openjfx;

import javafx.scene.control.Alert;

import java.util.List;

public class StringMessage {

    public StringMessage() {
    }
    public void dataTest(List<Account> accountList) {
        Person personA = new Person("Adam","Svensson",20);
        Person personB = new Person("Karl","Carlson",40);
        Person personC = new Person("Simo","Facteur",55);
        Person personD = new Person("Oskar","Nilson",19);
        Person personE = new Person("Jimmy","Faudel",45);
        Person personF = new Person("Elodie","Francoise",33);
        Person personG = new Person("Ester","Larson",26);
        Bank bankA = new Bank("SEB");
        Bank bankB = new Bank("Swedbank");
        Bank bankC = new Bank("Handelsbanken");
        Bank bankD = new Bank("Nordea");
        accountList.add(new Account(100, 30000.0f, bankA, personA));
        accountList.add(new Account(200, 10000.0f, bankC, personB));
        accountList.add(new Account(300, 20000.0f, bankB, personC));
        accountList.add(new Account(400, 50000.0f, bankD, personD));
        accountList.add(new Account(500, 20000.0f, bankB, personE));
        accountList.add(new Account(600, 5000.0f, bankB, personF));
        accountList.add(new Account(700, 2000.0f, bankC, personF));
        accountList.add(new Account(800, 0.0f, bankC, personG));
        accountList.add(new Account(900, 1200000.0f, bankC, personG));
    }
    public void idDuplicated(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("This ID ["+message+"] is already used!");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    public void idShouldBeInt() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("The ID should be digits!");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    public void ageShouldBeInt() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("The age should be digits!");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    public void balanceShouldBeFloat() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("The Balance should be digits!");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    public void confirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure? IF YES click OK!");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    public void wrongId(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("There is no account has this ID :["+message+"]");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    public void wrongIdFormat(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("The ID contains digits!");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    public void balanceLessThanAmount() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("The available balance is less than the required amount!");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    public void accountIdTo() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("The bank account to which it was sent does not exist!");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    public void sameIdBank() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("you can not transfer to to your account!");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
