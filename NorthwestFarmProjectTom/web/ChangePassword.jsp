<%-- 
    Document   : ChangePassword
    Created on : Jan 19, 2015, 6:39:48 PM
    Author     : S519459
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Change Existing Password</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link type="text/css" rel ="stylesheet" href="changeExistingPassword.css"> 
        <link type="text/css" rel ="stylesheet" href="project.css">
      
    
        <script type="text/javascript" src="accountsettings.js"></script>
    </head>
    <h1 align="center">Change Existing Password</h1>
    <div id="changeexistingpwddiv">
        
            <form action="ChangePasswordServlet">
                <table> 
                    <th align="center">Change Password</th>
                    <tr>
                        <td><label >&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Current Password </label></td>
                    </tr>
                    <tr  align="center">
                        <td><input type="password" id="currentpassword" name="current"></td>
                    </tr>
                    <tr>
                        <td><label >&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp New Password </label></td>
                    </tr>
                    <tr  align="center">
                        <td><input type="password" id="changenewpassword" name="new"></td>
                    </tr>
                    <tr>
                        <td><label >&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Confirm Password  </label></td>
                    </tr>
                    <tr  align="center">
                        <td><input type="password" id="conformnewpassword" name="confirmnew"></td>
                    </tr>
                    
                </table>
        <br>
        <div id="incorrect">
          <% if(request.getAttribute("incorrect") != null)
{
out.println(request.getAttribute("incorrect"));
}
else{
out.println("");
}
%>   
        </div>
                <input type="submit" value ="Submit" id="changecurrentpasswordButton">
            </form>
        <form action="Facilities.jsp" method="POST">
                <input type="submit" value ="Cancel" id="cancelcurrentpasswordchangeButton">
            </form>
        </div>
    </body>
</html>

