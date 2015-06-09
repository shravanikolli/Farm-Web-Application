package farm;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author S519459
 */
@WebServlet(urlPatterns = {"/ForgotPwdServlet"})
public class ForgotPwdServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, MessagingException, ClassNotFoundException, SQLException {
String usernameEntered = request.getParameter("username");
String userMailEntered =request.getParameter("mailid"); 
String password = "amraulnlaadydy";
String from ="mbaddam989@gmail.com";
String subject="Your Original Password For North Farm";
String messageText="";
String Cc="";
String pwdObtained = null;
String mailRegistered = "xxxxxxxxxxx";
LoginBean dbConnection = new LoginBean();
ResultSet  rs = dbConnection.getLoggedInUsersList();
String usernamRegistered="";
boolean value = false;
while( rs.next())
{
///System.out.println(" result set is "+rs.getString("userid"));
usernamRegistered=rs.getString("userid");
System.out.println("user registered is "+usernamRegistered);
if( usernamRegistered.equals(usernameEntered))
{
value=true;
 mailRegistered =rs.getString("emailid");
 pwdObtained =rs.getString("password");

break;
}
}
  
  if(value==false){
    request.setAttribute("message", "incorrect userid");  
   request.getRequestDispatcher("/ForgotPassword.jsp").forward(request, response);
      System.out.println(" incorrect user id");

         }


 if(value)
 {

 if(mailRegistered.equals(userMailEntered))
 { 

     System.out.println(" mail matches ");
ForgotPasswordGoogleMail.Send(from, password, mailRegistered,Cc ,subject, pwdObtained);
request.setAttribute("message", "The password has been sent to your mail");
 request.getRequestDispatcher("/LoginPage.jsp").forward(request, response);
 }
 else
 {

   String  message ="Registered mail doesnt match";
     System.out.println(" messgae is "+message);
    request.setAttribute("message", message);
 // response.sendRedirect("ForgotPassword.jsp");
  request.getRequestDispatcher("/ForgotPassword.jsp").forward(request, response);
     //String value =request.getParameter("message");
 }

}

}
     
        
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (MessagingException ex) {
            Logger.getLogger(ForgotPwdServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ForgotPwdServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ForgotPwdServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (MessagingException ex) {
            Logger.getLogger(ForgotPwdServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ForgotPwdServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ForgotPwdServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
