package farm;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author S519295
 */
public class RedirectFacilityBean {
    public String facilityid;
    public String facilityname;
    public String facilitydescription;
    public String latitude;
    public String longitude;
    public String directions;

    public RedirectFacilityBean(String facilityid,String facilityname, String facilitydescription, String latitude, String longitude, String directions) {
        this.facilityid = facilityid;
        this.facilitydescription = facilitydescription;
        this.latitude = latitude;
        this.longitude = longitude;
        this.directions = directions;
        this.facilityname=facilityname;
    }
    public RedirectFacilityBean()
    {
    this("","","","","","");
    }
    public String getFacilityid() 
    {
        return facilityid;
    }

    public void setFacilityid(String facilityid) {
        this.facilityid = facilityid;
    }

    public String getFacilityname() {
        return facilityname;
    }

    public void setFacilityname(String facilityname) {
        this.facilityname = facilityname;
    }

    public String getFacilitydescription() {
        return facilitydescription;
    }

    public void setFacilitydescription(String facilitydescription) {
        this.facilitydescription = facilitydescription;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

public String FacilityIdAndName(String facilityName) throws ClassNotFoundException, SQLException
{
Connection con = DataBaseConnection.giveConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from facility where facilityname='"+facilityName+"'");
String x = "";
while(rs.next())
{
x = rs.getString("facilityid");
}
return x;
}
public RedirectFacilityBean facilityWithName(String facilityName) throws ClassNotFoundException, SQLException
{
Connection con = DataBaseConnection.giveConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from facility where facilityname='"+facilityName+"'");
RedirectFacilityBean list = null;
while(rs.next())
{ 
    list=new RedirectFacilityBean(rs.getString("facilityid"),rs.getString("facilityname"),rs.getString("facilitydescription")
        ,rs.getString("latitude"),rs.getString("longitude"),rs.getString("directions"));
}
return list;
}
}
