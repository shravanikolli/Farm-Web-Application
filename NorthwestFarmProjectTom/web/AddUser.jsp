

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
        <title>Add User</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link type="text/css" rel ="stylesheet" href="register.css"> 
        <link type="text/css" rel ="stylesheet" href="project.css">
                <script type="text/javascript" src="Settings.js"></script>

    </head>
    <body>
        
        <br><br>
        <br><br><br>
       
        <div id="adduserdiv">
            <form action="AddUSerServlet">
                <table> 
                    <th align="center">Add User</th>
                    <tr>
                        <td><label >&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspFirst Name </label></td>
                    </tr>
                    <tr  align="center">
                        <td><input type="text" name="firstname" required></td>
                    </tr>
                    <tr>
                        <td><label >&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Last Name </label></td>
                    </tr>
                    <tr  align="center">
                        <td><input type="text" name="lastname" required></td>
                    </tr>
                    <tr>
                        <td><label >&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp UserId </label></td>
                    </tr>
                    <tr  align="center">
                        <td><input type="text" name="userid" required></td>
                    </tr>
                    <tr>
                        <td><label> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspPassword </label></td>
                    </tr>
                    <tr  align="center">
                        <td><input type="password" name="password1" required></td>
                    </tr>
                    <tr>
                        <td><label> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspConfirm Password </label></td>
                    </tr>
                    <tr  align="center">
                        <td><input type="password" name="password2" required></td>
                    </tr>
                    <tr>
                        <td><label> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspAdmin Password </label></td>
                    </tr>
                    <tr  align="center">
                        <td><input type="password" name="adminpassword" required></td>
                    </tr>
                    
                    <tr>
                          <div id="error">     
 <% if(request.getAttribute("pwdcomb") != null)
{
out.println(request.getAttribute("pwdcomb"));
}
else{
out.println("");
}
%>  
                    </tr>
                    
                    <tr>
                        <td>
                            <br><input type="submit" value ="Add" id="addButton" name="cancel">
                            </form>
                    <form action="Facilities.jsp">
                            <input type="submit" value ="Cancel" id="clearButton" name="Cancel">
                            </form>
                          </td>
                   </tr>
                </table>

        </div>
        
    </body>
</html>
