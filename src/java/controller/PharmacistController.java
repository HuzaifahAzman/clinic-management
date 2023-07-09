/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.BookingDAO;
import DAO.PrescriptionDAO;
import DAO.UserDAO;
import entity.Booking;
import entity.Prescription;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Adam
 */
@WebServlet(name = "PharmacistController", urlPatterns = {"/PharmacistController"})
public class PharmacistController extends HttpServlet {
    
    private User user = new User();
    private UserDAO userDAO = new UserDAO();
    private BookingDAO bookingDAO = new BookingDAO();
    private Booking booking = new Booking();
    private Prescription prescription = new Prescription();
    private PrescriptionDAO prescriptionDAO = new PrescriptionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try{
            String function = request.getParameter("function");
            
                if(function == null){
                    function = "homePage";
                }
                
                HttpSession session = request.getSession(true); 
                
                if(session == null || session.getAttribute("pharmacist") == null){
                    response.sendRedirect("index.jsp");
                }else{
                    
                    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                    response.setHeader("Pragma", "no-cache");
                    response.setDateHeader("Expires", 0);                    
                    
                    switch(function){
                        case "homePage":
                            viewPrescription(request, response);
                            break;
                            
                        case "logout":
                            logout(request, response);
                            break;
                            
                        case "viewDetailedPrescription":
                            viewDetailedPrescription(request, response);
                            break;
                            
                        case "completeTask":
                            completeTask(request, response);
                            break;
                            
                        case "completedPrescription":
                            completedPrescription(request, response);
                            break;
                        
                        case "displayUser":
                            displayUser(request, response);
                            break;
                        
                        case "updateUser":
                            updateUser(request, response);
                            break;
                            
                    }
                }
                
            }catch(Exception e){
                throw new ServletException(e);
            }
       
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       HttpSession session = request.getSession(true);

        //get session attribute from previous page
        session.getAttribute("pharmacist");
        
        try{
            String function = request.getParameter("function");
            
                if(function == null){
                    function = "homePage";
                }
                
                    switch(function){
                        case "homePage":
                            viewPrescription(request, response);
                            break;
                            
                        case "logout":
                            logout(request, response);
                            break;
                        
                        case "displayUser":
                            displayUser(request, response);
                            break;
                        
                        case "updateUser":
                            updateUser(request, response);
                            break;
                            
                        case "updateProfile":
                            updateProfile(request, response);
                            break;

                    }
                
            }catch(Exception e){
                throw new ServletException(e);
            }
    }
    
        private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        session.invalidate();
        
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }

    private void viewPrescription(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("pharmacist");
        
        user = userDAO.getUser(email);
        
        session.setAttribute("user", user);
 
        List<Booking> completedAppointment = bookingDAO.completedAppointment();
        
        request.setAttribute("completedAppointment", completedAppointment);
        
        RequestDispatcher rd = request.getRequestDispatcher("/pharmacist/pharmacistViewPrescription.jsp");
        rd.forward(request, response);
    }

    private void viewDetailedPrescription(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        
        int booking_id = Integer.parseInt(request.getParameter("bookingID"));
        
        List<Prescription> detailedPrescription = prescriptionDAO.detailPrescription(booking_id);
                      
        request.setAttribute("detailedPrescription", detailedPrescription);
        
        RequestDispatcher rd = request.getRequestDispatcher("/pharmacist/pharmacistViewPatientPrescription.jsp");
        rd.forward(request, response);        
    }

    private void completeTask(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
        int booking_id = Integer.parseInt(request.getParameter("bookingID"));
        
        prescriptionDAO.completeTask(booking_id);        
        
        request.setAttribute("success", "Task Completed");
        viewPrescription(request, response);
    }

    private void completedPrescription(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {        
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("pharmacist");
        
        List<Booking> completedPrescription = prescriptionDAO.completedPrescription();
        
        request.setAttribute("completedPrescription", completedPrescription);
        
        RequestDispatcher rd = request.getRequestDispatcher("/pharmacist/pharmacistViewCompletedPrescription.jsp");
        rd.forward(request, response);
        
    }
    
     private void displayUser (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
            
    HttpSession session = request.getSession(false);
    
    User currentUser = (User) session.getAttribute("user");
    
    user.setUserID(currentUser.getUserID());
    
    User name = userDAO.viewUser(user);
   
    request.setAttribute("pharmacistInfo", user);
    RequestDispatcher dispatcher;
    dispatcher = request.getRequestDispatcher("/pharmacist/pharmacistViewProfile.jsp");
    dispatcher.forward(request, response);
    }
    
    private void updateUser (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
    
    HttpSession session = request.getSession(false);
    
    User currentUser = (User) session.getAttribute("user");
    
    user.setUserID(currentUser.getUserID());
    
    User name = userDAO.viewUser(user);
   
    request.setAttribute("pharmacistInfo", user);
    RequestDispatcher dispatcher;
    dispatcher = request.getRequestDispatcher("/pharmacist/pharmacistUpdateProfile.jsp");
    dispatcher.forward(request, response);

}
    
    private void updateProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        
        HttpSession session = request.getSession(false);
        
        User currentUser = (User) session.getAttribute("user");
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String DOB = request.getParameter("DOB");
        String ic = request.getParameter("Ic");
        String password = request.getParameter("password");
        
        user.setUserID(currentUser.getUserID());
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setDOB(DOB);
        user.setIc(ic);
        user.setPassword(password);
        
        //inset data to database
        userDAO.updateUserProfile(user);
        displayUser(request, response);

    }
}


