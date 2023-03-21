package src.main.java.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectDatabase {
    // Tạo kết nối database
    private Connection conn = null;

    // Các phương thức
    private static String DB_URL = "jdbc:mysql://localhost:3306/project";
    private static String USER_NAME = "root";
    private static String PASSWORD = "12345678";

    /**
     * main
     * 
     * @author 
     * @param args
     */

    // Kết nối database
    public ConnectDatabase() throws Exception{
        Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
    }

    // Lấy database
    public Connection getDatabase() {
        return conn;
    }

    // Lấy data của mỗi bảng dữ liệu
    public ResultSet getDataAccount() throws Exception{
        return createStmt().executeQuery("SELECT * FORM account");
    }
    public ResultSet getDataClientAccount() throws Exception{
        return createStmt().executeQuery("SELECT * FORM client");
    }
    public ResultSet getDataServerAccount() throws Exception{
        return createStmt().executeQuery("SELECT * FORM server");
    }
    // Truyền lệnh SQL thực thi
    public ResultSet excute(String statementSQL) throws Exception{
        return createStmt().executeQuery(statementSQL);
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
