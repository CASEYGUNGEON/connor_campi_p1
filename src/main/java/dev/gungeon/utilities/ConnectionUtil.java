package dev.gungeon.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    //It lives!
    public static Connection createConnection() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://expensedb.cvsalbi9eqlo.us-west-1.rds.amazonaws.com/", "postgres", "8eoGX%Jx*srB5k");
            Logger.log("Connection established.",LogLevel.INFO);
            return conn;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
