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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DBActions;
import model.Enterprise;
import model.Facility;

/**
 *
 * @author S519295
 */
@WebServlet(name = "AddOrEditOrDeleteEnterpriseServlet", urlPatterns = {"/AddOrEditOrDeleteEnterpriseServlet"})
@MultipartConfig(location = "C:/", fileSizeThreshold = 1024 * 1024 * 5 * 5 * 5 * 5,
        maxFileSize = 1024 * 1024 * 5 * 5, maxRequestSize = 1024 * 1024 * 5 * 5 * 5)
public class AddOrEditOrDeleteEnterpriseServlet extends HttpServlet {

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
       String facilityDiscription = request.getParameter("facilityDescription");
       double latitude = Double.parseDouble(request.getParameter("latitude"));
       double longitude = Double.parseDouble(request.getParameter("longitude"));
       String directions = request.getParameter("directions");
       String clickedOn = request.getParameter("addOrEditOrDeleteEnterprise");
       String selectedEnterprise = request.getParameter("Enterprises");
       Facility facility = new Facility();
       facility.setFacilityName(facilityName);
       facility.setFacilityDescription(facilityDiscription);
       facility.setLatitude(latitude);
       facility.setLongitude(longitude);
       facility.setDirections(directions);
       HttpSession session = request.getSession();
       session.setAttribute("selectedEnterprise", selectedEnterprise);
       Facility selectedFacility = ((Facility)session.getAttribute("selectedFacilityDetails"));
       
       if(selectedFacility!=null){
           int selectedFacilityId = selectedFacility.getFacilityID();
        System.out.println("selected facility id is ----" + selectedFacilityId);
       int facilityId = selectedFacility.getFacilityID();
       
       session.setAttribute("updatedFacilityData", facility);
       if(clickedOn.equals("Save")){
           // updating facility details
           DBActions dbActions = new DBActions();
           ArrayList<String> facilitiesList = dbActions.getFacilitiesList(); 
           facilitiesList.remove(selectedFacility.getFacilityName());
           if(!facilitiesList.contains(facility.getFacilityName())){
           dbActions.updateFacilityDetails(facilityId, facility);
           //new
           session.setAttribute("selectedFacilityDetails", dbActions.getFacilityDetails(facilityId));
           session.setAttribute("facilitiesList", dbActions.getFacilitiesList());
           RequestDispatcher rd = request.getRequestDispatcher("Facilities.jsp");
           rd.forward(request, response);
           }else{
               System.out.println("facility name duplicated.........");
               request.setAttribute("isFacilityDuplicated", "this facility is already available");
               RequestDispatcher rd = request.getRequestDispatcher("EditFacility.jsp");
               rd.forward(request, response);
           }
       }else if(clickedOn.equals("Cancel")){
           //no need to write any code.
              RequestDispatcher rd = request.getRequestDispatcher("Facilities.jsp");
           rd.forward(request, response);
       }else if(clickedOn.equals("Add Enterprise")){
           //need to know which facility the new enterprise belongs to.
           DBActions dbActions = new DBActions();
           Enterprise selectedEnterpriseObject = dbActions.getEnterpriseDetails(selectedFacilityId, selectedEnterprise);
           session.setAttribute("selectedEnterpriseDetails", selectedEnterpriseObject);
           RequestDispatcher rd = request.getRequestDispatcher("AddEnterprise.jsp");
           rd.forward(request, response);
       }else if(clickedOn.equals("Edit Enterprise")){
           DBActions dbActions = new DBActions();
           Enterprise selectedEnterpriseObject = dbActions.getEnterpriseDetails(selectedFacilityId, selectedEnterprise);
           session.setAttribute("selectedEnterpriseDetails", selectedEnterpriseObject);
           ArrayList<String> imagesList = dbActions.getImageNamesOfSelectedEnterprise(selectedEnterpriseObject.getEnterpriseID());
           session.setAttribute("imagesList", imagesList);
           ArrayList<String> videosList = dbActions.getVideoNamesOfSelectedEnterprise(selectedEnterpriseObject.getEnterpriseID());
           session.setAttribute("videosList", videosList);
           session.setAttribute("mediafileChecker", (imagesList.isEmpty() && videosList.isEmpty()) ? "disabled" : "");
           //need to know the facility and enterprise to which we do changes.
           
           RequestDispatcher rd = request.getRequestDispatcher("EditEnterprise.jsp");
           rd.forward(request, response);
       }else{
           //code to delete selected enterprise. So, we need to know the facility to which the selected enterprise belongs to.
           DBActions dbActions = new DBActions();
           dbActions.deleteEnterprise(facilityId, selectedEnterprise);
           session.setAttribute("selectedFacilityDetails", dbActions.getFacilityDetails(facilityId));
           session.setAttribute("enterpriseChecker", dbActions.getFacilityDetails(facilityId).getEnterprisesList().isEmpty()?"disabled":"");
           RequestDispatcher rd = request.getRequestDispatcher("EditFacility.jsp");
           rd.forward(request, response);
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
