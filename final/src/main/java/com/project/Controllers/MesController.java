package com.project.Controllers;
import com.project.Component.*;

import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.project.Core.ConnectDB;
import com.project.Views.LogMessageFromClient;

public class MesController {
    
    public static void saveMessageIntoDB(Message mes) {
        try {
            ConnectDB conn = new ConnectDB();
            int result = conn.executeUpdate("INSERT INTO message (`text_mes`, `user_id`, `key_mes`) VALUES('" + mes.getMessage() + "','" + mes.getUserId() 
            + "','" + mes.getKey()
            + "')");
            if (result == 1) {
                System.out.println("Save message successfully!!!");
            } else {
                System.out.println("Save message failed!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showAllMessage(LogMessageFromClient log) throws Exception {
        ConnectDB conn = new ConnectDB();
        ResultSet message = conn.executeQuery("select message.*, users.* from message, users where message.user_id = users.id");
        while(message.next()){
            String mes = "  " + message.getString(6) + " : " + RailFence.decrypt(message.getString(2), message.getInt(4));
            log.addMes(mes);
        }
    }


    public static List<String> getAllMessageOfUser(int id) throws Exception {
        List<String> messages = new ArrayList<>();
        ConnectDB conn = new ConnectDB();
        String query = String.format("SELECT message.*, users.* FROM message, users WHERE message.user_id = users.id AND message.user_id = %d", id);
        ResultSet messageResult = conn.executeQuery(query);
        while (messageResult.next()) {
            String mes = "   Me"  + " : " + RailFence.decrypt(messageResult.getString(2), messageResult.getInt(4));
            messages.add(mes);
        }
        return messages;
    }
    
    
    
    
}
