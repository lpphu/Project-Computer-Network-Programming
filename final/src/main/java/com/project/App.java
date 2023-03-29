package com.project;

import java.sql.ResultSet;
import com.project.ConnectDB.ConnectDB;
import com.project.View_UI.ChatRoom;
import com.project.View_UI.SignIn;
import com.project.View_UI.SignUp;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // sign in
        try {
            SignIn formSignIn;
            formSignIn = new SignIn();
            SignUp formSignUp = new SignUp();
            ChatRoom room = new ChatRoom("Server");
            formSignIn.setVisible(true);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            ConnectDB a = new ConnectDB();
            ResultSet t = a.executeQuery("select * form account");
            while (t.next()) {
                System.out.println(t.getInt(1) + "  " + t.getString(2));
            }
            a.closeConnection();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // System.out.println( "Hello World!" );
    }
}
