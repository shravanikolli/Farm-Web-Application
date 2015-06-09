<%-- 
    Document   : LoginPage
    Created on : Jan 17, 2015, 5:21:02 PM
    Author     : S519459
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Welcome</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
       
        <link type="text/css" rel ="stylesheet" href="project.css">
        <link type="text/css" rel="stylesheet" href="register.css">
        
    </head>
    <body>
        <br>
       <h1 align='center'>Please Enter your Mail Id so that We can send a New Password </h1>
        <div id="forgotpwddivison">
            <form action="ForgotPwdServlet"  method="POST">
                <table> 
                    <th align="center">Forgot Password </th>
                    <tr><td></td></tr><tr><td></td></tr>
                   <tr>
                        <td><label >&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp User Id </label></td>
                    </tr>
                    <tr  align="center">
                        <td><input type="text" name="username" ></td>
                    </tr>
                    <tr>
                        <td><label> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspMail Id </label></td>
                    </tr>
                    <tr  align="center">
                        <td><input type="email" name="mailid"></td>
                    </tr>
                </table>
            <br><br><br><br><br><br><br><br><br><br><br>
            <div id="error">     
 <% if(request.getAttribute("message") != null)
{
out.println(request.getAttribute("message"));
}
else{
out.println("");
}
%>
<br>
</div> 
<table id="tbottom">
    <tr>   <input type="submit" value ="Send" id="addButton">
            </form>

      
 <form action="LoginPage.jsp">
         <input type="submit" value ="Cancel" id="addButton">
</form>
     </tr>
</table>
<br><br>
 
    </body>
</html>