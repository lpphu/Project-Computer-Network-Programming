package com.project.ConnectDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
 
public class ConnectDB {
    // Tạo kết nối database 
    private Connection conn = null;

    // Các phương thức
    private static String DB_URL = "jdbc:mysql://localhost:3306/test";
    private static String USER_NAME = "root";
    private static String PASSWORD = "";

    public ConnectDB() throws Exception{
        conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
    }

    // Lấy database 
    public Connection getDatabase() {
        return conn;
    }

    // Lấy data của mỗi bảng dữ liệu qua lệnh string
    public ResultSet executeQuery(String str) throws Exception{
        return createStmt().executeQuery(str);
    }

    // Thao tác thêm xóa sửa thông qua lệnh string
    public int executeUpdate(String str) throws Exception{
        return createStmt().executeUpdate(str);
    }

    // Tạo lệnh cho database
    protected Statement createStmt() throws Exception{
        return conn.createStatement();
    }

    // Ngừng sử dụng database
    public void closeConnection() throws Exception{
        conn.close();
    }
}