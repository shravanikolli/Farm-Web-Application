/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


/**
 *
 * @author s519351
 */
@ManagedBean
@SessionScoped
public class FacilitiesManagedBean implements Serializable {

    private static final long serialVersionUID=1L;
    
    private String FacilityName;
    private String EnterpriseName;
       
    public String getFacilityName() {
        System.out.println("Facility name is " + FacilityName);
        return FacilityName;
        
    }

    public void setFacilityName(String FacilityName) {
        this.FacilityName = FacilityName;
        System.out.println("Facility name set to " + FacilityName);
    }

    public String getEnterpriseName() {
        return EnterpriseName;
    }

    public void setEnterpriseName(String EnterpriseName) {
        this.EnterpriseName = EnterpriseName;
    }

    /**
     * Creates a new instance of FacilitiesManagedBean
     */
    public FacilitiesManagedBean() {
    }
    
    public void passFName(){
     String fname= FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("FName");
//     fname="R T";
        System.out.println("Facility name from javascript: "+fname);
        setFacilityName(fname);
    }

    /*
     To retrieve directions to facilities
     */
    public String[] getfacdirections() {
//        ArrayList<String> dir = new ArrayList<>();
        String[] dir = null;
        Statement stmt = null;
        Connection con = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmapp", "root", "password@1234");
            String sql = "select * from facility where facility_name ='"+getFacilityName()+"'";
//            String sql = "select * from facility where facility_name ='R.T. Wright Farm'";

            PreparedStatement ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);

            while (rs.next()) {
                System.out.println("Directions: " + rs.getString("directions"));
                dir = rs.getString("directions").split(",");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return dir;
    }

    public ArrayList<Facility> getFacilityList() {
        ArrayList<Facility> Flist = new ArrayList<>();
        Statement stmt = null;
        Connection con = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmapp", "root", "password@1234");
            String sql = "select facility_name from facility";

            stmt = con.createStatement();

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("Facility Name:" + rs.getString("facility_name"));
//                Flist.add(new Facility("Giri","Giri",1,1,"giri"));
                Flist.add(new Facility(rs.getString("facility_name")));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                con.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return Flist;
    }
    
    
    public ArrayList<Facility> getFacDetails() {
        ArrayList<Facility> Flist = new ArrayList<>();
        Statement stmt = null;
        Connection con = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmapp", "root", "password@1234");
            System.out.println("************ "+getFacilityName());
            String sql = "select * from facility where facility_name='"+getFacilityName()+"'";
            System.out.println("sql stmt: "+sql);
            stmt = con.createStatement();

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("Facility Name:" + rs.getString("facility_name"));
//                Flist.add(new Facility("Giri","Giri",1,1,"giri"));
                Flist.add(new Facility(rs.getString("facility_name"), rs.getString("facility_desc"), rs.getFloat("latitude"), rs.getFloat("longitude"), rs.getString("directions")));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                con.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return Flist;
    }
    
    
    
    /**
     *
     * @return
     */
    public String getFacLat() {
        Statement stmt = null;
        Connection con = null;
        ResultSet rs = null;
        String myLat="";

        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmapp", "root", "password@1234");
            String sql = "select latitude from facility";

            stmt = con.createStatement();

            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Latitude:" + rs.getFloat("latitude"));
//                if (newrs.next()) {
                    myLat += rs.getFloat("latitude")+ ",";
//                } else {
//                    myLat += rs.getFloat("latitude") ;
//                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                con.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Latitude: "+myLat);
        
//        myLat = "sfklefuhasfjwsFKJASDFSJHKFSFHASFSASHF";
        return myLat;
    }
    
    
     public String getFacLng() {
        Statement stmt = null;
        Connection con = null;
        ResultSet rs = null;
        String myLng="";

        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmapp", "root", "password@1234");
            String sql = "select longitude from facility";

            stmt = con.createStatement();

            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Longitude:" + rs.getFloat("longitude"));
                myLng+=rs.getFloat("longitude")+",";
                
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                con.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
         System.out.println("Longitude: "+myLng);
         
//         myLng = "SEFSFASFYKWGFA:CWYIOFGWkcasuifwCAILjFH9W7fgw";
        return myLng;
    }

     public String getFacName() {
        String myName="";
        Statement stmt = null;
        Connection con = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmapp", "root", "password@1234");
            String sql = "select facility_name from facility";

            stmt = con.createStatement();

            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Facility Name:" + rs.getString("facility_name"));
               myName+=rs.getString("facility_name")+",";
                
            }
System.out.println("Facility Name xxxxxxxxxxxxxxxxx: "+myName);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                con.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//         myName = "GIRIRIRIRIRIRIRIRIRI_KOLILILILILILILILLILI";
        return myName;
    }
    public ArrayList<Enterprise> getEnterpriseDetails() {

        ArrayList<Enterprise> Elist = new ArrayList<>();
        Statement stmt = null;
        Connection con = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmapp", "root", "password@1234");
            //'"+getFacilityName()+"'
            String sql = "select * from enterprise where facility_id in (select facility_id from facility where facility_name='"+getFacilityName()+"')";
            
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
//                String enterpriseIconPath="http:\\localhost:8080\\C:\\Users\\s519351\\Documents\\NetBeansProjects\\FinalGDP\\"+rs.getString("enterprise_icon");
//                String enterpriseIconPath="C:\\Users\\s519351\\Documents\\NetBeansProjects\\FinalGDP\\web\\"+rs.getString("enterprise_icon");
//                System.out.println("Enterprise Icon Path: "+enterpriseIconPath);
                Elist.add(new Enterprise(rs.getString("enterprise_name"), rs.getString("enterprise_desc"), rs.getString("enterprise_icon")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                con.close();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Elist;
    }

    public String getEnterpriseDesc() {
        String EntDesc = null;
        Statement stmt = null;
        Connection con = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Ent Name ***" + getEnterpriseName());
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmapp", "root", "password@1234");
            String sql = "select * from enterprise where enterprise_name= '" + getEnterpriseName() + "'";
            PreparedStatement ps = con.prepareStatement(sql);
            System.out.println("Ent Name ***" + getEnterpriseName());
            rs = ps.executeQuery(sql);

            while (rs.next()) {
                EntDesc = rs.getString("enterprise_desc");
                System.out.println("Ent Desc: " + rs.getString("enterprise_desc"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
//                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return EntDesc;
    }

    public ArrayList<Image> getImageDetails() {

        ArrayList<Image> Ilist = new ArrayList<>();
        Statement stmt = null;
        Connection con = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmapp", "root", "password@1234");
            String sql = "select * from image where enterprise_id in (select enterprise_id from enterprise where enterprise_name='" + getEnterpriseName() + "')";

            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("Iname: " + rs.getString("imagename") + "\n Idesc:" + rs.getString("image_desc") + "\n Eicon:" + rs.getString("imagepath"));
//                String imagePath="C:\\Users\\s519351\\Documents\\NetBeansProjects\\FinalGDP\\web\\"+rs.getString("imagepath");
                Ilist.add(new Image(rs.getString("image_desc"), rs.getString("imagepath"), rs.getString("imagename")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Ilist;
    }

    public ArrayList<Video> getVideoDetails() {

        ArrayList<Video> Vlist = new ArrayList<>();
        Statement stmt = null;
        Connection con = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmapp", "root", "password@1234");
            String sql = "select * from video where enterprise_id in (select enterprise_id from enterprise where enterprise_name='" + getEnterpriseName() + "')";

            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("Vname: " + rs.getString("videoname") + "\n Vdesc:" + rs.getString("video_desc") + "\n Vpath:" + rs.getString("videopath"));
                Vlist.add(new Video(rs.getString("video_desc"), rs.getString("videopath"), rs.getString("videoname")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Vlist;
    }

}
