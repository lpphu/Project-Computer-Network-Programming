package com.project.Controllers;
import java.awt.Frame;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

import javax.swing.JFrame;

import com.project.Core.ConnectDB;
import com.project.Views.*;

public class UserController {
    
    public String SignIn(String username, String password) {

        if( username.equals("") ) {
            return "Username not empty!!";
        }
        if( password.equals("") ) {
            return "Password not empty!!";
        }

        try {
            Boolean result = false;
            ConnectDB conn = new ConnectDB();
            ResultSet user = conn.executeQuery("select * from users");
            while(user.next()){
                if (user.getString(2).equals(username) && user.getString(3).equals(password)){
                    result = true;
                    break;
                }else {
                    return "Username or passwordd incorrect!!";
                }
            }
            if (result){
                this.closeAllFrameInScreen();
                ChatRoom room = new ChatRoom(username);
                room.setVisible(true);
                // screen.setVisible(false);
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Err!!";
        }
        return password;
    }

    public String signUp(String username , String password , String confirmPassword ) {
        String notification = "";
        Boolean run = true;

        if(username.equals("") ) {
            notification = "Username is not empty!!";
            run = false;
        }
        if(password.equals("") ) {
            notification = "password is not empty!!";
            run = false;
        }
        if(confirmPassword.equals("") ) {
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
                    ChatRoom room = new ChatRoom(username);
                    room.setVisible(true);
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

    public void SignOut(String username, String password) {

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
