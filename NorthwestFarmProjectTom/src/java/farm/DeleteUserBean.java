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
public class DeleteUserBean 
{
 public ArrayList<String> ListOfregisteredUsers() throws ClassNotFoundException, SQLException
 {
     ArrayList<String> list = new ArrayList<>();
 Connection con = DataBaseConnection.giveConnection();
String query=  "select * from login ";
Statement st= con.createStatement();
ResultSet rs = st.executeQuery(query);
while(rs.next())
{
if(!(rs.getString("issuperuser").equals("1")))
list.add(rs.getString("userid"));
}
return list;
}
public int deleteUserWithId(String userid) throws ClassNotFoundException, ClassNotFoundException, ClassNotFoundException, ClassNotFoundException, ClassNotFoundException, SQLException
{
String query ="delete from login where userid='"+userid+"'";
PreparedStatement ps=DataBaseConnection.giveConnection().prepareStatement(query);
int i= ps.executeUpdate();
return i;
} 
}
