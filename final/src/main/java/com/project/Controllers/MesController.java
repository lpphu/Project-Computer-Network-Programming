package com.project.Controllers;
import com.project.Component.*;

import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.project.Core.ConnectDB;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public class MesController {
    
    public static void saveMessageIntoDB(Message mes) {
        try {
            ConnectDB conn = new ConnectDB();
            int result = conn.executeUpdate("INSERT INTO message (`text_mes`, `user_id`, `key_mes`) VALUES('" + RailFence.encrypt(mes.getMessage(), mes.getKey()) + "','" + mes.getUserId() 
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


    public static List<String> getAllMessageOfUser(int id) throws Exception {
        List<String> messages = new ArrayList<>();
        ConnectDB conn = new ConnectDB();
        String query = String.format("SELECT message.*, users.* FROM message, users WHERE message.user_id = users.id AND message.user_id = %d", id);
        ResultSet messageResult = conn.executeQuery(query);
        while (messageResult.next()) {
            String time = convertTimestampToString(messageResult.getTimestamp("time_insert"));
            String mes = "   Me"  + " : " + RailFence.decrypt(messageResult.getString(2), messageResult.getInt(4)) + "   " + time;
            messages.add(mes);
        }
        return messages;
    }

    public static String convertToHumanReadableTime(String timestamp) {
        long currentTimeMillis = System.currentTimeMillis();
        long timeDifferenceMillis = currentTimeMillis - Long.parseLong(timestamp);
        long timeDifferenceSeconds = timeDifferenceMillis / 1000;
        if (timeDifferenceSeconds < 60) {
            return timeDifferenceSeconds + " seconds ago";
        } else {
            long timeDifferenceMinutes = timeDifferenceSeconds / 60;
            if (timeDifferenceMinutes < 60) {
                return timeDifferenceMinutes + " minute(s) ago";
            } else {
                long timeDifferenceHours = timeDifferenceMinutes / 60;
                if (timeDifferenceHours < 24) {
                    return timeDifferenceHours + " hour(s) ago";
                } else {
                    long timeDifferenceDays = timeDifferenceHours / 24;
                    return timeDifferenceDays + " day(s) ago";
                }
            }
        }
    }
      

    public static String convertTimestampToString(Timestamp time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sdf.format(time);
        return str;
    }

    public static int getGroupId() {
        int groupId = 1;
        try {
            ConnectDB conn = new ConnectDB();
            ResultSet user = conn.executeQuery("SELECT * FROM groupChat order by id desc limit 1");
            if (user.next()) {
                groupId = user.getInt("groupId");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return groupId;
    }
    
}
