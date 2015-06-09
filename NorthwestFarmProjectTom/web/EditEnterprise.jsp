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
        <title>Edit Enterprise ${selectedEnterpriseDetails.enterpriseName}</title>
        <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
                <script src="imagePreview.js"></script>
                <link type="text/css" rel ="stylesheet" href="editEnterprise.css"> 
                    <link type="text/css" rel ="stylesheet" href="project.css">
                        <link type="text/css" rel="stylesheet" href="uploadbutton.css">
                            </head>
                            <body>

                                <h1 align="center">Edit Enterprise ${selectedEnterpriseDetails.enterpriseName}(${selectedFacilityDetails.facilityName})</h1>

                                <div id="editenterprisediv1" class="main">
                                    <br><br>
                                            <form method="post" action="EditEnterpriseServlet" enctype="multipart/form-data">
                                                ${isEnterpriseDuplicated}
                                                <table>
                                                    <tr>
                                                        <td>
                                                            Enterprise Name
                                                        </td>
                                                        <td>
                                                            <input type="text" name="enterpriseName" required value="${selectedEnterpriseDetails.enterpriseName}">
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            Enterprise Icon
                                                        </td>
                                                        <td>
                                                            <div class="choose_file">
                                                                <span >Choose File</span>

                                                                <input type="file" name="enterpriseIcon" accept="image/*" id="uploadImage" onchange="previewImage();"/>
                                                            </div>
                                                            &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp

                                                            <image src="${selectedEnterpriseDetails.enterpriseIcon}" alt="not available" style="width : 60px; height:40px;"/>
                                                            <image id="uploadPreview" style="width : 60px; height:40px;" src=""/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            Enterprise Description
                                                        </td>
                                                        <td>
                                                            <textarea rows="05" cols="23" name="enterpriseDescription">${selectedEnterpriseDetails.enterpriseDescription}</textarea>
                                                        </td>
                                                    </tr>
                                                </table>
                                                <br>



                                                    <br><br>

                                                            <input type="submit" value="Save Changes" id="savingenterprisebutton" name="saveOrCancel">
                                                                </form>
                                                        
                                                       
                                                        
                                                                <form action="EditFacility.jsp">
                                                                    <input type="submit" value="Cancel" id="cancelenterprisebutton" name="saveOrCancel">
                                                                </form>
                                                                </div>
                                                                <div id="editenterprisediv2" class="main">
                                                                    <!--<img id="imagemedia" >-->
                                                                    <form id="mediafiles" action="ModifyMediafilesServlet">
                                                                        <h2 align="center">Media Files</h2>
                                                                        <select id="mediaFilelist" size="6" name="mediaFiles">

                                                                            <c:forEach var="imageName" items="${imagesList}">
                                                                                <option value="${imageName}(image)" selected>${imageName}(image)</option>>
                                                                            </c:forEach>
                                                                            <c:forEach var="videoName" items="${videosList}">
                                                                                <option value="${videoName}(video)" selected>${videoName}(video)</option>>
                                                                            </c:forEach>

                                                                        </select>

                                                                        <br><br><br><br>
                                                                                        <input type="submit" value="Add New MediaFile" name="modifyMediafiles" id="addmediafilebuttonbutton">

                                                                                            <input type="submit" value="Edit MediaFile" id="editmediafilebutton" name="modifyMediafiles" >
                                                                                                <br>
                                                                                                    <input type="submit" value="Delete MediaFile" name="modifyMediafiles" id="deletemediafilebuttonbutton" onclick="alert('Are you sure you want to delete')">


                                                                                                        </form>
                                                                                                        </div>
                                                                                                        </body>
                                                                                                        </html>
