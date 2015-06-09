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
        <title>Edit Video</title>
        <link type="text/css" rel="stylesheet" href="project.css">
        <link type="text/css" rel="stylesheet" href="editMediaFile.css">
        <link type="text/css" rel="stylesheet" href="uploadbutton.css">
    </head>
    <body>
        <h1 align="center">Edit Video ${selectedVideo.videoName} of ${selectedEnterpriseDetails.enterpriseName}(${selectedFacilityDetails.facilityName})</h1>
        <div id="maindiv">

            <form method="post" action="EditVideoServlet" enctype="multipart/form-data">
                <br><br>
                ${isVideoDuplicated}
                <table>
                    <tr>
                        <td>
                            Edit Video
                        </td>
                        <td>
                            <div class="choose_file">
                                <span >Choose File</span>
                                <input type="file" id="uploadImage" accept="video/*" name="fileAddress" onchange="previewImage()"/>
                            </div>
                            &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Video Name
                        </td>
                        <td>

                            <input type="text" id="mediaName" name="videoName" value="${selectedVideo.videoName}">

                        </td>
                    </tr>
                    <tr>
                        <td>
                            Video Description
                        </td>
                        <td>
                            <textarea rows="05" cols="23" id="mediaDesc" name="videoDescription" >${selectedVideo.videoDesc}</textarea>
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
            <video width="320" height="240" controls> <source type="video/mp4" src=${selectedVideo.videoPath}   /></video>

        </div>
    </body>
</html>
