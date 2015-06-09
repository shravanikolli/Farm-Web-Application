/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package farm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author S519459
 */
public class DataBaseConnection 
{
    
public static Connection giveConnection() throws ClassNotFoundException, SQLException
{
 String driverName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "farmapp";
        String userName = "root";
        String password = "password@1234";
   Connection con=null;
  Class.forName(driverName);
con = DriverManager.getConnection(url + dbName, userName, password);
    System.out.println(con);
return con;
}
}
