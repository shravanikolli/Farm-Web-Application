/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.DBActions;
import model.Enterprise;
import model.Facility;

/**
 *
 * @author S519295
 */
@WebServlet(name = "AddEnterpriseServlet", urlPatterns = {"/AddEnterpriseServlet"})
@MultipartConfig(location = "C:/", fileSizeThreshold = 1024 * 1024 * 5 * 5 * 5 * 5,
        maxFileSize = 1024 * 1024 * 5 * 5, maxRequestSize = 1024 * 1024 * 5 * 5 * 5)
public class AddEnterpriseServlet extends HttpServlet {

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
        
        

    }

    private String extractFileName(Part part) {

        String contentDisp = part.getHeader("content-disposition");
        System.out.println("contentdisp----" + contentDisp);
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }

        return "";
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
          RequestDispatcher rd = request.getRequestDispatcher("EditFacility.jsp");
        rd.forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        
            HttpSession session = request.getSession();
            Facility facility = (Facility) session.getAttribute("selectedFacilityDetails");
            System.out.println("facility Id----" + facility.getFacilityID());
            //String saveOrCancel = request.getParameter("saveOrCancelEnterprise");
            System.out.println("fac name"+facility.getFacilityName());
            if (facility.getFacilityName() != null) {
                File fileSaveDir = new File("C:\\Users\\"+ DBActions.PATH +"\\Documents\\NetBeansProjects\\"+DBActions.NAME+"\\web" + File.separator + facility.getFacilityID());
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdir();
                    System.out.println("make dir");
                }

                String enterpriseName = request.getParameter("enterpriseName");
                String enterpriseDescription = request.getParameter("enterpriseDescription");
                Part part = request.getPart("enterpriseIcon");
                if (part != null) {
                    String fileName = extractFileName(part).replace(' ', 'x');
                    System.out.println("fileName----" + fileName);
                    part.write("Users\\"+ DBActions.PATH +"\\Documents\\NetBeansProjects\\"+DBActions.NAME+"\\web" + File.separator + facility.getFacilityID() + File.separator + fileName);
                    String filePath = facility.getFacilityID() + File.separator + fileName;
                    Enterprise newEnterprise = new Enterprise();
                    newEnterprise.setEnterpriseName(enterpriseName);
                    newEnterprise.setEnterpriseIcon(filePath);
                    newEnterprise.setFacilityID(facility.getFacilityID());
                    newEnterprise.setEnterpriseDescription(enterpriseDescription);

                    int z = 0;
                    for (int i = 0; i < facility.getEnterprisesList().size(); i++) {
                        if (facility.getEnterprisesList().get(i).getEnterpriseName().equals(enterpriseName)) {
                            z++;
                        }
                    }

                    DBActions dbActions = new DBActions();
                    if (z == 0) {
                        dbActions.addNewEnterprise(newEnterprise);

                        session.setAttribute("selectedFacilityDetails", dbActions.getFacilityDetails(facility.getFacilityID()));
                        session.setAttribute("enterpriseChecker", dbActions.getFacilityDetails(facility.getFacilityID()).getEnterprisesList().isEmpty() ? "disabled" : "");
                        response.sendRedirect("ExtraEnterpriseServlet?isEnterpriseDuplicated=no");
                    } else {
                        
                        System.out.println("already the facility contains this enterprise");
                        response.sendRedirect("ExtraEnterpriseServlet?isEnterpriseDuplicated=yes");
                    }
                    
                    
                    System.out.println("part is not null");

                }else{
                    System.out.println("part is null");
                }

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
