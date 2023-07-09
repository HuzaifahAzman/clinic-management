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
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * @author user
 */
@WebServlet(name = "PatientController", urlPatterns = {"/PatientController"})
public class PatientController extends HttpServlet {
    
    private User user = new User();
    private UserDAO userDAO = new UserDAO();
    Booking booking = new Booking();
    BookingDAO bookingDAO = new BookingDAO();
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
                
                if(session == null || session.getAttribute("patient") == null){
                    response.sendRedirect("index.jsp");
                    
                } else{
                    
                    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                    response.setHeader("Pragma", "no-cache");
                    response.setDateHeader("Expires", 0);

                    switch(function){

                    //redirect user respective interface
                    case "homePage":
                        patientPage(request, response);
                        break;

                    case "viewBooking":
                        viewBooking(request, response);
                        break;

                    case "cancelAppointment":
                        cancelAppointment(request, response);
                        break;
                        
                    case "viewPrescription":
                        viewPrescription(request, response);
                        break;

                    case "logout":
                        logout(request, response);
                        break;
                    
                    case "viewPatientPrescription":
                        viewPatientPrescription(request, response);
                        break;
                        
                    case "doctorsRemarks":
                        doctorsRemarks(request, response);
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
            throws ServletException, IOException{
        
        HttpSession session = request.getSession(true);
        
        try{
            String function = request.getParameter("function");
                if(function == null){
                    function = "login";
                }
                switch(function){
                
                    //register patient and redirect to login page
                    case "registerPatient":
                        registerPatient(request, response);
                        break;
                        
                    case "bookAppointment":
                        bookAppointment(request, response);
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
    
    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    
    RequestDispatcher dispatcher;
    dispatcher = request.getRequestDispatcher("/login.jsp");
    dispatcher.forward(request, response);

}

    private void registerPatient(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {

        //get parameters
        String name = request.getParameter("name");
        String ic = request.getParameter("ic");
        String dob = request.getParameter("dob");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        //set parameter to class
        user.setName(name);
        user.setIc(ic);
        user.setDOB(dob);
        user.setPhone(phone);
        user.setGender(gender);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole("patient");
        
        //inset data to database
        userDAO.registerUser(user);
        
        loginUser(request, response);
        
    }

    private void patientPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        HttpSession session = request.getSession(false);
        String patient_email = (String)session.getAttribute("patient");
        
        user = userDAO.getUser(patient_email);
        
        session.setAttribute("user", user);
        
        RequestDispatcher rd = request.getRequestDispatcher("/patient/patientBooking.jsp");
        rd.forward(request, response);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        session.invalidate();
        response.sendRedirect("index.jsp");
    }

    private void bookAppointment(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ClassNotFoundException, SQLException, ParseException {
        HttpSession session = request.getSession(false);
        User currentUser = (User) session.getAttribute("user");
        
        //set paramaters
        String stringDate = request.getParameter("date");
        
        //parse string to date 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(stringDate);
        
        String time = request.getParameter("time");
        String symptom = request.getParameter("symptom");
        String doctor = request.getParameter("doctor");
        
        //get doctor id
        int doctorID = userDAO.getDoctorID(doctor);
        
        //set parameters to the class
        booking.setUser_id(currentUser.getUserID());
        booking.setDate(date);
        booking.setTime(time);
        booking.setSymptoms(symptom);
        booking.setDoctor(doctorID);
        booking.setStatus("Pending");
        
        //get the number of bookings on a specific date
        List<Integer> bookedDates = bookingDAO.getDate(booking);
        
        if(bookedDates.size() >= 3){
            request.setAttribute("fail", "Requested date is not available!");
            patientPage(request, response);
        }else{
            //pass values to BookingDAO
             bookingDAO.bookAppointment(booking);
             request.setAttribute("success", "Appointment booking successful");
            patientPage(request, response);
        }

    }

    private void viewBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        HttpSession session = request.getSession(false);
        User currentUser = (User) session.getAttribute("user");

        List<Booking> book = bookingDAO.viewBooking(currentUser);

        request.setAttribute("bookingList", book);
        RequestDispatcher rd = request.getRequestDispatcher("/patient/patientViewBooking.jsp");
        rd.forward(request, response);
    }

    private void cancelAppointment(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
        HttpSession session = request.getSession(false);
        User currentUser = (User) session.getAttribute("user");
        
        int booking_id = Integer.parseInt(request.getParameter("bookingID"));
        
        request.setAttribute("success", "Appoinment successfully cancelled!");
        bookingDAO.cancelAppointment(booking_id);
        viewBooking(request, response);
    }

    private void viewPrescription(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
        HttpSession session = request.getSession(false);
        User currentUser = (User) session.getAttribute("user");
        
        System.out.println(currentUser.getUserID());
        
        List<Booking> completedPatientPrescription = bookingDAO.completedPatientPrescription(currentUser);
        
        request.setAttribute("completedPatientPrescription", completedPatientPrescription);
        
        RequestDispatcher rd = request.getRequestDispatcher("/patient/patientPrescription.jsp");
        rd.forward(request, response);
    }

    //fix this
    private void viewPatientPrescription(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        
        int booking_id = Integer.parseInt(request.getParameter("bookingID"));
        
        List<Prescription> detailedPrescription = prescriptionDAO.detailPrescription(booking_id);
                      
        request.setAttribute("detailedPrescription", detailedPrescription);
        
        RequestDispatcher rd = request.getRequestDispatcher("/patient/patientViewPrescription.jsp");
        rd.forward(request, response);       
    }

    private void doctorsRemarks(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
        HttpSession session = request.getSession(false);
        User currentUser = (User) session.getAttribute("user");
        
        String remarks = bookingDAO.viewRemarks(currentUser);
        
        request.setAttribute("remarks", remarks);
        
        RequestDispatcher rd = request.getRequestDispatcher("/patient/patientViewRemarks.jsp");
        rd.forward(request, response);
       
    }
    
    private void displayUser (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
            
    HttpSession session = request.getSession(false);
    
    User currentUser = (User) session.getAttribute("user");
    
    user.setUserID(currentUser.getUserID());
    
    User name = userDAO.viewUser(user);
   
    request.setAttribute("patientInfo", user);
    RequestDispatcher dispatcher;
    dispatcher = request.getRequestDispatcher("/patient/patientViewProfile.jsp");
    dispatcher.forward(request, response);
    }
    
    private void updateUser (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
    
    HttpSession session = request.getSession(false);
    
    User currentUser = (User) session.getAttribute("user");
    
    user.setUserID(currentUser.getUserID());
    
    User name = userDAO.viewUser(user);
   
    request.setAttribute("patientInfo", user);
    RequestDispatcher dispatcher;
    dispatcher = request.getRequestDispatcher("/patient/patientUpdateProfile.jsp");
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
