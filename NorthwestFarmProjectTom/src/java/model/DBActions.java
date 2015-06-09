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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author S519295
 */
public class DBActions {
    public final String URL = "jdbc:mysql://localhost/farmapp";
    public final String USER = "root";
    public final String PASSWORD = "password@1234";
    public static final String PATH="s519351";
    public static final String NAME="NorthwestFarmProjectTom";
    public void addNewFacility(Facility facilityObj){
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
          Connection  con = null;
          PreparedStatement pstmt = null;
        try {
            con = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "insert into FACILITY(FACILITY_NAME, FACILITY_DESC, LATITUDE, LONGITUDE, DIRECTIONS) values(?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, facilityObj.getFacilityName());
            pstmt.setString(2, facilityObj.getFacilityDescription());
            pstmt.setDouble(3, facilityObj.getLatitude());
            pstmt.setDouble(4, facilityObj.getLongitude());
            pstmt.setString(5, facilityObj.getDirections());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                pstmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
       
    }
    public ArrayList<String> getFacilitiesList(){
        ArrayList<String> facilityList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
          Connection  con = null;
          PreparedStatement pstmt = null;
        try {
            con = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "select FACILITY_NAME from FACILITY";
            pstmt = con.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();
            while(resultSet.next()){
              facilityList.add(resultSet.getString(1));
          }
        
        } catch (SQLException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                pstmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
          return facilityList;
    }
    public Facility getFacilityDetails(int selectedFacilityId){
        Facility facility = new Facility();
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
          Connection  con = null;
          PreparedStatement pstmt = null;
        try {
            con = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
            //To get existing facility data except enterprises list.
            String query = "select FACILITY_ID, FACILITY_NAME, FACILITY_DESC, LATITUDE, LONGITUDE, DIRECTIONS from FACILITY where FACILITY_ID = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, selectedFacilityId);
            ResultSet resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                System.out.println("got facility details");
                int facID = resultSet.getInt("FACILITY_ID");
                System.out.println(facID);
              facility.setFacilityID(facID);
              facility.setFacilityName(resultSet.getString("FACILITY_NAME"));
              facility.setFacilityDescription(resultSet.getString("FACILITY_DESC"));
              facility.setLatitude(resultSet.getDouble("LATITUDE"));
              facility.setLongitude(resultSet.getDouble("LONGITUDE"));
              facility.setDirections(resultSet.getString("DIRECTIONS"));
              
          }
            if(facility.getFacilityID() != 0){
                System.out.println("facility id is not null");
                String query2 = "select ENTERPRISE_ID, FACILITY_ID, ENTERPRISE_NAME, ENTERPRISE_ICON, ENTERPRISE_DESC from ENTERPRISE where FACILITY_ID = ?";
                pstmt = con.prepareStatement(query2);
                pstmt.setInt(1, facility.getFacilityID());
                resultSet = pstmt.executeQuery();
                while(resultSet.next()){
                    Enterprise enterprise = new Enterprise();
                    enterprise.setEnterpriseID(resultSet.getInt("ENTERPRISE_ID"));
                    enterprise.setFacilityID(resultSet.getInt("FACILITY_ID"));
                    enterprise.setEnterpriseName(resultSet.getString("ENTERPRISE_NAME"));
                    enterprise.setEnterpriseIcon(resultSet.getString("ENTERPRISE_ICON"));
                    enterprise.setEnterpriseDescription(resultSet.getString("ENTERPRISE_DESC"));
                    facility.getEnterprisesList().add(enterprise);
                    System.out.println("entered into enterprise area");
                    
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
                }
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return facility;
    }
    public void updateFacilityDetails(int facilityID, Facility facility){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
          Connection  con = null;
          PreparedStatement pstmt = null;
        try {
            con = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "update FACILITY set FACILITY_NAME = ?, FACILITY_DESC = ?, LATITUDE = ?, LONGITUDE = ?, DIRECTIONS = ? where FACILITY_ID = ?";
            
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, facility.getFacilityName());
            System.out.println(facility.getFacilityName());
            System.out.println(facilityID);
            pstmt.setString(2, facility.getFacilityDescription().trim());
            pstmt.setDouble(3, facility.getLatitude());
            pstmt.setDouble(4, facility.getLongitude());
            pstmt.setString(5, facility.getDirections().trim());
            pstmt.setInt(6, facilityID);
            pstmt.executeUpdate();
              System.out.println("updated");
        } catch (SQLException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                pstmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    public void addNewEnterprise(Enterprise enterprise){        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
          Connection  con = null;
          PreparedStatement pstmt = null;
        try {
            con = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
                String query = "insert into Enterprise(facility_Id, enterprise_Name, enterprise_Icon, enterprise_Desc) values(?, ?, ?, ?)";
           pstmt = con.prepareStatement(query);
           pstmt.setInt(1, enterprise.getFacilityID());
           pstmt.setString(2, enterprise.getEnterpriseName());
           pstmt.setString(3, enterprise.getEnterpriseIcon());
           pstmt.setString(4, enterprise.getEnterpriseDescription());
          pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
                }
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public Enterprise getEnterpriseDetails(int facilityId, String enterpriseName){
     
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
          Connection  con = null;
          PreparedStatement pstmt = null;
           Enterprise enterprise = null;
        try {
            con = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
           
                String query = "select ENTERPRISE_ID, FACILITY_ID, ENTERPRISE_NAME, ENTERPRISE_ICON, ENTERPRISE_DESC from ENTERPRISE where FACILITY_ID = ? and ENTERPRISE_NAME = ?";
                pstmt = con.prepareStatement(query);
                pstmt.setInt(1, facilityId);
                pstmt.setString(2, enterpriseName);
                ResultSet resultSet = pstmt.executeQuery();
                while(resultSet.next()){
                    enterprise = new Enterprise();
                    enterprise.setEnterpriseID(resultSet.getInt("ENTERPRISE_ID"));
                    enterprise.setFacilityID(resultSet.getInt("FACILITY_ID"));
                    enterprise.setEnterpriseName(resultSet.getString("ENTERPRISE_NAME"));
                    enterprise.setEnterpriseIcon(resultSet.getString("ENTERPRISE_ICON"));
                    enterprise.setEnterpriseDescription(resultSet.getString("ENTERPRISE_DESC"));
                    
                    
                }
           
            
        } catch (SQLException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
                }
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return enterprise ;
    }
    public int getSelectedFacilityId(String facilityName){
        int facilityId = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
          Connection  con = null;
          PreparedStatement pstmt = null;
        try {
            con = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "select FACILITY_ID from FACILITY where FACILITY_NAME = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, facilityName);
            ResultSet resultSet = pstmt.executeQuery();
            while(resultSet.next()){
              facilityId = resultSet.getInt(1);
          }
        } catch (SQLException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                pstmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return facilityId;
    }
    public Image getImageDetails(int enterpriseId, String imageName){
        Image image = new Image();
         try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
          Connection  con = null;
          PreparedStatement pstmt = null;
        try {
            con = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "select IMAGE_ID, ENTERPRISE_ID, IMAGENAME, IMAGEPATH, IMAGE_DESC from IMAGE where ENTERPRISE_ID = ? and IMAGENAME = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, enterpriseId);
            pstmt.setString(2, imageName);
            ResultSet resultSet = pstmt.executeQuery();
            while(resultSet.next()){
              image.setImageID(resultSet.getInt("IMAGE_ID"));
              image.setEnterpriseID(resultSet.getInt("ENTERPRISE_ID"));
              image.setImageName(resultSet.getString("IMAGENAME"));
              image.setImagePath(resultSet.getString("IMAGEPATH"));
              image.setImageDesc(resultSet.getString("IMAGE_DESC"));
          }
        } catch (SQLException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                pstmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return image;
    }
    public Video getVideoDetails(int enterpriseId, String videoName){
        Video video = new Video();
         try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
          Connection  con = null;
          PreparedStatement pstmt = null;
        try {
            con = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "select VIDEO_ID, ENTERPRISE_ID, VIDEONAME, VIDEOPATH, VIDEO_DESC from VIDEO where ENTERPRISE_ID = ? and VIDEONAME = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, enterpriseId);
            pstmt.setString(2, videoName);
            ResultSet resultSet = pstmt.executeQuery();
            while(resultSet.next()){
              video.setVideoID(resultSet.getInt("VIDEO_ID"));
              video.setEnterpriseID(resultSet.getInt("ENTERPRISE_ID"));
              video.setVideoName(resultSet.getString("VIDEONAME"));
              video.setVideoPath(resultSet.getString("VIDEOPATH"));
              video.setVideoDesc(resultSet.getString("VIDEO_DESC"));
          }
        } catch (SQLException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                pstmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return video;
    }
    public void addImageToEnterprise(int enterpriseId, Image image){
        
         try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
          Connection  con;
          PreparedStatement pstmt;
        try {
            con = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "INSERT INTO IMAGE(ENTERPRISE_ID, IMAGENAME, IMAGEPATH, IMAGE_DESC) VALUES(?, ?, ?, ?)";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, enterpriseId);
            pstmt.setString(2, image.getImageName());
            pstmt.setString(3, image.getImagePath());
            pstmt.setString(4, image.getImageDesc());
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void addVideoToEnterprise(int enterpriseId, Video video){
          try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
          Connection  con = null;
          PreparedStatement pstmt = null;
        try {
            con = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "INSERT INTO VIDEO(ENTERPRISE_ID, VIDEONAME, VIDEOPATH, VIDEO_DESC) VALUES(?, ?, ?, ?)";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, enterpriseId);
            pstmt.setString(2, video.getVideoName());
            pstmt.setString(3, video.getVideoPath());
            pstmt.setString(4, video.getVideoDesc());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                pstmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    public ArrayList<String> getImageNamesOfSelectedEnterprise(int enterpriseId){
        ArrayList<String> imagesList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
          Connection  con = null;
          PreparedStatement pstmt = null;
        try {
            con = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "select IMAGENAME from IMAGE where ENTERPRISE_ID = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, enterpriseId);
            ResultSet resultSet = pstmt.executeQuery();
            while(resultSet.next()){
              imagesList.add(resultSet.getString(1));
          }
        } catch (SQLException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                pstmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return imagesList;
    }
    public ArrayList<String> getVideoNamesOfSelectedEnterprise(int enterpriseId){
        ArrayList<String> videosList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
          Connection  con = null;
          PreparedStatement pstmt = null;
        try {
            con = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "select VIDEONAME from VIDEO where ENTERPRISE_ID = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, enterpriseId);
            ResultSet resultSet = pstmt.executeQuery();
            while(resultSet.next()){
              videosList.add(resultSet.getString(1));
          }
        } catch (SQLException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                pstmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return videosList;
    }
    public void updateImageDetails(int imageId, int enterpriseId, Image image){
         try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
          Connection  con = null;
          PreparedStatement pstmt = null;
        try {
            con = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "update IMAGE set IMAGENAME = ?, IMAGEPATH = ?, IMAGE_DESC = ? where IMAGE_ID = ? AND ENTERPRISE_ID = ?";
            
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, image.getImageName());
            pstmt.setString(2, image.getImagePath());
            pstmt.setString(3, image.getImageDesc());
            pstmt.setInt(4, imageId);
            pstmt.setInt(5, enterpriseId);
            pstmt.executeUpdate();
              System.out.println("image updated");
        } catch (SQLException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                pstmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
     public void updateVideoDetails(int videoId, int enterpriseId, Video video){
         try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
          Connection  con = null;
          PreparedStatement pstmt = null;
        try {
            con = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "update VIDEO set VIDEONAME = ?, VIDEOPATH = ?, VIDEO_DESC = ? where VIDEO_ID = ? AND ENTERPRISE_ID = ?";
            
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, video.getVideoName());
            pstmt.setString(2, video.getVideoPath());
            pstmt.setString(3, video.getVideoDesc());
            pstmt.setInt(4, videoId);
            pstmt.setInt(5, enterpriseId);
            pstmt.executeUpdate();
              System.out.println("video updated");
        } catch (SQLException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                pstmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    public void deleteFacility(String facilityName){
         try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
          Connection  con = null;
          PreparedStatement pstmt = null;
        try {
            con = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
                String query = "delete from FACILITY where FACILITY_NAME = ?";
           pstmt = con.prepareStatement(query);
           pstmt.setString(1, facilityName);
          pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
                }
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void deleteEnterprise(int facilityId, String enterpriseName){
           try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
          Connection  con = null;
          PreparedStatement pstmt = null;
        try {
            con = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
                String query = "delete from ENTERPRISE where FACILITY_ID = ? AND ENTERPRISE_NAME = ?";
           pstmt = con.prepareStatement(query);
          pstmt.setInt(1, facilityId);
          pstmt.setString(2, enterpriseName);
          pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
                }
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void deleteImage(int enterpriseId, String imageName){
           try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
          Connection  con = null;
          PreparedStatement pstmt = null;
        try {
            con = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
                String query = "delete from IMAGE where ENTERPRISE_ID = ? AND IMAGENAME = ?";
           pstmt = con.prepareStatement(query);
           pstmt.setInt(1, enterpriseId);
           pstmt.setString(2, imageName);
          pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
                }
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void deleteVideo(int enterpriseId, String videoName){
           try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
          Connection  con = null;
          PreparedStatement pstmt = null;
        try {
            con = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
                String query = "delete from VIDEO where ENTERPRISE_ID = ? AND VIDEONAME = ?";
           pstmt = con.prepareStatement(query);
           pstmt.setInt(1, enterpriseId);
           pstmt.setString(2, videoName);
          pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
                }
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void updateEnterpriseDetails(int enterpriseId, Enterprise enterprise){
            try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
          Connection  con = null;
          PreparedStatement pstmt = null;
        try {
            con = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
                String query = "update ENTERPRISE set ENTERPRISE_NAME = ?, ENTERPRISE_ICON = ?, ENTERPRISE_DESC = ? where ENTERPRISE_ID = ?";
           pstmt = con.prepareStatement(query);
           pstmt.setString(1, enterprise.getEnterpriseName());
           pstmt.setString(2, enterprise.getEnterpriseIcon());
           pstmt.setString(3, enterprise.getEnterpriseDescription());
           pstmt.setInt(4, enterpriseId);
          pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
                }
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBActions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
