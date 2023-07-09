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
import entity.AppointmentHistory;
import entity.Booking;
import entity.Inventory;
import entity.Prescription;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
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
 * @author User
 */
@WebServlet(name = "AdminController", urlPatterns = {"/AdminController"})
public class AdminController extends HttpServlet {

    private User user = new User();
    private UserDAO userDAO = new UserDAO();
    private Booking booking = new Booking();                               
    private BookingDAO bookingDAO = new BookingDAO();
    private Inventory inventory = new Inventory();                               
    private InventoryDAO inventoryDAO = new InventoryDAO();
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
                
            if(session == null || session.getAttribute("admin") == null){
                response.sendRedirect("index.jsp");
            }else{

                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                response.setHeader("Pragma", "no-cache");
                response.setDateHeader("Expires", 0);
                    
                switch(function){
                    case "homePage":
                        adminPage(request, response);
                        break;
                    case "inventoryPage":
                        inventoryPage(request, response);
                        break;
                    case "appointmentPage":
                        appointmentPage(request, response);
                        break;
                    case "manageStaffPage":
                        manageStaffPage(request, response);
                        break;
                    case "logout":
                        logout(request, response);
                        break;
                    case "displayAdmin":
                        displayAdmin(request, response);
                        break;
                    case "updateAdmin":
                        updateAdmin(request, response);
                        break;
                }
            }
        }catch(Exception e){
            
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //create session for admin
        HttpSession session = request.getSession(true);
        
        //get session attribute from previous page
        session.getAttribute("admin");
        
        try{
            String function = request.getParameter("function");
            if(function == null){
                function = "homePage";
            }
            switch(function){
                case "patientHistory":
                    patientHistory(request, response);
                    break;
                case "addInventory":
                    addInventory(request, response);
                    break;
                case "deleteInventory":
                    deleteInventory(request, response);
                    break;
                case "activationInventory":
                    activationInventory(request, response);
                    break;
                case "deactivationInventory":
                    deactivationInventory(request, response);
                    break;
                case "updateFormInventory":
                    updateFormInventory(request, response);
                    break;
                case "updateInventory":
                    updateInventory(request, response);
                    break;
                case "appointmentHistory":
                    appointmentHistory(request, response);
                    break;
                case "appointmentReport":
                    appointmentReport(request, response);
                    break;
                case "addStaff":
                    addStaff(request, response);
                    break;
                case "updateStaff":
                    updateStaff(request, response);
                    break;
                case "updateFormStaff":
                    updateFormStaff(request, response);
                    break;
                case "deleteStaff":
                    deleteStaff(request, response);
                    break;
                case "logout":
                    logout(request, response);
                    break;
                case "displayAdmin":
                    displayAdmin(request, response);
                    break;
                case "updateProfileAdmin":
                    updateProfileAdmin(request, response);
                    break;
                case "updateAdmin":
                    updateAdmin(request, response);
                    break;
                case "deleteAppointment":
                    deleteAppointment(request, response);
                    break;
            }
        }catch(Exception e){
            
        }
    }

    private void adminPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        HttpSession session = request.getSession(false);
        
        String admin_email = (String) session.getAttribute("admin");
        
        user = userDAO.getUser(admin_email);
        
        session.setAttribute("user", user);
        
        List<User> getPatientHistory = userDAO.getPatientHistory();
        
        session.setAttribute("patientHistory", getPatientHistory);
        session.setAttribute("displayPatientHistory", "");
        
        RequestDispatcher r = request.getRequestDispatcher("/admin/adminPatientHistory.jsp");
        r.forward(request, response);
    }
    
    private void patientHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        HttpSession session = request.getSession(false);
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        List<Booking> getPatientHistory = bookingDAO.getAPatientHistory(id);
        
        session.setAttribute("aPatientHistory", getPatientHistory);
        
        session.setAttribute("displayPatientHistory", "yes");
        
        RequestDispatcher r = request.getRequestDispatcher("/admin/adminPatientHistory.jsp");
        r.forward(request, response);
    }
    
    private void deleteAppointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        HttpSession session = request.getSession(false);
        int booking_id = Integer.parseInt(request.getParameter("booking_id"));
        
        prescriptionDAO.deletePrescriptionBooking(booking_id);
        bookingDAO.cancelAppointment(booking_id);
        
        List<User> getPatientHistory = userDAO.getPatientHistory();
        
        session.setAttribute("patientHistory", getPatientHistory);
        session.setAttribute("displayPatientHistory", "");
        
        RequestDispatcher r = request.getRequestDispatcher("/admin/adminPatientHistory.jsp");
        r.forward(request, response);
    }

    private void inventoryPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        HttpSession session = request.getSession(false);
        
        List<Inventory> getInventory = inventoryDAO.viewInventory();
        
        session.setAttribute("inventory", getInventory);
        
        RequestDispatcher r = request.getRequestDispatcher("/admin/adminInventory.jsp");
        r.forward(request, response);

    }
    
    private void addInventory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {

        String product_name = request.getParameter("product_name");
        String product_id = request.getParameter("product_id");
        String supplier_name = request.getParameter("supplier_name");
        int stock = Integer.parseInt(request.getParameter("stock"));                                            
        int price = Integer.parseInt(request.getParameter("price"));
        String status = request.getParameter("status");
       
        inventory.setProduct_name(product_name);
        inventory.setProduct_id(product_id);
        inventory.setSupplier_name(supplier_name);
        inventory.setStock(stock);
        inventory.setPrice(price);
        inventory.setStatus(status);

        inventoryDAO.addInventory(inventory);
        
        inventoryPage(request, response);
    }
    
    private void deleteInventory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        String product_id = request.getParameter("product_id");

        inventoryDAO.deleteInventory(product_id);
        
        inventoryPage(request, response);
    }
    
    private void deactivationInventory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        String product_id = request.getParameter("product_id");

        inventoryDAO.deactivationInventory(product_id);
        
        inventoryPage(request, response);
    }
    
    private void activationInventory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        String product_id = request.getParameter("product_id");

        inventoryDAO.activationInventory(product_id);
        
        inventoryPage(request, response);
    }
    
    private void updateFormInventory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        HttpSession session = request.getSession(false);

        String product_id = request.getParameter("product_id");
        
        session.setAttribute("updateID", product_id);
        
        inventoryPage(request, response);
    }
    
    private void updateInventory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        HttpSession session = request.getSession(false);
        
        String product_name = request.getParameter("product_name");
        String product_id = request.getParameter("product_id");
        String supplier_name = request.getParameter("supplier_name");
        int stock = Integer.parseInt(request.getParameter("stock"));                                            
        int price = Integer.parseInt(request.getParameter("price"));
        String status = request.getParameter("status");
        
        inventory.setProduct_name(product_name);
        inventory.setProduct_id(product_id);
        inventory.setSupplier_name(supplier_name);
        inventory.setStock(stock);
        inventory.setPrice(price);
        inventory.setStatus(status);
        
        inventoryDAO.updateInventory(inventory);
        
        session.setAttribute("updateID", "");
        
        inventoryPage(request, response);
    }
    
    private void appointmentPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ClassNotFoundException, SQLException {
        HttpSession session = request.getSession(false);
        
        List<AppointmentHistory> getMonthlyAptHistory = bookingDAO.allMonthlyAppointment ();
        
        session.setAttribute("monthlyAptHistory", getMonthlyAptHistory);
        
        session.setAttribute("displayAppointmentHistory", "");
        session.setAttribute("displayAppointmentReport", "");
        
        RequestDispatcher r = request.getRequestDispatcher("/admin/adminAppointment.jsp");
        r.forward(request, response);
    }
    
    private void appointmentHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ClassNotFoundException, SQLException {
        HttpSession session = request.getSession(false);

       //set paramaters
        int year = Integer.parseInt(request.getParameter("year"));
        int month = Integer.parseInt(request.getParameter("month"));
        
        List<Booking> getMonthlyApt = bookingDAO.monthlyAppointment(year, month);
        
        session.setAttribute("monthlyHistory", getMonthlyApt);
        
        session.setAttribute("displayAppointmentHistory", "yes");
        session.setAttribute("displayAppointmentReport", "");
        
        RequestDispatcher r = request.getRequestDispatcher("/admin/adminAppointment.jsp");
        r.forward(request, response);
    }
    
    private void appointmentReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ClassNotFoundException, SQLException {
        HttpSession session = request.getSession(false);
       
        //set paramaters
        int year = Integer.parseInt(request.getParameter("year"));
        int month = Integer.parseInt(request.getParameter("month"));

        // number list of appoint for specific month
        List<AppointmentHistory> getMonthlyAptReport = bookingDAO.allMonthlyAppointmentReport(year, month);
          
        session.setAttribute("monthlyHistoryReport", getMonthlyAptReport);
        
        session.setAttribute("displayAppointmentReport", "yes");
        session.setAttribute("displayAppointmentHistory", "");
        
        RequestDispatcher r = request.getRequestDispatcher("/admin/adminAppointment.jsp");
        r.forward(request, response);
    }
    
    private void manageStaffPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, ClassNotFoundException, SQLException {
        HttpSession session = request.getSession(false);
        
        List<User> getStaff = userDAO.getStaff();
        
        session.setAttribute("allStaff", getStaff);
        
        RequestDispatcher r = request.getRequestDispatcher("/admin/adminManageStaff.jsp");
        r.forward(request, response);
    }
    
    private void addStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException, ParseException {

        String staff_name = request.getParameter("staff_name");
        String staff_email = request.getParameter("staff_email");
        String staff_password = request.getParameter("staff_password");
        String staff_phone = request.getParameter("staff_phone");
        String staff_DOB = request.getParameter("staff_DOB");
        String staff_IC = request.getParameter("staff_IC");
        String staff_gender = request.getParameter("staff_gender");
        String staff_role = request.getParameter("staff_role");
       
        user.setName(staff_name);
        user.setEmail(staff_email);
        user.setPassword(staff_password);
        user.setPhone(staff_phone);
        user.setDOB(staff_DOB);
        user.setIc(staff_IC);
        user.setGender(staff_gender);
        user.setRole(staff_role);

        userDAO.registerUser(user);
        
        manageStaffPage(request, response);
    }
    
    private void updateFormStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException, ParseException {
        HttpSession session = request.getSession(false);

        String staff_id = request.getParameter("staff_id");
        
        session.setAttribute("updateStaffID", staff_id);
        
        manageStaffPage(request, response);
    }
    
    private void updateStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException, ParseException {
        HttpSession session = request.getSession(false);
        
        int staff_id = Integer.parseInt(request.getParameter("staff_id"));
        String staff_name = request.getParameter("staff_name");
        String staff_email = request.getParameter("staff_email");
        String staff_password = request.getParameter("staff_password");
        String staff_phone = request.getParameter("staff_phone");
        String staff_DOB = request.getParameter("staff_DOB");
        String staff_IC = request.getParameter("staff_IC");
        String staff_gender = request.getParameter("staff_gender");
        String staff_role = request.getParameter("staff_role");
        
        user.setUserID(staff_id);
        user.setName(staff_name);
        user.setEmail(staff_email);
        user.setPassword(staff_password);
        user.setPhone(staff_phone);
        user.setDOB(staff_DOB);
        user.setIc(staff_IC);
        user.setGender(staff_gender);
        user.setRole(staff_role);
        
        userDAO.updateUser(user);
        
        session.setAttribute("updateStaffID", "");
        
        manageStaffPage(request, response);
    }
    
    private void deleteStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException, ParseException {
        int staff_id = Integer.parseInt(request.getParameter("staff_id"));

        userDAO.deleteUser(staff_id);
        
        manageStaffPage(request, response);
    }
    
    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.invalidate();
        RequestDispatcher r = request.getRequestDispatcher("/index.jsp");
        r.forward(request, response);
    }
    
    private void displayAdmin (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        HttpSession session = request.getSession(false);

        User currentUser = (User) session.getAttribute("user");

        user.setUserID(currentUser.getUserID());

        userDAO.viewUser(user);

        User name = userDAO.viewUser(user);

        request.setAttribute("adminInfo", user);

        RequestDispatcher dispatcher;
        dispatcher = request.getRequestDispatcher("/admin/adminViewProfile.jsp");
        dispatcher.forward(request, response);
    }
    
    private void updateProfileAdmin (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        HttpSession session = request.getSession(false);

        User currentUser = (User) session.getAttribute("user");

        user.setUserID(currentUser.getUserID());
        
        userDAO.viewUser(user);
        
        User name = userDAO.viewUser(user);

        request.setAttribute("adminInfo", user);
        
        RequestDispatcher dispatcher;
        dispatcher = request.getRequestDispatcher("/admin/adminUpdateProfile.jsp");
        dispatcher.forward(request, response);

    }
    
    private void updateAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        
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
        displayAdmin(request, response);

    }
}
