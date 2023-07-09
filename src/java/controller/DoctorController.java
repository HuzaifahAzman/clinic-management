/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.BookingDAO;
import DAO.InventoryDAO;
import DAO.PrescriptionDAO;
import DAO.UserDAO;
import entity.Booking;
import entity.Inventory;
import entity.Prescription;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.lang.Double;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author user
 */
@WebServlet(name = "DoctorController", urlPatterns = {"/DoctorController"})
public class DoctorController extends HttpServlet {

     private User user = new User();
     private UserDAO userDAO = new UserDAO();
     private BookingDAO bookingDAO = new BookingDAO();
     private Booking booking = new Booking();
     private Prescription prescription = new Prescription();
     private PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
     private Inventory inventory = new Inventory();
     private InventoryDAO inventoryDAO = new InventoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try{
            String function = request.getParameter("function");
                if(function == null){
                    function = "homePage";
                }
                
                 HttpSession session = request.getSession(true);
                
                if(session == null || session.getAttribute("doctor") == null){
                    response.sendRedirect("index.jsp");
                    
                } else{
                    
                    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                    response.setHeader("Pragma", "no-cache");
                    response.setDateHeader("Expires", 0);
                    
                    switch(function){
                        case "homePage":
                            validateAppointment(request, response);
                            break;

                        case "viewUpcomingAppointment":
                            viewUpcomingAppointment(request, response);
                            break;

                        case "logout":
                            logout(request, response);
                            break;

                        case "approveAppointment":
                            approveAppointment(request, response);
                            break;

                        case "rejectAppointment":
                            rejectAppointment(request, response);
                            break;
                            
                        case "todaysAppointment":
                            todaysAppointment(request, response);
                            break;
                            
                        case "addPrescriptionPage":
                            addPrescriptionPage(request, response);
                            break; 
                            
                        case "appointmentHistory":
                            appointmentHistory(request, response);
                            break;
                            
                        case "viewPatientPrescriptionPage":
                            viewPatientPrescriptionPage(request, response);
                            break;
                        
                        case "updateProfile":
                            updateProfile(request, response);
                            break;
                        
                        case "updateUser":
                            updateUser(request, response);
                            break;
                        
                        case "displayUser":
                            displayUser(request, response);
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
        session.getAttribute("doctor");
        
        try{
            String function = request.getParameter("function");
            
                if(function == null){
                    function = "homePage";
                }
                switch(function){
                    
                    case "homePage":
                        validateAppointment(request, response);
                        break; 
                        
                    case "filterAppointment":
                        filterAppointment(request, response);
                        break;
                        
                    case "addPrescription":
                        addPrescription(request, response);
                        break;
                        
                    case "rejectingRemarks":
                        rejectingRemarks(request, response);
                        break;
                        
                    case "updateProfile":
                        updateProfile(request, response);
                        break;
                        
                    case "updateUser":
                        updateUser(request, response);
                        break;
                        
                    case "displayUser":
                        displayUser(request, response);
                        break;
                        
                }
                
            }catch(Exception e){
                throw new ServletException(e);
            }
    }
    
    private void viewUpcomingAppointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException{
        HttpSession session = request.getSession(false);
        User currentUser = (User) session.getAttribute("user");

        List<Booking> getAppointments = bookingDAO.appointment(currentUser);
        
        request.setAttribute("appointmentList", getAppointments);
        
        RequestDispatcher dispatcher;
        dispatcher = request.getRequestDispatcher("/doctor/doctorViewUpcomingAppointment.jsp");
        dispatcher.forward(request, response);
    }

    private void validateAppointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        HttpSession session = request.getSession(false);
        String doctor_email = (String) session.getAttribute("doctor");
        
        user = userDAO.getUser(doctor_email);
        
        session.setAttribute("user", user);

        List<Booking> getAppointments = bookingDAO.validateAppointment(user);
        
        request.setAttribute("appointmentList", getAppointments);
        
        RequestDispatcher dispatcher;
        dispatcher = request.getRequestDispatcher("/doctor/doctorValidateAppointment.jsp");
        dispatcher.forward(request, response);
    }

    private void todaysAppointment(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
        HttpSession session = request.getSession(false);
        User currentUser = (User) session.getAttribute("user");
        
        List<Booking> getTodaysAppointment = bookingDAO.todaysAppointment(currentUser);
        
        request.setAttribute("todaysAppointment", getTodaysAppointment);
        
        RequestDispatcher dispatcher;
        dispatcher = request.getRequestDispatcher("/doctor/doctorTodaysAppointment.jsp");
        dispatcher.forward(request, response);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        session.removeAttribute("doctor");
        session.invalidate();
        
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }

    private void approveAppointment(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
        int booking_id  = Integer.parseInt(request.getParameter("bookingID"));
        
        bookingDAO.approveAppointment(booking_id);
        
        request.setAttribute("success", "Appoinment approved!" );
        validateAppointment(request, response);
    }

    private void rejectAppointment(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
        int booking_id  = Integer.parseInt(request.getParameter("bookingID"));
        
        request.setAttribute("bookingID", booking_id);
        
        RequestDispatcher rd = request.getRequestDispatcher("/doctor/doctorRejectingRemarks.jsp");
        rd.forward(request, response);
    }
    
    private void rejectingRemarks(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
        int booking_id  = Integer.parseInt(request.getParameter("booking_id")); 
        String rejectionComments = request.getParameter("rejectionComments");
        
        bookingDAO.rejectAppointment(booking_id, rejectionComments);
        
        request.setAttribute("fail", "Appoinment rejected!" );
        validateAppointment(request, response);
    }

    private void filterAppointment(HttpServletRequest request, HttpServletResponse response) throws ParseException, ClassNotFoundException, SQLException, ServletException, IOException {
        //return session
        HttpSession session = request.getSession(false);
        
        //set paramaters
        String stringDate = request.getParameter("date");
        
        //parse string to date 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(stringDate); 
        
        //check date submitted 
        List<Booking> appointmentHistory = bookingDAO.filterAppointment(user, date);
        
        request.setAttribute("appointmentHistory",appointmentHistory);
        
        RequestDispatcher dispatcher;
        dispatcher = request.getRequestDispatcher("/doctor/doctorFilterAppointment.jsp");
        dispatcher.forward(request, response);
  
    }  

    private void addPrescriptionPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {      
        //get session
        HttpSession session = request.getSession(false);

        //get parameter from jsp
        int bookingID = Integer.parseInt(request.getParameter("bookingID"));
        
        User patientInfo = userDAO.patientInfo(bookingID);
        List<Inventory> inventory = inventoryDAO.getInventory();

        session.setAttribute("patientInfo", patientInfo);
        request.setAttribute("bookingID", bookingID);
        request.setAttribute("inventory", inventory);
        RequestDispatcher rd = request.getRequestDispatcher("/doctor/doctorPrescriptionPage.jsp");
        rd.forward(request, response);   
    }

    private void addPrescription(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        //create session
        HttpSession session = request.getSession(false);        
        User currentUser = (User) session.getAttribute("user");
        
        //get User ID and bookingID
        int booking_id = Integer.parseInt(request.getParameter("bookingID"));
        int patient_id = Integer.parseInt(request.getParameter("patientID"));  
        
        String[] medicine_name = request.getParameterValues("medicine[]");
        String[] medicine_intake = request.getParameterValues("intake[]"); 
        String[] medicine_dosage_type = request.getParameterValues("dosage_type[]");
        String[] medicine_dosage = request.getParameterValues("dosage[]");
        String[] medicine_frequency= request.getParameterValues("frequency[]");    
        String[] medicine_stock= request.getParameterValues("stock[]");    
      

        //define variables needed to be parsed
        int[] dosage = new int [medicine_name.length];
        int[] req_stock = new int [medicine_name.length];

        String wrong = "";
        String failedMessage = "Insufficient amount of Stock!";
        
        //store parameters in array
        for(int i = 0; i < medicine_name.length; i++){
            dosage[i] = Integer.parseInt(medicine_dosage[i]);
            req_stock[i] = Integer.parseInt(medicine_stock[i]);
            
            // if current stock less than total requested stock
            if (inventoryDAO.getStock(medicine_name[i])<req_stock[i])
            {
                // fail
                wrong = "fail";
                failedMessage += "<br>" + medicine_name[i] +" is " + inventoryDAO.getStock(medicine_name[i]);
                request.setAttribute("bookingID", request.getParameter("bookingID"));
            }
            System.out.print(dosage[i]);
            System.out.print(req_stock[i]);
        }
        
        // if condition failed
        if (wrong.equals("fail")){
            request.setAttribute("failed", failedMessage);
            addPrescriptionPage(request, response);
        }
        
        //store values in class
        if (wrong.equals(""))
        {
            for(int i = 0; i < medicine_name.length; i++){
                prescription.setBooking_id(booking_id);
                prescription.setUser_id(patient_id);
                prescription.setMedicine(medicine_name[i]);
                prescription.setIntake(medicine_intake[i]);                        
                prescription.setDosage(dosage[i]);
                prescription.setStock(req_stock[i]);
                prescription.setFrequency(medicine_frequency[i]);
                prescription.setDosage_type(medicine_dosage_type[i]);
                
                prescriptionDAO.addPrescription(currentUser.getUserID(), booking_id, patient_id, prescription);
                inventoryDAO.decreaseStock(medicine_name[i], (inventoryDAO.getStock(medicine_name[i]) - req_stock[i]));
            }

            bookingDAO.doneAppointment(booking_id);

            //decrease stock

            request.setAttribute("success", "Prescription success!");
            todaysAppointment(request, response);
        }
        
    }

    private void appointmentHistory(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
        HttpSession session = request.getSession(false);
        User currentUser = (User) session.getAttribute("user");
        
        List<Booking> appointmentHistory = bookingDAO.appointmentHistory(currentUser);
        
        request.setAttribute("appointmentHistory", appointmentHistory);
        
        RequestDispatcher dispatcher;
        dispatcher = request.getRequestDispatcher("/doctor/doctorAppointmentHistory.jsp");
        dispatcher.forward(request, response);
        
    }

    private void viewPatientPrescriptionPage(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
        int booking_id = Integer.parseInt(request.getParameter("bookingID"));
        
        List<Prescription> detailedPrescription = prescriptionDAO.detailPrescription(booking_id);
                      
        request.setAttribute("detailedPrescription", detailedPrescription);
        
        RequestDispatcher rd = request.getRequestDispatcher("/doctor/doctorViewPatientPrescription.jsp");
        rd.forward(request, response);       
    }
    
    private void displayUser (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
            
    HttpSession session = request.getSession(false);
    
    User currentUser = (User) session.getAttribute("user");
    
    user.setUserID(currentUser.getUserID());
    
    User name = userDAO.viewUser(user);
   
    request.setAttribute("doctorInfo", user);
    RequestDispatcher dispatcher;
    dispatcher = request.getRequestDispatcher("/doctor/doctorViewProfile.jsp");
    dispatcher.forward(request, response);
    }
    
    private void updateUser (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
    
    HttpSession session = request.getSession(false);
    
    User currentUser = (User) session.getAttribute("user");
    
    user.setUserID(currentUser.getUserID());
    
    User name = userDAO.viewUser(user);
   
    request.setAttribute("doctorInfo", user);
    
    RequestDispatcher dispatcher;
    dispatcher = request.getRequestDispatcher("doctor/doctorUpdateProfile.jsp");
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
        userDAO.updateUser(user);
        displayUser(request, response);

    }



}
