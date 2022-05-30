package electricity.management.system;

import java.sql.*;
import java.sql.DriverManager;

public class Conn {
    private static String username="root";
    private static String password="hakim@123";
    private static String connectionString = "jdbc:mysql://localhost:3306/ebs";
    private static Connection connection;
    Statement command;
    
    Conn(){
        try{
            connection=DriverManager.getConnection(connectionString,username,password);
            command=connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
