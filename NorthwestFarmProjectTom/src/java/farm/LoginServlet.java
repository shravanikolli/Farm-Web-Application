/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farm;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DBActions;

/**
 *
 * @author S519295
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
       System.out.println("&&&&&&&&&&&&&&&&&&&&&**************************");
             System.out.println("------------------------------***************8");

 String usernameEntered =request.getParameter("userid1");
 String passwordEntered =request.getParameter("password1");
      System.out.println("------------------------------***************8");

        System.out.println(usernameEntered);
        System.out.println(passwordEntered);
 if(usernameEntered.equals("") || "".equals(passwordEntered))
 {
     System.out.println("------------------------------***************8");
 request.setAttribute("message12", "Please fill the required fileds");
 request.getRequestDispatcher("/LoginPage.jsp").forward(request, response);
 }
 else{
String pwdObtained;
LoginBean lg = new LoginBean();
ResultSet rs = lg.getLoggedInUsersList();
int count=0;
 while(rs.next())      
 {
 String username=rs.getString("userid");
 if( username.equals(usernameEntered))
 {
     count++;
 pwdObtained =rs.getString("password");
     System.out.println(pwdObtained+""+passwordEntered);
 if(pwdObtained.equals(passwordEntered))
 { 
 String IsSupperUser = rs.getString("issuperuser");
 System.out.println(" super user value  "+IsSupperUser);
// if(IsSupperUser.equals("1"))
//    response.sendRedirect("Allfacilities.jsp");
// else
 //DBActions actions = new DBActions();
//ArrayList<String>  facilitiesList = actions.getFacilitiesList();

   HttpSession session =  request.getSession();
     System.out.println("-------44");
  session.setAttribute("superuser", IsSupperUser);
  session.setAttribute("userid", username);
   DBActions dbActions = new DBActions();
       // HttpSession session = request.getSession();
        ArrayList<String> facilitiesList = dbActions.getFacilitiesList(); 
        session.setAttribute("userChecker", IsSupperUser.equals("1")?"show":"hidden");
        System.out.println( (session.getAttribute("userChecker"))+("**************"));
        System.out.println("---------------------------------------------");
        session.setAttribute("facilitiesList", facilitiesList);
        session.setAttribute("facilityChecker", facilitiesList.isEmpty()?"disabled":"");
       request.getRequestDispatcher("Facilities.jsp").forward(request, response);
 //SESSION OBJECT IS CREATED HERE FOR FUTURE USE
  
 }
 else
 {
 String  message ="Wrong  Password";
 request.setAttribute("message12", message);
request.getRequestDispatcher("/LoginPage.jsp").forward(request, response);
 }
 }

 } 
  if(count==0)
 {
  request.setAttribute("message12", "Username is incorrect");
 request.getRequestDispatcher("/LoginPage.jsp").forward(request, response);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
