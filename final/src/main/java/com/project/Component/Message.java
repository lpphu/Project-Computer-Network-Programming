package com.project.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Message implements Serializable{
    private int userId;
    private String message;
    private int key;

    
    public Message() {
        this.userId = 0;
        this.message = "";
        this.key = 0;
    }
    public Message(int userId, String message, int key) {
        this.userId = userId;
        this.message = message;
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getUserId() {
        return userId;
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
                "userId= " + userId + ", message=" + message + ", key = " + key + "}"; 
    }
}