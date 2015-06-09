<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Welcome</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link type="text/css" rel ="stylesheet" href="project.css">
        <link type="text/css" rel ="stylesheet" href="facilities.css">
        <script type="text/javascript" src="accountsettings.js"></script>
    </head>
    <body>
    <input type="hidden" value=${userChecker} id="one"/>
     <script type="text/javascript" src="Settings.js" ></script>
        <br><br><br><br>
             <h1 >Facilities</h1>
             
        <div id="facilitydiv">
            <br>
            <form action="AddNewFacility.jsp">
            <input type="submit" value="Add Facility" id="addfacilitybutton">
            </form>
            <br><br>
            <form action="EditOrDeleteFacilityServlet">
                <select name="Facilities" size="8" id="facilitylist">
                    <c:forEach var="facilityName" items="${facilitiesList}">
                        <option value="${facilityName}" selected>${facilityName}</option>>
                    </c:forEach>
                </select>
            <br><br>
            
            <input type="submit" value="Edit Facility" id="editfacilitybutton" name="editOrDeleteFacility" />
            
            
                <input type="submit" value="Delete Facility" id="deletefacilitybutton" name ="editOrDeleteFacility"  />
            
         </div> 
            
    </body>
</html>
