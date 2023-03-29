package com.project.Model;

public class Account {
    private String account;
    private String password;

    // Create accountModel
    public Account(){
        account = "";
        password = "";
    }
    public Account(String account, String password, String name, String gender){
        this.account = account;
        this.password = password;
    }

    public String getAccount(){
        return account;
    }
    public String getPassword(){
        return password;
    }

    public void setAccount(String account){
        this.account = account;
    }
    public void setPassword(String password){
        this.password = password;
    }
}
