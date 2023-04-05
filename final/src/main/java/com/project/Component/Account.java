package com.project.Component;

public class Account {
    private String username;
    private String password;
    private Boolean isAdmin;
    private int userId;

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public Account(String username, String password, Boolean isAdmin, int userId) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.userId = userId;
        
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin + ",userId='" + userId + '\'' +
                '}';
    }
}
