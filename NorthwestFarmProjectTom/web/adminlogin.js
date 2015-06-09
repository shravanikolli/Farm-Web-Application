/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function loginDetails() {
    
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    if(username == "admin" && password == "admin")
    {             
       
        window.location.assign("facilities.html");
    }
    
    else if(username == "scott" && password =="scott")
    {
        window.location.assign("Ufacilities.html");
    }
   else
    {
        alert("please enter correct username and password");
    }
   
}

window.onload = function() {
       document.getElementById("Log In").onclick = loginDetails;
}

