package org.openjfx;

import java.util.ArrayList;

public class Model {

    ArrayList<Account> accounts;

    public Model(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
}
