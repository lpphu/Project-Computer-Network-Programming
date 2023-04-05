package com.project.Component;

public class RailFence {
    public static String encrypt(String text, int key) {
        int len = text.length();
        char[][] rail = new char[key][len];

        for (int i = 0; i < key; i++) {
            for (int j = 0; j < len; j++) {
                rail[i][j] = '\n';
            }
        }

        boolean direction = false;
        int row = 0;
        int col = 0;

        for (int i = 0; i < len; i++) {
            if (row == 0 || row == key - 1) {
                direction = !direction;
            }

            rail[row][col++] = text.charAt(i);

            if (direction) {
                row++;
            } else {
                row--;
            }
        }

        String result = "";
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < len; j++) {
                if (rail[i][j] != '\n') {
                    result += rail[i][j];
                }
            }
        }

        return result;
    }

    public static String decrypt(String cipher, int key) {
        int len = cipher.length();
        char[][] rail = new char[key][len];

        for (int i = 0; i < key; i++) {
            for (int j = 0; j < len; j++) {
                rail[i][j] = '\n';
            }
        }

        boolean direction = false;
        int row = 0;
        int col = 0;

        for (int i = 0; i < len; i++) {
            if (row == 0) {
                direction = true;
            }
            if (row == key - 1) {
                direction = false;
            }

            rail[row][col++] = '*';

            if (direction) {
                row++;
            } else {
                row--;
            }
        }

        int index = 0;
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < len; j++) {
                if (rail[i][j] == '*' && index < len) {
                    rail[i][j] = cipher.charAt(index++);
                }
            }
        }

        String result = "";
        direction = false;
        row = 0;
        col = 0;

        for (int i = 0; i < len; i++) {
            if (row == 0 || row == key - 1) {
                direction = !direction;
            }

            result += rail[row][col++];

            if (direction) {
                row++;
            } else {
                row--;
            }
        }

        return result;
    }
}

