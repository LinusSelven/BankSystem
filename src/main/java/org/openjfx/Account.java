package org.openjfx;

public class Account {
    private int id;
    private Bank bank;
    private Human person;
    private float balance;

    public Account(int id, Bank bank, Human person, float balance) {
        this.id = id;
        this.bank = bank;
        this.person = person;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public Bank getBank() {
        return bank;
    }

    public Human getPerson() {
        return person;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
