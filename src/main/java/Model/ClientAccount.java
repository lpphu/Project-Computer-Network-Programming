package src.main.java.Model;

public class ClientAccount extends Account {
    private String id;

    public ClientAccount(){
        super();
    }
    public ClientAccount(String id, String account, String password, String name, String gender){
        super(account, password, name, gender);
        this.id = id;
    }

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
}
