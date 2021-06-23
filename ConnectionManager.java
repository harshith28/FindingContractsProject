package code;

import java.sql.*;

public class ConnectionManager {

    private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private static String username = "harshith_28";
    private static String password = "ammu2802";
    private static Connection con;

    public static Connection getConnection() throws Exception {
      con = DriverManager.getConnection(url, username, password);
        return con;
    }
    
}