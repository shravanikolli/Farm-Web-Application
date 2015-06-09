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
@WebServlet(name = "AddNewFacilityServlet", urlPatterns = {"/AddNewFacilityServlet"})
public class AddNewFacilityServlet extends HttpServlet {

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
        String facilityName = request.getParameter("facilityName");
        System.out.println(" facility name is "+facilityName);
        String facilityDescription = request.getParameter("facilityDescription");
        double latitude = Double.parseDouble(request.getParameter("latitude"));
        double longitude = Double.parseDouble(request.getParameter("longitude"));
        String facilityDirections = request.getParameter("directions");
        Facility facility = new Facility();
        facility.setFacilityName(facilityName);
        facility.setFacilityDescription(facilityDescription);
        facility.setLatitude(latitude);
        facility.setLongitude(longitude);
        facility.setDirections(facilityDirections);
        DBActions dbActions = new DBActions();
        HttpSession session = request.getSession();
        //new code
        ArrayList<String> facilitiesListToCheck = (ArrayList<String>)session.getAttribute("facilitiesList");
        if(!facilitiesListToCheck.contains(facilityName)){
        
        dbActions.addNewFacility(facility);
        ArrayList<String> facilitiesList = dbActions.getFacilitiesList(); 
        session.setAttribute("facilitiesList", facilitiesList);
        RequestDispatcher rd = request.getRequestDispatcher("Facilities.jsp");
         rd.forward(request, response);
        }else{
            request.setAttribute("isFacilityDuplicated", "Already facility available with this name");
            System.out.println("we had that facility already....");
            RequestDispatcher rd = request.getRequestDispatcher("AddNewFacility.jsp");
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
