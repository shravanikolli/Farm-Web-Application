package farm;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author s519459
 */
@WebServlet(urlPatterns = {"/AddUSerServlet"})
public class AddUSerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
  String firstname =request.getParameter("firstname");
  String lastname =request.getParameter("lastname");
  String password1 =request.getParameter("password1");
   String password2 =request.getParameter("password2");
  String userid =request.getParameter("userid");
  String adminpassword =request.getParameter("adminpassword");
  String cancel= request.getParameter("cancel");
  System.out.println(" first----------"+firstname.equals(""));
  int superuser =0;
 AddUserBean add = new AddUserBean();
//SESSION FOR USERID IS BEING USED
 HttpSession session =request.getSession();
 String userLoggedIn = (String) session.getAttribute("userid");
// System.out.println(" user logged on "+userLoggedIn);
// System.out.println("user logged in value from session in AddUserServlet is "+userLoggedIn);
 ArrayList<String> list = add.UserLoggedOnDetails(userLoggedIn);
 String passwordObtained = list.get(0);
 if(cancel!=null  && cancel.equals("Add"))
  
         if(passwordObtained.equals(adminpassword))
         {
             if(password1.equals(password2))
             {
 int res = add.AddUser(userid, password2,firstname,lastname,superuser);
//        System.out.println(" insertion of new user- in deltee user servlet----------- "+res); 
        if(res==0){
                         request.setAttribute("pwdcomb", "Similar Userid already exists");
        request.getRequestDispatcher("/AddUser.jsp").forward(request, response);}
        else
        request.getRequestDispatcher("/Facilities.jsp").forward(request, response);
        
             }
             else
             {    
             request.setAttribute("pwdcomb", "Password for new user mismatch");
             request.getRequestDispatcher("/AddUser.jsp").forward(request, response);
             } 
         }
         else{
             request.setAttribute("pwdcomb", "Password for admin mismatch");
             request.getRequestDispatcher("/AddUser.jsp").forward(request, response);        
         
         }
      

      System.out.println(" cancel value "+cancel);
  if(cancel.equals("cancel") && cancel!=null)
  {
  //System.out.println("cancel called at last");
  //response.sendRedirect("/GDPP/faces/Allfacilities.jsp");
  request.getRequestDispatcher("/Facilities.jsp").forward(request, response);
  }
  else
      cancel="asd";
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
            Logger.getLogger(AddUSerServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddUSerServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AddUSerServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddUSerServlet.class.getName()).log(Level.SEVERE, null, ex);
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
