package com.project.Controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class HashPass{
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedPassword = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedPassword) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    public static boolean checkPassword(String password, String hash) throws NoSuchAlgorithmException {
        return hash.equals(hashPassword(password));
    }



}

