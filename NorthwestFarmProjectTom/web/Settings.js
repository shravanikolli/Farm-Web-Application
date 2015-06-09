/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//please don't delete this this is dropdown of first one that vishnu made
//document.write('<div id="navMenu"><ul><li><a href="#" style="background-color: #7bc1f7; color: white;text-align: center;">Account Settings</a><ul><li> <a href="facilities.html">Home</a></li><li> <a href="changeExistingPassword.html">Change Password</a></li><li> <a href="register.html">Add User</a></li><li> <a href="facilities.html">Delete User</a></li><li> <a href="login.html">Log Out</a></li></ul></li></ul></div>');
var userChecker=document.getElementById("one").value;
document.writeln('<html><head><meta charset="UTF-8">\n\
<meta name="viewport" content="width=device-width, initial-scale=1.0">\n\
<link type="text/css" rel ="stylesheet" href="project.css">\n\
<link href="dropdown.css" rel="stylesheet">\n\
<link href="dropdown1.css" rel="stylesheet">\n\
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js">\n\
</script><script type="text/javascript" src="accountsettings.js"></script></head>\n\
<body>\n\
<div class="click-nav"><ul class="no-js1"><li><a class="clicker">\n\
<img src="img/i-3.png" alt="Icon">Account Settings</a><ul><li><a href="Facilities.jsp">\n\
<img src="img/i-2.png" alt="Icon">Home</a></li><li><a href="ChangePassword.jsp">\n\
<img src="img/i-4.png" alt="Icon">Change Password</a></li><li><a href="ChangeProfile.jsp">\n\
<img src="img/i-4.png" alt="Icon">Change Profile</a></li><li><a href="LoginPage.jsp">\n\
<img src="img/i-6.png" alt="Icon">Sign out</a></li></ul> </li></ul></div> \n\
<div class="click-nav1" style="visibility: '+userChecker+'" ><ul class="no-js2"  ><li> <a class="clicker1">Manage Users<img src="img/i-1.png" alt="Icon"></a><ul><li><a href="AddUser.jsp"><img src="img/i-4.png" alt="Icon">Add User</a></li><li><a href="TakeToDeleteUserServlet"><img src="img/i-4.png" alt="Icon">Delete User</a></li></ul></li></ul></div></body></html>');
