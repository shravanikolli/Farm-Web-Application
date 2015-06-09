package farm;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author S519459
 */
public class ChangePasswordBean {
public ResultSet LoggedInUserDetails(String userLogged) throws ClassNotFoundException, SQLException {

   Connection con = DataBaseConnection.giveConnection();
ResultSet rs;
Statement stmt = con.createStatement();
rs = stmt.executeQuery("select  * from login where  userid='"+userLogged+"'" );

      return rs;
  } 
 public void insertIntoLogin(String passwordEntered, String userLogged) throws SQLException, ClassNotFoundException
 {

     int i;
    try (Connection con = DataBaseConnection.giveConnection();) {
        String query ="update login set password='"+passwordEntered+"' where userid='"+userLogged+"'";
    try (PreparedStatement ps = con.prepareStatement(query)) {
        i = ps.executeUpdate();
    }
    }
     System.out.println(" password up date is done "+i);
 }
}
