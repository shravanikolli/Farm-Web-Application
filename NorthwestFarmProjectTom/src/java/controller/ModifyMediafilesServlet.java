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
import model.Image;
import model.Video;

/**
 *
 * @author S519295
 */
@WebServlet(name = "ModifyMediafilesServlet", urlPatterns = {"/ModifyMediafilesServlet"})
@MultipartConfig(location="C:/", fileSizeThreshold=26246533, 
    maxFileSize=26246533, maxRequestSize=26246533)
public class ModifyMediafilesServlet extends HttpServlet {

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
        String fileName = request.getParameter("mediaFiles");
        System.out.println("selected file name is...." + fileName);
        String action = request.getParameter("modifyMediafiles");
        HttpSession session = request.getSession();
       Facility selectedFacility = (Facility)session.getAttribute("selectedFacilityDetails");
       Enterprise selectedEnterpriseObject = (Enterprise)session.getAttribute("selectedEnterpriseDetails");
        DBActions dbAction = new DBActions();
       if(selectedFacility != null && selectedEnterpriseObject != null && action != null){
           if(action.equals("Edit MediaFile")){
               //edit selected mediafile by dispatching request to ModifyMediaFile.jsp 
               if(fileName.endsWith("(image)")){
                   System.out.println("entered into select image area");
               Image selectedImage = dbAction.getImageDetails(selectedEnterpriseObject.getEnterpriseID(), fileName.substring(0, fileName.length()-7));
               session.setAttribute("selectedImage", selectedImage);
               System.out.println("selected image path...." + selectedImage.getImagePath());
               RequestDispatcher rd = request.getRequestDispatcher("EditImage.jsp");
               rd.forward(request, response);
               }else{
                   System.out.println("entered into select video area");
                   Video selectedVideo = dbAction.getVideoDetails(selectedEnterpriseObject.getEnterpriseID(), fileName.substring(0, fileName.length()-7));
                   session.setAttribute("selectedVideo", selectedVideo);
                   RequestDispatcher rd = request.getRequestDispatcher("EditVideo.jsp");
                   rd.forward(request, response);
               }
           }
           else if(action.equals("Delete MediaFile")){
           //delete selected mediafile
               ArrayList<String> imagesList = new ArrayList();
               ArrayList<String> videosList = new ArrayList();
               if(fileName.endsWith("(image)")){
               dbAction.deleteImage(selectedEnterpriseObject.getEnterpriseID(), fileName.substring(0, fileName.length()-7));
               imagesList = dbAction.getImageNamesOfSelectedEnterprise(selectedEnterpriseObject.getEnterpriseID());
           session.setAttribute("imagesList", imagesList);
               }else{
                   //for videos
                   dbAction.deleteVideo(selectedEnterpriseObject.getEnterpriseID(), fileName.substring(0, fileName.length()-7));
           videosList = dbAction.getVideoNamesOfSelectedEnterprise(selectedEnterpriseObject.getEnterpriseID());
           session.setAttribute("videosList", videosList);
               }
           session.setAttribute("mediafileChecker", (imagesList.isEmpty() && videosList.isEmpty()) ? "disabled" : "");
           RequestDispatcher rd = request.getRequestDispatcher("EditEnterprise.jsp");
           rd.forward(request, response);
           
       }else{
           //Add new mediafile
          RequestDispatcher rd = request.getRequestDispatcher("AddMediaFile.jsp");
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
