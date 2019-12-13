package org.openjfx;

import java.util.Objects;

public class Account {
    private int id;
    private float balance;
    private Bank bank;
    private Person person;


    public Account(int id, float balance, Bank bank, Person person) {
        this.id = id;
        this.balance = balance;
        this.bank = bank;
        this.person = person;

    }

    public int getId() {
        return id;
    }

    public Bank getBank() {
        return bank;
    }

    public Person getPerson() {
        return person;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                ", bank=" + bank +
                ", person=" + person +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id &&
                Float.compare(account.balance, balance) == 0 &&
                Objects.equals(bank, account.bank) &&
                Objects.equals(person, account.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance, bank, person);
    }
}
