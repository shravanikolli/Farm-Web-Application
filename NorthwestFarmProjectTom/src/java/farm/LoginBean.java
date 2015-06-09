package farm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginBean 
{
 public String userid;
 public String emailid;
 public String firstName;
 public String lastName;

    public LoginBean() {
    }

    public LoginBean(String userid, String emailid, String firstName, String lastName) {
        this.userid = userid;
        this.emailid = emailid;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
 
 public ResultSet getLoggedInUsersList() throws ClassNotFoundException, SQLException {

   Connection con = DataBaseConnection.giveConnection();
ResultSet rs;
Statement stmt = con.createStatement();
rs = stmt.executeQuery("select  * from login " );
      return rs;
  }
 public LoginBean CurrentUser(String userid) throws ClassNotFoundException, SQLException
 {
     LoginBean x = new LoginBean();
ResultSet rs = x.getLoggedInUsersList();
while(rs.next())
{
if(rs.getString("userid").equals(userid))
x= new LoginBean(rs.getString("userid"),rs.getString("emailid"),rs.getString("firstname"),rs.getString("lastname"));
} 
return x;
 }
 public int UpdateProfile(String userid,String firstname,String lastname,String emailid) throws ClassNotFoundException, SQLException
 {
 Connection con = DataBaseConnection.giveConnection();
 //update login set emailid='m@gmail.com' ,firstname='aruna', lastname='ranjith' where userid='Malla';
String query ="UPDATE login SET emailid='"+emailid+"', firstname='"+firstname+"',lastname='"+lastname+"'WHERE userid='"+userid+"'";
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
 
}