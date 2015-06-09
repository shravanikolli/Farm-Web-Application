/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;

/**
 *
 * @author S519459
 */
public class Facility {
    
private int facilityID;
private String facilityName;
private String facilityDescription;
private double latitude;
private double longitude;
private String directions;
private ArrayList<Enterprise> enterprisesList;
public Facility(){
    enterprisesList = new ArrayList<>();
}

  public Facility(String name, String description, float latitude, float longitude, String directions) {
        this.facilityName = name;
        this.facilityDescription = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.directions = directions;
    }
  
  public Facility(String name){
      this.facilityName = name;
  }

    public int getFacilityID() {
        return facilityID;
    }

    public void setFacilityID(int facilityID) {
        this.facilityID = facilityID;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getFacilityDescription() {
        return facilityDescription;
    }

    public void setFacilityDescription(String facilityDescription) {
        this.facilityDescription = facilityDescription;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public ArrayList<Enterprise> getEnterprisesList() {
        return enterprisesList;
    }

    public void setEnterprisesList(ArrayList<Enterprise> enterprisesList) {
        this.enterprisesList = enterprisesList;
    }
 
    
}
