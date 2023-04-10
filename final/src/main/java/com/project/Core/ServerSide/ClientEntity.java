package com.project.Core.ServerSide;

import java.net.InetAddress;
import java.util.ArrayList;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

public class ClientEntity {
    private InetAddress clientAddress;
    private int userId;
    private ArrayList<Integer> groupId;
    private int port;

    public ClientEntity(InetAddress clientAddress,int port, int userId, Integer groupId) {
        this.clientAddress = clientAddress;
        this.userId = userId;
        this.groupId = new ArrayList<Integer>(groupId);
        this.port = port;
    }
    
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public  ArrayList<Integer> getGroupIid() {
        return groupId;
    }
    public void setGroupIid(Integer groupId) {
        this.groupId = new ArrayList<Integer>(groupId);
    }
    public InetAddress getClientAddress() {
        return clientAddress;
    }
    public void setClientAddress(InetAddress clientAddress) {
        this.clientAddress = clientAddress;
    }

    @Override

    public String toString() {
        return "ClientEntity{" + "clientAddress=" + clientAddress + ", userId=" + userId + ", groupId=" + displayGroupId() + ", port=" + port + '}';
    }

    private String displayGroupId() {
        String res = "";
        for( Integer item : groupId) {
            res += item + ",";
        }
        return res;
    }
    
}
