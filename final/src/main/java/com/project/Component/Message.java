package com.project.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;

public class Message implements Serializable{
    private int userId;
    private String message;
    private int key;
    private int groupID;
    private String type = "NONE";

    
    
    public Message() {
        this.userId = 0;
        this.message = "";
        this.key = 0;
        this.type = "NONE";
        this.groupID = 0;
    }
    public Message(int userId, String message, int key) {
        this.userId = userId;
        this.message = message;
        this.key = key;
        this.type = "NONE";
        this.groupID = 0;
    }
    public Message(int userId, String message, int key, int groupID) {
        this.userId = userId;
        this.message = message;
        this.key = key;
        this.groupID = groupID;
        this.type = "NONE";
    }
    public Message(int userId, String message, int key, int groupID, String type) {
        this.userId = userId;
        this.message = message;
        this.key = key;
        this.groupID = groupID;
        this.type = type;
    }
    public Message(int userId, String message, int key, String type) {
        this.userId = userId;
        this.message = message;
        this.key = key;
        this.type = type;
    }
    

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public int getGroupID() {
        return groupID;
    }
    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }


    public String getMessage() {
        return message;
    }

    public Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }
    public byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }

    @Override
    public String toString() {
        return "Message{" +
                "userId= " + userId + ", message=" + message + ", key = " + key + ",  group=" + groupID + ", type " + type + "}"; 
    }
    public Integer getUserId() {
        return this.userId;
    }
}