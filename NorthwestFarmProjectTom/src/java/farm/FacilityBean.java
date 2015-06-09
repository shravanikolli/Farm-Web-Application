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
public class FacilityBean {
    public String facilityid;
    public String facilityname;
    public String facilitydescription;
    public String latitude;
    public String longitude;
    public String directions;
    ArrayList<String> facilityList;

    public FacilityBean(String facilityid,String facilityname, String facilitydescription, String latitude, String longitude, String directions) {
        this.facilityid = facilityid;
        this.facilitydescription = facilitydescription;
        this.latitude = latitude;
        this.longitude = longitude;
        this.directions = directions;
        this.facilityname=facilityname;
    }

    public FacilityBean() {
        facilityList = new ArrayList<>();
    }

    public String getFacilityid() {
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

    public ArrayList<String> getFacilityList() {
        return facilityList;
    }

    public void setFacilityList(ArrayList<String> facilityList) {
        this.facilityList = facilityList;
    }

   public  ResultSet allFacilities()throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
    {
    Class.forName("com.mysql.jdbc.Driver").newInstance();
          Connection  con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "");
          Statement stmt = con.createStatement();
          String query = "select * from facility";
   ResultSet resultSet=stmt.executeQuery(query);
         return  resultSet;
    }
   public ArrayList<FacilityBean> retrieveFacilities() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        FacilityBean x = new FacilityBean();
       ResultSet rs =x.allFacilities();
       ArrayList<FacilityBean> list = new ArrayList();
          while(rs.next()){

         list.add(new FacilityBean(rs.getString("facilityid"),rs.getString("facilityname"),rs.getString("facilitydescription")
        ,rs.getString("latitude"),rs.getString("longitude"),rs.getString("directions")));
  }
       System.out.println("list count in ret facili is "+list.size());
     
        return list;
    }
  public FacilityBean retrieveFacilityWithName(String facilityName,int facilityid) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
    {
        System.out.println(" facility id in facility bean retrieveFacilityWithName "+facilityid);
     FacilityBean x = new FacilityBean();
  ArrayList<FacilityBean> resultSet =x.retrieveFacilities();
  String facid =String.valueOf(facilityid);
for(FacilityBean fb :resultSet)
   {
 if( facilityName != null){
  if(fb.getFacilityname().equals(facilityName) )
   {
 return fb;
   }}
 else if(fb.getFacilityid().equals(facid))
     return fb;
   }
   return null;
    }
  public int facilityIdIWithName(String name) throws ClassNotFoundException, SQLException
  {
  Connection con =DataBaseConnection.giveConnection();
  Statement st =con.createStatement();
  ResultSet rs = st.executeQuery("select * from facility where facilityname='"+name+"'");
  while(rs.next())
  {
      if(rs.getString("facilityname").equals(name))
  return Integer.valueOf(rs.getString("facilityid"));
  }
  return 0;
  }
}
