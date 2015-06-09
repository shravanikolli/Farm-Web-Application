package farm;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(urlPatterns = {"/ChangePasswordServlet"})
public class ChangePasswordServlet extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, SQLException {
//SESSION FROM LOGIN SERVLET IS USED HERE 
HttpSession session = request.getSession();
String userLogged =(String)session.getAttribute("userid");
ChangePasswordBean changePwdBean = new ChangePasswordBean();
ResultSet rs = changePwdBean.LoggedInUserDetails(userLogged);
String currentPassword =request.getParameter("current");
String passwordEntered =request.getParameter("new");
String passwordReEntered =request.getParameter("confirmnew");
while(rs.next())      
 {
String pwdLogged = rs.getString("password");
     System.out.println("password logged "+pwdLogged);
 if(! pwdLogged.equals(currentPassword))
 {
 request.setAttribute("incorrect", " current password is incorrect");
    request.getRequestDispatcher("/ChangePassword.jsp").forward(request, response);

 break;
 }
 if(pwdLogged.equals(currentPassword) && !passwordEntered.equals(passwordReEntered) )
 {
   request.setAttribute("incorrect", " new combination mismatch");
      request.getRequestDispatcher("/ChangePassword.jsp").forward(request, response);

    }
 
 if(pwdLogged.equals(currentPassword) && passwordEntered.equals(passwordReEntered) )
 {
     changePwdBean.insertIntoLogin(passwordEntered, userLogged);
 request.setAttribute("incorrect", " login successful");
 request.getRequestDispatcher("/Facilities.jsp").forward(request, response);
break;
 }
 }
rs.close();
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChangePasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChangePasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChangePasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChangePasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
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
