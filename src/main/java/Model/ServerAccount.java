package src.main.java.Model;

public class ServerAccount extends Account {
    private String id;

    public ServerAccount(){
        super();
    }
    public ServerAccount(String id, String account, String password, String name, String gender){
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
