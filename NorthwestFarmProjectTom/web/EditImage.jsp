<%-- 
    Document   : EditImage
    Created on : Feb 1, 2015, 12:01:59 AM
    Author     : S519295
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Edit Image</title>
        <script  type="text/javascript" src="imagePreview.js"></script>
        <link type="text/css" rel="stylesheet" href="project.css">
        <link type="text/css" rel="stylesheet" href="editMediaFile.css">
        <link type="text/css" rel="stylesheet" href="uploadbutton.css">
        
        
    </head>
    <body>
    <h1 align="center">Edit Image ${selectedImage.imageName} of ${selectedEnterpriseDetails.enterpriseName}(${selectedFacilityDetails.facilityName})</h1>
        <div id="maindiv">
            <br>
            <form method="post" action="EditImageServlet" enctype="multipart/form-data">
                ${isMediafileDuplicated}
         <table>
            <!-- <tr><img src="${selectedImage.imagePath}" alt="not available"></tr>-->
                    <tr>
                        <td>
                            Edit Image
                        </td>
                        <td>
                            <div class="choose_file">
                            <span >Choose File</span>
                            <input type="file" id="uploadImage" accept="image/*" name="fileAddress" onchange="previewImage()"/>
                            </div>
                        &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Image Name
                        </td>
                        <td>
                            <input type="text" id="mediaName" name="imageName" value="${selectedImage.imageName}">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Image Description
                        </td>
                        <td>
                            <textarea rows="05" cols="23" id="mediaDesc" name="imageDescription" >${selectedImage.imageDesc}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td><br></td>
                    </tr>
                    <tr>
                        <td><input type="submit" id="saveeditimage" value="Save" ></td>
                        </form>
                      
                        
        <form action="EditEnterprise.jsp">
                        <td><input type="submit" id="canceleditimage" value="Cancel"></td>
                        </form>
                    </tr>
                    
                </table>
        </div>
                        <div id="preview">
                            <image id="uploadPreview" src="${selectedImage.imagePath}"/>
                        </div>
      
    </body>
</html>
