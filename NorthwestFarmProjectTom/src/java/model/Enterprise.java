/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author S519459
 */
public class Enterprise {
    private int enterpriseID;
    private String enterpriseName;
    private int facilityID;
    private String enterpriseIcon;
    private String enterpriseDescription;
    
      public Enterprise(){
    
    }
    
   public Enterprise(String name, String description, String icon) {
        this.enterpriseName = name;
        this.enterpriseDescription = description;
        this.enterpriseIcon = icon;
    }

    public int getEnterpriseID() {
        return enterpriseID;
    }

    public void setEnterpriseID(int enterpriseID) {
        this.enterpriseID = enterpriseID;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public int getFacilityID() {
        return facilityID;
    }

    public void setFacilityID(int facilityID) {
        this.facilityID = facilityID;
    }

    public String getEnterpriseIcon() {
        return enterpriseIcon;
    }

    public void setEnterpriseIcon(String enterpriseIcon) {
        this.enterpriseIcon = enterpriseIcon;
    }

    public String getEnterpriseDescription() {
        return enterpriseDescription;
    }

    public void setEnterpriseDescription(String enterpriseDescription) {
        this.enterpriseDescription = enterpriseDescription;
    }

    @Override
    public boolean equals(Object o) {
        if(this.enterpriseName.equals(((Enterprise)o).enterpriseName)){
            return true;
        }else{
            return false;
        }
    }
    
}
