package src.main.java.Model;

public class Account {
    protected String account;
    protected String password;
    protected String name;
    protected String gender;

    public Account(){
        account = "";
        password = "";
        name = "";
        gender = "";
    }
    public Account(String account, String password, String name, String gender){
        this.account = account;
        this.password = password;
        this.name = name;
        this.gender = gender;
    }

    public String getAccount(){
        return account;
    }
    public String getPassword(){
        return password;
    }
    public String getName(){
        return name;
    }
    public String getGender(){
        return gender;
    }

    public void setAccount(String account){
        this.account = account;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
}
