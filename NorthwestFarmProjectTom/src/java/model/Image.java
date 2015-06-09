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
public class Image {
  private int enterpriseID;
  private String imageName;
  private int imageID;
  private String imagePath;
  private String imageDesc;
  
    public Image(){
  }
  
    public Image(String ImageDesc, String ImagePath, String ImageName) {
        this.imageDesc = ImageDesc;
        this.imagePath = ImagePath;
        this.imageName = ImageName;
    }

  
  
    public int getEnterpriseID() {
        return enterpriseID;
    }

    public void setEnterpriseID(int enterpriseID) {
        this.enterpriseID = enterpriseID;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageDesc() {
        return imageDesc;
    }

    public void setImageDesc(String imageDesc) {
        this.imageDesc = imageDesc;
    }

    @Override
    public boolean equals(Object o) {
        if(this.imageName.equals(((Image)o).imageName)){
            return true;
        }else{
            return false;
        }
    }

    
  
}
