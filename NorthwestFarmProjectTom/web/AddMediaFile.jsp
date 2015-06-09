<%-- 
    Document   : AddMediaFile
    Created on : Jan 30, 2015, 11:39:12 PM
    Author     : S519295
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="imagePreview.js"></script>
        <title>Add Media File</title>
        <link type="text/css" rel ="stylesheet" href="addmediafiles.css"> 
        <link type="text/css" rel ="stylesheet" href="project.css">
             <link type="text/css" rel="stylesheet" href="uploadbutton.css">
    </head>
    <body>
        <h1 align="center">Add Media File</h1>
        <div id="editmediafilediv">
       
            <form method="post" action="AddMediaFileServlet" enctype="multipart/form-data">
                ${isMediafileDuplicated}
         <table>
             <br>
                    <tr>
                        <td><input type="radio" id="inputradio" name="fileType" value="image">Image</td>
                        <td><input type="radio" id="inputradio" name="fileType" value="video">Video</td>
                    </tr>
                    <tr>
                        <td>
                            Add Image/Video
                        </td>
                        <td>
                       <!--   <input type="file" id="vimg" accept="image/*, video/*" name="fileAddress"/>  -->
                       <div class="choose_file">
                            <span >Choose File</span>
                       <input type="file"  id="uploadImage" accept="image/*, video/*" name="fileAddress" onchange="previewImage()" required/>
                        &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                       </div>
                             
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Image/Video Name
                        </td>
                        <td>
                            <input type="text" id="mediaName" name="fileName" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Image/Video Description
                        </td>
                        <td>
                            <textarea rows="05" cols="23" id="mediaDesc" name="fileDescription"></textarea>
                        </td>
                    </tr>
                     <tr>
                        <td><br></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Save" id="savemediafilebutton"></td>
                    
                        </form>
           
        <form action="EditEnterprise.jsp">
                        <td><input type="submit" value="Cancel" id="cancelmediafilebutton"></td>
                        </form>
                    </tr>    
                </table>
            </div>
    </body>
</html>
