package PMS.bean;

import PMS.security.PwdUtil;

public class Account {
    private String name;
    private String password;

    public Account() {
    }


    public Account(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
