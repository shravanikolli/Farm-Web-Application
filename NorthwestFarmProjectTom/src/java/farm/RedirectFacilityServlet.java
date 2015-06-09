package farm;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import farm.FacilityBean;
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

/**
 *
 * @author S519459
 */
@WebServlet(urlPatterns = {"/RedirectFacilityServlet"})
public class RedirectFacilityServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
   String redirect =request.getParameter("redirect");
   String facilityName =request.getParameter("facilityN");
        System.out.println(" facility name is "+facilityName);
   if(facilityName==null)
   {
  request.setAttribute("choose", "You didn't choose any facility");
  request.getRequestDispatcher("/Allfacilities.jsp").forward(request, response);
    }
   else
   {
  FacilityBean facilityBean = new FacilityBean();
//  HttpSession session = request.getSession();
//  String facilityId =(String)session.getAttribute("facilityid");
//  if(facilityId ==null)
//      facilityId="0";
  int facilityId1=facilityBean.facilityIdIWithName(facilityName);
       System.out.println("################################################# "+facilityId1);
 FacilityBean facilityWithName = facilityBean.retrieveFacilityWithName(facilityName,facilityId1);
  request.setAttribute("rset", facilityWithName);
  request.setAttribute("facilityN", facilityName);
  request.setAttribute("redirect", redirect);
if("Edit Facility".equals(redirect))
        {
    System.out.println("  redirect for edit jsp "+redirect);
    request.getRequestDispatcher("/EditFacility.jsp").forward(request, response);

        }
else 
        {
  request.getRequestDispatcher("EditFacilityServlet").forward(request, response);
        }
    }}
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
            Logger.getLogger(RedirectFacilityServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(RedirectFacilityServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RedirectFacilityServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RedirectFacilityServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RedirectFacilityServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(RedirectFacilityServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RedirectFacilityServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RedirectFacilityServlet.class.getName()).log(Level.SEVERE, null, ex);
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
