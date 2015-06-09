<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Add Enterprise</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="imagePreview.js"></script>
        <link type="text/css" rel ="stylesheet" href="addEnterprise.css"> 
        <link type="text/css" rel ="stylesheet" href="project.css">
            <link type="text/css" rel ="stylesheet" href="uploadbutton.css">
    </head>
    <body>
        <script src="Settings.js"></script>
        <br>
        <h1 align="center">Add Enterprise(${selectedFacilityDetails.facilityName})</h1>
        
        
        
       
       <div id="addenterprisediv">
            <br><br>
            <form method="post" action="AddEnterpriseServlet" enctype="multipart/form-data">
                ${isEnterpriseDuplicated}
            <table>
                <tr>
                    <td>
                        Enterprise Name
                    </td>
                    <td>
                        <input type="text" name="enterpriseName" required>
                    </td>
                </tr>
                <tr>
                    <td>
                        Enterprise Icon
                    </td>
                    <td>
                        <div class="choose_file">
                            <span >Choose File</span>
                        <input type="file" accept="image/*" id="uploadImage" onchange="previewImage();" name="enterpriseIcon" required/>
                        </div>
                        <br>
                  <!--      <span id="impath" style="color: black"></span>  -->
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <image id="uploadPreview" style="width : 60px; height:40px;" src="img/i-1.jpg"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Enterprise Description
                    </td>
                    <td>
                        <textarea rows="05" cols="23" name="enterpriseDescription"></textarea>
                    </td>
                </tr>
            </table>
            
            <br><br>
            
            <input type="submit" value="Save" id="addingenterprisebutton" name="saveOrCancelEnterprise">
            </form>
                   
            <form action="EditFacility.jsp">
            <input type="submit" value="Cancel" id="cancelenterprisebutton" name="saveOrCancelEnterprise">
           
        </div>
         </form>
        
    </body>
</html>
