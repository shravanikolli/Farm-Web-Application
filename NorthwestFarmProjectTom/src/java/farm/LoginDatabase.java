/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package farm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author S519459
 */
public class LoginDatabase {
 
 public ResultSet getLoggedInUsersList() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
 //System.out.println("we are in this method********************************************************");

  ResultSet rs = null;

   try
   {
   Connection con = DataBaseConnection.giveConnection();
Statement stmt = con.createStatement();
rs = stmt.executeQuery("select  * from login " );
   }
   catch(ClassNotFoundException ex)
   {
       System.out.println(" exceptin ^^^^^^^^^^"+ex);   
   }
      return rs;
  }
}
