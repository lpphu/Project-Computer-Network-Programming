package com.project.Controllers;
import com.project.Component.*;
import java.awt.Frame;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

import javax.swing.JFrame;

import com.project.Core.ConnectDB;
import com.project.Views.*;

public class UserController {
    
    public Response SignIn(String username, String password) {
        if( username.equals("") ) {
            return new Response(false, "Username not empty!!", null);
        }
        if( password.equals("") ) {
            return new Response(false, "Password not empty!!", null);
        }

        try {
            Boolean result = false;
            ConnectDB conn = new ConnectDB();
            ResultSet user = conn.executeQuery("select * from users");
            while(user.next()){
                if (user.getString(2).equals(username) && user.getString(3).equals(password)){
                    LoginPreferences login = new LoginPreferences();
                    login.saveUsername(username);
                    return new Response(true, "Login sucessfully!!", new Account(user.getString(2), user.getString(3), user.getBoolean(4), user.getInt(1)));
                }
            }
            return new Response(false, "Username or passwordd incorrect!!", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(false, "Err!!", null);
        }
    }

    public String signUp(String username , String password , String confirmPassword ) {
        String notification = "";
        Boolean run = true;

        if(username.equals("") ) {
            notification = "Username is not empty!!";
            run = false;
        }
        if(password.equals("") && run != false ) {
            notification = "password is not empty!!";
            run = false;
        }
        if(confirmPassword.equals("") && run != false ) {
            notification = "confirmPassword is not empty!!";
            run = false;
        }
        try {
            ConnectDB conn = new ConnectDB();
            ResultSet user = conn.executeQuery("select * from users where username = '" + username +"'" );
            if(user.next() == true && run == true ) {
                notification = "User alreaddy exist !!";
                run = false;
            }
            if( !password.equals(confirmPassword)  && run == true) {
                notification = "Password and confirm password is not match!!";
                run = false;
            }
            if (run) {
                int result = conn.executeUpdate("INSERT INTO USERS (`username`, `password`) VALUES('" + username + "','" + password + "')");
                if (result == 1) {
                    notification = "User created successfully!!";
                    this.closeAllFrameInScreen();
                    // ChatRoom room = new ChatRoom(username);
                    // room.setVisible(true);
                } else {
                    notification = "User created fail!!";
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return notification;
        
    }
    public static String getNameUser(int id) {
        String name = "";
        try {
            System.out.println(id);
            ConnectDB conn = new ConnectDB();
            ResultSet user = conn.executeQuery("SELECT username FROM users WHERE id = '" + id + "'");
            if (user.next()) {
                name = user.getString("username");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }
    



    public void closeAllFrameInScreen() {
        Frame[] frames = Frame.getFrames();
        List<JFrame> jFrames = new ArrayList<>();
        for (Frame f : frames) {
            if (f instanceof JFrame) {
                jFrames.add((JFrame) f);
            }
        }
        for (JFrame f : jFrames) {
            if (f.isVisible()) {
                f.setVisible(false);
            }
        }
    }
    
    
}
