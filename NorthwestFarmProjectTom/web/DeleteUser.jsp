<%-- 
    Document   : DeleteUser
    Created on : Jan 26, 2015, 3:35:19 AM
    Author     : S519459
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="farm.DeleteUserBean" %>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Delete User Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--<link  type="text/css" href="deleteUsers.css" rel="stylesheet">-->
        <link type="text/css" rel ="stylesheet" href="project.css">
        <!--<link type="text/css" rel ="stylesheet" href="deleteusers.css">-->
        <!--<script type="text/javascript" src="Settings.js"></script>-->

    </head>
    <body>
        
        <h1 align="center" style="color: white; font-family: calibri; font-size: 40px;">List Of Users</h1>
        <div id="deleteusersdivison"></div>
        <div class="users">
            <form  action="DeleteUserServlet" method=""POST>
                <table align="center">
               <% 
      ArrayList<String>  al =(ArrayList<String>)request.getAttribute("list");
      for(String userid:al){
                     %>

                     <tr> <td> <input type="checkbox" value="<%=userid%>" id="users" name="users"><%=userid%></tr>
  <%}%>
  </table>
     </div>     
<div id="error">     
 <% if(request.getAttribute("error") != null)
{
out.println(request.getAttribute("error"));
}
else{
out.println(" ");
}
%>
  <table align="center">
      <tr> <td>  
              <input type="submit" Value="Delete" id="deleteuserbutton" onclick="danger()"/>
                    </form>
        </td>
<td> 
    <form  action="Facilities.jsp" method=""POST>
             <input type="submit" Value="Cancel" id="canceldeleteuserbutton">
            </form></td> 
  </tr>
</table>
</div>

    </body>
</html>
