package StudentApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
    public static String SqlDriverClass = "com.mysql.cj.jdbc.Driver";
    public static String DatabaseUrl = "jdbc:mysql://localhost:3306/sas_db";
    public static String DBuser = "root";
    public static String DBpass = "root";


    public Connection getSQLiteCOnnection(){
        String url = "jdbc:sqlite:C://sqlite/db/test.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


}
