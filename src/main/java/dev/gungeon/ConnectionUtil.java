package dev.gungeon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    //It lives!
    public static Connection CreateConnection() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://expensedb.cvsalbi9eqlo.us-west-1.rds.amazonaws.com/", "postgres", "8eoGX%Jx*srB5k");
            return conn;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
