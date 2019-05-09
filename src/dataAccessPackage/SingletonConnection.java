package dataAccessPackage;

import exceptionPackage.AccesDBException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {

    private static Connection uniqueConnection = null;

    public static Connection getInstance( ) throws SQLException {
        if (uniqueConnection == null) {
            uniqueConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carnetsPatient?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris", "root", "premier");
        }
        return uniqueConnection;
    }

    public static void close() throws SQLException{
        if(uniqueConnection != null){
            uniqueConnection.close();
            uniqueConnection = null;
        }
    }
}