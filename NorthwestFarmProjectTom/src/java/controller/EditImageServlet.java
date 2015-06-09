/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
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
import javax.servlet.http.Part;
import model.DBActions;
import model.Enterprise;
import model.Facility;
import model.Image;

/**
 *
 * @author S519295
 */
@WebServlet(name = "EditImageServlet", urlPatterns = {"/EditImageServlet"})
@MultipartConfig(location="C:/", fileSizeThreshold=1024*1024, 
    maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class EditImageServlet extends HttpServlet {

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
        RequestDispatcher rd = request.getRequestDispatcher("EditEnterprise.jsp");
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
        Image selectedImage = (Image)session.getAttribute("selectedImage");
        DBActions dbActions = new DBActions();
       String imageName = request.getParameter("imageName");
       String imageDescription = request.getParameter("imageDescription");
       Image image = new Image(); 
       image.setEnterpriseID(selectedEnterprise.getEnterpriseID());
       image.setImageName(imageName);
       image.setImageDesc(imageDescription);
       image.setImagePath(selectedImage.getImagePath());
       
       //code for image part
         
                              Part part=request.getPart("fileAddress");
                              String mediaFileName=extractFileName(part).replace(' ', 'x');
                System.out.println("fileName----"+ mediaFileName);
                 if(!mediaFileName.equals("")){
                     System.out.println("entered against");
                     File fileSaveDir=new File("C:\\Users\\"+ DBActions.PATH +"\\Documents\\NetBeansProjects\\"+DBActions.NAME+"\\web" + File.separator + selectedFacility.getFacilityID() + File.separator + selectedEnterprise.getEnterpriseID());
                if(!fileSaveDir.exists()){
                    fileSaveDir.mkdir();
                }
                     
                part.write("Users\\"+ DBActions.PATH +"\\Documents\\NetBeansProjects\\"+DBActions.NAME+"\\web"+ File.separator + selectedFacility.getFacilityID() + File.separator + selectedEnterprise.getEnterpriseID() + File.separator + mediaFileName);
            String filePath =  selectedFacility.getFacilityID() + File.separator + selectedEnterprise.getEnterpriseID() + File.separator + mediaFileName;
            image.setImagePath(filePath);
                 }
                 //validation code
                 ArrayList<String> imagesListToCheck = dbActions.getImageNamesOfSelectedEnterprise(selectedEnterprise.getEnterpriseID());
                 imagesListToCheck.remove(selectedImage.getImageName());
                if (!imagesListToCheck.contains(image.getImageName())) {
                
                 dbActions.updateImageDetails(selectedImage.getImageID(), selectedImage.getEnterpriseID(), image);
        //this code should be at last after all alters we made.
          ArrayList<String> imagesList = dbActions.getImageNamesOfSelectedEnterprise(selectedEnterprise.getEnterpriseID());
           session.setAttribute("imagesList", imagesList);
           //code to redirect the page to another.
           response.sendRedirect("ExtraEditMediaFileServlet?isMediafileDuplicated=no");
                }else{
                    System.out.println("enterprise contains this image already");
                    response.sendRedirect("ExtraEditMediaFileServlet?isMediafileDuplicated=yes");
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
