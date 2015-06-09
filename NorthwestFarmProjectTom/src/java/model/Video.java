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
public class Video {
   private int videoID;
   private int enterpriseID;
   private String videoName;
   private String videoPath;
   private String videoDesc;
   
     public Video() {
    }

    public Video(String VideoDesc, String VideoPath, String VideoName) {
        this.videoDesc = VideoDesc;
        this.videoPath = VideoPath;
        this.videoName = VideoName;
    }


    public int getVideoID() {
        return videoID;
    }

    public void setVideoID(int videoID) {
        this.videoID = videoID;
    }

    public int getEnterpriseID() {
        return enterpriseID;
    }

    public void setEnterpriseID(int enterpriseID) {
        this.enterpriseID = enterpriseID;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    @Override
    public boolean equals(Object o) {
        return this.videoName.equals(((Video)o).videoName);
    }
   
}
