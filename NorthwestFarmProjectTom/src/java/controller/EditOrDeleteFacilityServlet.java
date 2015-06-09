/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DBActions;
import model.Facility;

/**
 *
 * @author S519295
 */
@WebServlet(name = "EditOrDeleteFacilityServlet", urlPatterns = {"/EditOrDeleteFacilityServlet"})
public class EditOrDeleteFacilityServlet extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //getting the data from request parameters.
       String selectedFacility = request.getParameter("Facilities");
       String editOrDelete = request.getParameter("editOrDeleteFacility");
       //getting session object.
       HttpSession session = request.getSession();
       //add selected facility to the session object.
       session.setAttribute("selectedFacility", selectedFacility);
       if(editOrDelete!=null){
       RequestDispatcher rd;
       DBActions dbActions = new DBActions();
       if(editOrDelete.equals("Edit Facility")){
           //need to get facility data like name, description, latitude, longitude, directions and enterprises.
           
          Facility facility = dbActions.getFacilityDetails(dbActions.getSelectedFacilityId(selectedFacility));
          if(facility != null){
          session.setAttribute("selectedFacilityDetails", facility);
          session.setAttribute("enterpriseChecker", facility.getEnterprisesList().isEmpty()? "disabled" : "");
          }
           System.out.println("clicked on edit facility " + selectedFacility);
           rd = request.getRequestDispatcher("EditFacility.jsp");
       }else{
           System.out.println("clicked on delete facility " + selectedFacility);
           dbActions.deleteFacility(selectedFacility);
            ArrayList<String> facilitiesList = dbActions.getFacilitiesList(); 
        session.setAttribute("facilitiesList", facilitiesList);
        session.setAttribute("facilityChecker", facilitiesList.isEmpty()?"disabled":"");
           rd = request.getRequestDispatcher("Facilities.jsp");
       }
       rd.forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
