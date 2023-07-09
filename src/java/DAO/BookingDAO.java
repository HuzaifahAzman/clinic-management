/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.AppointmentHistory;
import entity.Booking;
import entity.User;
import static java.lang.System.out;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class BookingDAO {
    
    private String dbName = "clinicmanagement";
    private String url = "jdbc:mysql://localhost/" + dbName + "?";
    private String userName = "root";
    private String password = "";
    private String driver = "com.mysql.jdbc.Driver";
    
    //patient book appointment
    public void bookAppointment(Booking book) throws ClassNotFoundException, SQLException{
        
        //get connection
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, userName, password);
        
        //define query 
        String query = "INSERT INTO booking"
                + "(user_id, date, time, symptoms, doctor, status, prescription_status, rejecting_remarks)"
                + "VALUES (?,?,?,?,?,?,?,?)";
        
        //prepared Statment
        PreparedStatement ps = con.prepareStatement(query);
        
        java.sql.Date bookDate = new java.sql.Date(book.getDate().getTime());
        
        //insert values
        ps.setInt(1, book.getUser_id());
        ps.setDate(2, bookDate);
        ps.setString(3, book.getTime());
        ps.setString(4, book.getSymptoms());
        ps.setInt(5, book.getDoctor());
        ps.setString(6, book.getStatus());
        ps.setString(7, "Pending");
        ps.setString(8, "");
        
        //execute query
        ps.execute();
        
        //close connection
        con.close();
       
    }
    
    //doctor view appointment
    public List<Booking> viewBooking(User user) throws SQLException, ClassNotFoundException{
        
        List<Booking> booking = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement smst = null;
        
        try{
            
            
            //Connect to database
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            
            //define query
            String query = "SELECT booking.*, user.* "
                    + "FROM booking "
                    + "JOIN user ON booking.doctor = user.ID "
                    + "WHERE user_id=? "
                    + "AND (status=?) "
                    + "OR (status=?) "
                    + "OR (status=?)";
            
            //execute query
            ps = con.prepareStatement(query);
            
            //set user_id
            ps.setInt(1, user.getUserID());
            ps.setString(2, "Approved");
            ps.setString(3, "Rejected");
            ps.setString(4, "Pending");
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                //get data from database
                Date date = rs.getDate("booking.date");
                String time = rs.getString("booking.time");
                String symptoms = rs.getString("booking.symptoms");
                int doctor = rs.getInt("booking.doctor");
                String doctorName = rs.getString("user.name");
                String status = rs.getString("booking.status");
                int booking_id = rs.getInt("booking_id");
                
                Booking book = new Booking( date,  symptoms,  time,  status,  doctorName,  doctor, booking_id);
                booking.add(book);
            }
            
        }finally{
            con.close();
        }
        
        
        return booking;
    }
    
    //patient view appointment
    public List<Booking> appointment(User user) throws ClassNotFoundException, SQLException{
        
        List<Booking> appointment = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement smst = null;
        
        try{
            
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            
            String query = "SELECT booking.*, user.* "
                    + "FROM booking "
                    + "JOIN user ON booking.user_id = user.ID "
                    + "WHERE booking.doctor = ? and booking.status =?";
            
            //execute query
            ps = con.prepareStatement(query);
            
             //set user_id
            ps.setInt(1, user.getUserID());
            ps.setString(2, "Approved");
            
            rs = ps.executeQuery();

            while(rs.next()){
                String patientName = rs.getString("user.name");
                Date date = rs.getDate("booking.date");
                String time  = rs.getString("booking.time");
                String symptoms = rs.getString("booking.symptoms");
                String status = rs.getString("status");
                
                Booking book = new Booking(date, symptoms,  time,  status,  patientName);
                appointment.add(book);
            }
            
            
        }finally{
            con.close();
        }
        
        return appointment;
        
    }
    
    public List<Booking> getAPatientHistory(int id) throws ClassNotFoundException, SQLException{
        
        List<Booking> appointment = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement smst = null;
        
        try{
            
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            
            String query = "SELECT booking.*, user.* "
                    + "FROM booking "
                    + "JOIN user ON booking.doctor = user.ID "
                    + "WHERE user_id=?";
            
            //execute query
            ps = con.prepareStatement(query);
            
             //set user_id
            ps.setInt(1, id);
            
            rs = ps.executeQuery();

            while(rs.next()){
                //get data from database
                Date date = rs.getDate("booking.date");
                String time = rs.getString("booking.time");
                String symptoms = rs.getString("booking.symptoms");
                int doctor = rs.getInt("booking.doctor");
                String doctorName = rs.getString("user.name");
                String status = rs.getString("booking.status");
                int booking_id = rs.getInt("booking_id");
                
                Booking book = new Booking( date,  symptoms,  time,  status,  doctorName,  doctor, booking_id);
                appointment.add(book);
            }
            
            
        }finally{
            con.close();
        }
        
        return appointment;
        
    }
    
    //patient view appointment
    public List<AppointmentHistory> allMonthlyAppointment() throws ClassNotFoundException, SQLException{
        
        List<AppointmentHistory> aptHistory = new ArrayList<>();
        AppointmentHistory apt = new AppointmentHistory();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement smst = null;
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            
            String query = "SELECT doctor, YEAR(date) AS Year, MONTH(date) AS Month, COUNT(date) AS AppointmentCount FROM booking GROUP BY YEAR(date), MONTH(date)";
            
            //execute query
            ps = con.prepareStatement(query);
            
            rs = ps.executeQuery();

            while(rs.next()){
                int year = rs.getInt("Year");
                int month = rs.getInt("Month");
                int aptMonth = rs.getInt("AppointmentCount");
                
                apt = new AppointmentHistory(year, month, aptMonth);
                aptHistory.add(apt);
            }
            
            
        }finally{
            con.close();
        }
        
        return aptHistory;
        
    }
    
    //patient view appointment
    public List<AppointmentHistory> allMonthlyAppointmentReport(int year, int month) throws ClassNotFoundException, SQLException{
        
        List<AppointmentHistory> aptHistory = new ArrayList<>();
        AppointmentHistory apt = new AppointmentHistory();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement smst = null;
        UserDAO userDAO = new UserDAO();
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            
            String query = "SELECT doctor, YEAR(date) AS Year, MONTH(date) AS Month, COUNT(date) AS AppointmentCount FROM booking "
                    + "WHERE YEAR(date) = ? "
                    + "AND MONTH(date) = ? "
                    + "GROUP BY doctor";
            
            //execute query
            ps = con.prepareStatement(query);
            
            ps.setInt(1, year);
            ps.setInt(2, month);
            
            rs = ps.executeQuery();

            while(rs.next()){
                String doctorName = userDAO.getName(rs.getInt("doctor"));
                int aptMonth = rs.getInt("AppointmentCount");
                
                int rejectedCount = this.monthlyAppointmentStatusCount(rs.getInt("doctor"), year, month, "Rejected");
                int approvedCount = this.monthlyAppointmentStatusCount(rs.getInt("doctor"), year, month, "Approved");
                int completedCount = this.monthlyAppointmentStatusCount(rs.getInt("doctor"), year, month, "Completed");
                int pendingCount = this.monthlyAppointmentStatusCount(rs.getInt("doctor"), year, month, "Pending");
                
                apt = new AppointmentHistory(doctorName, year, month, aptMonth, rejectedCount, approvedCount, completedCount, pendingCount);
                aptHistory.add(apt);
            }
            
            
        }finally{
            con.close();
        }
        
        return aptHistory;
        
    }
    
    public int monthlyAppointmentStatusCount(int doctorID, int year, int month, String stat) throws ClassNotFoundException, SQLException{
        
        int count = 0;
        String statName = stat + "Count" ;
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement smst = null;
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            
            String query = "SELECT COUNT(date) AS ? FROM booking \n" +
                    "WHERE status = ? \n" +
                    "AND YEAR(date) = ? \n" +
                    "AND MONTH(date) = ? \n" +
                    "AND doctor = ?";
            
            //execute query
            ps = con.prepareStatement(query);
            
            ps.setString(1, statName);
            ps.setString(2, stat);
            ps.setInt(3, year);
            ps.setInt(4, month);
            ps.setInt(5, doctorID);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                count = rs.getInt(statName);
            }
            
            
        }finally{
            con.close();
        }
        
        return count;
        
    }
    
    public List<Booking> monthlyAppointment(int year, int month) throws ClassNotFoundException, SQLException{
        
        List<Booking> aptHistory = new ArrayList<>();
        AppointmentHistory apt = new AppointmentHistory();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement smst = null;
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            
            String query = "SELECT booking.*, user.* FROM booking JOIN user ON booking.doctor = user.ID WHERE YEAR(date) = ? AND MONTH(date) = ?";
            
            //execute query
            ps = con.prepareStatement(query);
            
            ps.setInt(1, year);
            ps.setInt(2, month);
            
            rs = ps.executeQuery();

            while(rs.next()){
                //get data from database
                Date date = rs.getDate("booking.date");
                String time = rs.getString("booking.time");
                String symptoms = rs.getString("booking.symptoms");
                int doctor = rs.getInt("booking.doctor");
                String doctorName = rs.getString("user.name");
                String status = rs.getString("booking.status");
                int booking_id = rs.getInt("booking_id");
                
                Booking book = new Booking( date,  symptoms,  time,  status,  doctorName,  doctor, booking_id);
                aptHistory.add(book);
            }
            
            
        }finally{
            con.close();
        }
        
        return aptHistory;
        
    }
    
    //Doctor validate appointment
    public List<Booking> validateAppointment(User user) throws ClassNotFoundException, SQLException{
        
        List<Booking> appointment = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement smst = null;
        
        try{
            
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            
            String query = "SELECT booking.*, user.* "
                    + "FROM booking "
                    + "JOIN user ON booking.user_id = user.ID "
                    + "WHERE booking.doctor = ? "
                    + "AND booking.status =?";
            
            //execute query
            ps = con.prepareStatement(query);
            
             //set user_id
            ps.setInt(1, user.getUserID());
            ps.setString(2, "Pending");
            
            rs = ps.executeQuery();

            while(rs.next()){
                String patientName = rs.getString("user.name");
                Date date = rs.getDate("booking.date");
                String time  = rs.getString("booking.time");
                String symptoms = rs.getString("booking.symptoms");
                String status = rs.getString("status");
                int booking_id = rs.getInt("booking.booking_id");
                
                Booking book = new Booking( date,  symptoms,  time,  status,  booking_id,  patientName);
                appointment.add(book);
            }
            
            
        }finally{
            con.close();
        }
        
        return appointment;   
    }
    
    //Doctor get today's meeting
    public List<Booking> todaysAppointment(User user) throws ClassNotFoundException, SQLException{
        
        List<Booking> todaysAppointment = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement smst = null;
        
        try{
            //connect to database
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            
            //query for today's appointment
            String query = "SELECT booking.*, user.* "
                    + "FROM booking "
                    + "JOIN user on booking.user_id = user.ID "
                    + "WHERE booking.doctor=? "
                    + "AND booking.status=? "
                    + "AND booking.date=?";
            
            //execute query
            ps = con.prepareStatement(query);
            
            //get current date
            long millis = System.currentTimeMillis();  
            java.sql.Date currentDate = new java.sql.Date(millis); 

            
            ps.setInt(1, user.getUserID());
            ps.setString(2, "Approved");
            ps.setDate(3, currentDate);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                String patientName = rs.getString("user.name");
                Date date = rs.getDate("booking.date");
                String time  = rs.getString("booking.time");
                String symptoms = rs.getString("booking.symptoms");
                String status = rs.getString("status");
                int booking_id = rs.getInt("booking.booking_id");
                
                Booking book = new Booking( date,  symptoms,  time,  status,  booking_id,  patientName);
                todaysAppointment.add(book);
            }                  
                   
 
        }finally{
           con.close();
        }
        
        
        return todaysAppointment;
        
    }
    
    //get appointment history
    public List<Booking> filterAppointment(User user, java.util.Date date) throws ClassNotFoundException, SQLException{
        
        List<Booking> appointmentHistory = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement smst = null;
        
        try{
            //connect to database
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            
            //query for today's appointment
            String query = "SELECT booking.*, user.* "
                    + "FROM booking "
                    + "JOIN user on booking.user_id = user.ID "
                    + "WHERE booking.doctor=? "
                    + "AND booking.status=? "
                    + "AND booking.date=?";
            
            //execute query
            ps = con.prepareStatement(query);           
            
            //convert util.date to sql.date
            java.sql.Date bookDate = new java.sql.Date(date.getTime());
 
            ps.setInt(1, user.getUserID());
            ps.setString(2, "Approved");
            ps.setDate(3, bookDate);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                String patientName = rs.getString("user.name");
                Date date1 = rs.getDate("booking.date");
                String time  = rs.getString("booking.time");
                String symptoms = rs.getString("booking.symptoms");
                String status = rs.getString("status");
                int booking_id = rs.getInt("booking.booking_id");
                
                Booking book = new Booking(date1,  symptoms,  time,  status,  booking_id,  patientName);
                appointmentHistory.add(book);
            }                  
                   
 
        }finally{
           con.close();
        }
        
        return appointmentHistory;

    }
    
    //get appointment history
    public List<Booking> appointmentHistory(User user, java.util.Date date) throws ClassNotFoundException, SQLException{
        
        List<Booking> appointmentHistory = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement smst = null;
        
        try{
            //connect to database
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            
            //query for today's appointment
            String query = "SELECT booking.*, user.* "
                    + "FROM booking "
                    + "JOIN user on booking.user_id = user.ID "
                    + "WHERE booking.doctor=? "
                    + "AND booking.status=? "
                    + "AND booking.date=?";
            
            //execute query
            ps = con.prepareStatement(query);           
            
            //convert util.date to sql.date
            java.sql.Date bookDate = new java.sql.Date(date.getTime());
 
            ps.setInt(1, user.getUserID());
            ps.setString(2, "Approved");
            ps.setDate(3, bookDate);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                String patientName = rs.getString("user.name");
                Date date1 = rs.getDate("booking.date");
                String time  = rs.getString("booking.time");
                String symptoms = rs.getString("booking.symptoms");
                String status = rs.getString("status");
                int booking_id = rs.getInt("booking.booking_id");
                
                Booking book = new Booking(date1,  symptoms,  time,  status,  booking_id,  patientName);
                appointmentHistory.add(book);
            }                  
                   
 
        }finally{
           con.close();
        }
        
        return appointmentHistory;

    }
    
    //get number of appointment on a specific date
    public List<Integer> getDate(Booking book) throws ClassNotFoundException, SQLException{
        List<Integer> getDate = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement smst = null;
        
        try{
            //get connection
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            
            //set query
            String query = "SELECT * FROM booking "
                    + "WHERE date=? AND doctor=?";
            
            //run the query
            ps = con.prepareStatement(query);
            
            java.sql.Date bookDate = new java.sql.Date(book.getDate().getTime());
            
            ps.setDate(1, bookDate);
            ps.setInt(2, book.getDoctor());
            
            //execute query
            rs = ps.executeQuery();
            
            while(rs.next()){
                int booking_id = rs.getInt("booking_id");
                
                getDate.add(booking_id);
            }
            
        }finally{
            
        }
        
        return getDate;
    }
    
    public List<Booking> appointmentHistory(User user) throws ClassNotFoundException, SQLException{
        List<Booking> appointmentHistory = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement smst = null;
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            
            String query = "SELECT booking.*, user.* "
                    + "FROM booking "
                    + "JOIN user on booking.user_id = user.ID "
                    + "WHERE booking.doctor=? "
                    + "AND booking.status=? ";
            
            ps = con.prepareStatement(query);

            ps.setInt(1, user.getUserID());
            ps.setString(2, "Completed");
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                String patientName = rs.getString("user.name");
                Date date1 = rs.getDate("booking.date");
                String time  = rs.getString("booking.time");
                String symptoms = rs.getString("booking.symptoms");
                String status = rs.getString("booking.status");
                int booking_id = rs.getInt("booking.booking_id");
                
                Booking book = new Booking(date1,  symptoms,  time,  status,  booking_id,  patientName);
                appointmentHistory.add(book);
            }               
            
            
        }finally{
            con.close();
        }
        
        return appointmentHistory;
    }
    
    //get all completed appointment for pharmacist to see
    public List<Booking> completedAppointment() throws ClassNotFoundException, SQLException{
        List<Booking> completedAppointment = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement smst = null;
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            
            String query = "SELECT booking.*, user.*"
                    + "FROM booking "
                    + "JOIN user ON booking.user_id = user.ID "
                    + "WHERE booking.status=? "
                    + "AND booking.prescription_status=?";
            
            ps = con.prepareStatement(query);
            
            ps.setString(1, "Completed");
            ps.setString(2, "Pending");
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                String patientName = rs.getString("user.name");
                int doctorID = rs.getInt("booking.doctor");
                int bookingID = rs.getInt("booking.booking_id");
                
                String query2 = "SELECT * FROM user where ID=?";
                
                PreparedStatement ps1 = con.prepareStatement(query2);
                
                ps1.setInt(1, doctorID);
                
                ResultSet rs1 = ps1.executeQuery();
                
                while(rs1.next()){
                    String doctorName = rs1.getString("name");
                    Booking book = new Booking(patientName, doctorName, bookingID);
                    completedAppointment.add(book);
                }               
                
            }    
            
            
            
            
        }finally{
            con.close();
        }
        
        return completedAppointment;
    }
    
    public List<Booking> completedPatientPrescription(User user) throws ClassNotFoundException, SQLException{
        List<Booking> completedPatientAppointment = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement smst = null;
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            
            String query = "SELECT booking.*, user.*"
                    + "FROM booking "
                    + "JOIN user ON booking.user_id = user.ID "
                    + "WHERE booking.status=?"
                    + "AND booking.prescription_status=? "
                    + "AND booking.user_id=?";
            
            ps = con.prepareStatement(query);
            
            ps.setString(1, "Completed");
            ps.setString(2, "Completed");
            ps.setInt(3, user.getUserID());
            
            rs = ps.executeQuery();
            
            while(rs.next()){
              int booking_id = rs.getInt("booking.booking_id");
              Date date1 = rs.getDate("booking.date");
              String time = rs.getString("booking.time");
              int doctorID = rs.getInt("booking.doctor");
              
              String query2 = "SELECT * FROM user where ID=?";
                
              PreparedStatement ps1 = con.prepareStatement(query2);
                
              ps1.setInt(1, doctorID);
                
              ResultSet rs1 = ps1.executeQuery();
                
                while(rs1.next()){
                    String doctorName = rs1.getString("name");
                    Booking book = new Booking(booking_id, date1, time, doctorName);
                    completedPatientAppointment.add(book);
                }               
              
            }    
            
            
            
            
        }finally{
            con.close();
        }
        
        return completedPatientAppointment;
    }
    
    //view doctor's remarks to patient
    public String viewRemarks(User user) throws ClassNotFoundException, SQLException{
        String remarks = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement smst = null;
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            
            String query = "SELECT * FROM booking "
                    + "WHERE user_id=? "
                    + "AND status=?";
            
            ps = con.prepareStatement(query);
            
            ps.setInt(1, user.getUserID());
            ps.setString(2, "Rejected");
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                remarks = rs.getString("rejecting_remarks");
            }                   
                    
            
        }finally{
            con.close();          
        }
        
        
        return remarks;
    }
    
    //doctor reject patient appointment
    public void rejectAppointment(int book_id, String comments) throws ClassNotFoundException, SQLException{
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement smst = null;
        
        try{
            
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            
            String query = "UPDATE booking "
                    + "SET rejecting_remarks=?, status=? "
                    + "WHERE booking_id=? ";
            
            ps = con.prepareStatement(query);
            
            ps.setString(1, comments);
            ps.setString(2, "Rejected");
            ps.setInt(3, book_id);
            
            ps.execute();
            
            
        }finally{
            con.close();
        }
    }
    
    //doctor approve appointment
    public void approveAppointment(int book_id) throws ClassNotFoundException, SQLException{
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement smst = null;
        
        try{
            
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            
            String query = "UPDATE booking "
                    + "SET status=? "
                    + "WHERE booking_id=? ";
            
            ps = con.prepareStatement(query);
            
            ps.setString(1, "Approved");
            ps.setInt(2, book_id);
            
            ps.execute();
            
        }finally{
            con.close();
        }
    }
    
    //patient cancel appointment
    public void cancelAppointment(int book_id) throws ClassNotFoundException, SQLException{

        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, userName, password);

        String query = "DELETE FROM booking "
                + "WHERE booking_id=? ";

        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, book_id);

        ps.execute();

        con.close();
        
    }
    
    //done with appointment
    public void doneAppointment(int book_id) throws ClassNotFoundException, SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement smst = null;
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            
            String query = "UPDATE booking "
                    + "SET status=? "
                    + "WHERE booking_id=?";
                    
            ps = con.prepareStatement(query);
            
            ps.setString(1, "Completed");
            ps.setInt(2, book_id);
            
            ps.execute();            
            
        }finally{
            con.close();
        }
    }
    
}
