/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package farm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author S519459
 */
public class AddUserBean
{
public int AddUser(String userid,String password,String firstname,String lastname,int superuser) throws ClassNotFoundException, SQLException
{
 String email="";
 Connection con = DataBaseConnection.giveConnection();
 String query=  "insert into login values('"+userid+"','"+password+"','"+email+"','"+firstname+"','"+lastname+"','"+superuser+"')";
PreparedStatement ps =con.prepareStatement(query);
int res =0;
        try
        {
       res=  ps.executeUpdate();
        }
        catch(Exception ex)
        {
        res=0;
        }
return res;
}
public ArrayList<String> UserLoggedOnDetails(String userid) throws ClassNotFoundException, SQLException
{
    ArrayList<String> list = new ArrayList<String>();
 Connection con = DataBaseConnection.giveConnection();
    System.out.println("user logged on value in AddUserBean is  "+userid);
String query=  "select * from login where userid='"+userid+"'";
Statement st= con.createStatement();
ResultSet rs = st.executeQuery(query);
while(rs.next())
{
list.add(rs.getString("password"));
list.add(rs.getString("issuperuser"));
}
return list;
}
}
