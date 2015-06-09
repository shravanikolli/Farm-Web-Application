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
@WebServlet(name = "EditEnterpriseServlet", urlPatterns = {"/EditEnterpriseServlet"})
@MultipartConfig(location="C:/", fileSizeThreshold=1024*1024, 
    maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class EditEnterpriseServlet extends HttpServlet {

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
          System.out.println("contentdisp----"+contentDisp);
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
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
       Facility selectedFacility = (Facility)session.getAttribute("selectedFacilityDetails");
       Enterprise selectedEnterprise = (Enterprise)session.getAttribute("selectedEnterpriseDetails");
        String enterpriseName = request.getParameter("enterpriseName");
        String enterpriseDescription = request.getParameter("enterpriseDescription");
        Enterprise enterprise = new Enterprise();
        enterprise.setEnterpriseName(enterpriseName);
        enterprise.setEnterpriseDescription(enterpriseDescription);
        enterprise.setEnterpriseIcon(selectedEnterprise.getEnterpriseIcon());
        
        //edited image code here.
         Part part=request.getPart("enterpriseIcon");
                              String mediaFileName=extractFileName(part).replace(' ', 'x');
                System.out.println("fileName----"+ mediaFileName);
                //checking whether the user needs to modify or not.
                if(!mediaFileName.equals("")){
                     System.out.println("entered against");
                     File fileSaveDir=new File("C:\\Users\\"+ DBActions.PATH +"\\Documents\\NetBeansProjects\\"+DBActions.NAME+"\\web" + File.separator + selectedFacility.getFacilityID());
                if(!fileSaveDir.exists()){
                    fileSaveDir.mkdir();
                }
                     
                part.write("Users\\"+ DBActions.PATH +"\\Documents\\NetBeansProjects\\"+DBActions.NAME+"\\web"+ File.separator + selectedFacility.getFacilityID() + File.separator + mediaFileName);
            String filePath =  selectedFacility.getFacilityID() + File.separator + mediaFileName;
            enterprise.setEnterpriseIcon(filePath);
                 }
                
               DBActions dbActions = new DBActions();
               //validation code
               selectedFacility.getEnterprisesList().remove(selectedEnterprise);
               if(!selectedFacility.getEnterprisesList().contains(enterprise)){               
               //updating enterprise details to the database.
               dbActions.updateEnterpriseDetails(selectedEnterprise.getEnterpriseID(), enterprise);
               session.setAttribute("selectedFacilityDetails", dbActions.getFacilityDetails(selectedFacility.getFacilityID()));
       session.setAttribute("selectedEnterpriseDetails", dbActions.getEnterpriseDetails(selectedFacility.getFacilityID(), enterpriseName));
       
       //redirecting to another web page.
      response.sendRedirect("ExtraEditEnterpriseServlet?isEnterpriseDuplicated=no");
               }else{
                    System.out.println("already the facility contains this enterprise");
                        response.sendRedirect("ExtraEditEnterpriseServlet?isEnterpriseDuplicated=yes");
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
