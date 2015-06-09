/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author S519295
 */
public class RetrieveUserIDAndPwd {
    public Login login;

    public RetrieveUserIDAndPwd() {
        this.login = new Login();
    }
    public Login retrieveLoginDetails(String user) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
          Connection  con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "");
          String query = "select userId, password from NWLogin13 where userId = ?" ;
          PreparedStatement stmt = con.prepareStatement(query);
          stmt.setString(1, user);
          ResultSet resultSet = stmt.executeQuery();
          Login login = new Login();
          while(resultSet.next()){
              login.setUserID(resultSet.getString(1));
              login.setPassword(resultSet.getString(2));
              System.out.println("userId and password of the user retrieved.........");
          }
          stmt.close();
          con.close();
        
        return login;
    }
    
}
