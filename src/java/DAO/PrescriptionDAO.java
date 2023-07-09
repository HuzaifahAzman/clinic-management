/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.Booking;
import entity.Prescription;
import entity.User;
import java.sql.*; //Step 1 - import package
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adam
 */
public class PrescriptionDAO {
    
    private String dbName = "clinicmanagement";
    private String url = "jdbc:mysql://localhost/" + dbName + "?";
    private String userName = "root";
    private String password = "";
    private String driver = "com.mysql.jdbc.Driver";
    
    //add prescription for specific patient
    public void addPrescription (int doctorID, int bookingID, int patientID, Prescription prescription) throws ClassNotFoundException, SQLException{

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement smst = null;
        
        try{
            
            //connect to database
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            
            
                String query = "INSERT INTO prescription "
                        + "(user_id, booking_id, doctor, dosage, dosage_type, frequency, medicine, intake) "
                        + "VALUES (?,?,?,?,?,?,?,?)";

                //execute query
                ps = con.prepareStatement(query);
            
                //insert values in database
                ps.setInt(1, patientID);
                ps.setInt(2, bookingID);
                ps.setInt(3, doctorID);
                ps.setInt(4, prescription.getDosage());
                ps.setString(5, prescription.getDosage_type());
                ps.setString(6, prescription.getFrequency());
                ps.setString(7, prescription.getMedicine());
                ps.setString(8, prescription.getIntake());
                
                //execute prepared statement
                ps.execute();
            
        }finally{
            //close connection 
            con.close();
        }
        
    }
    
    //list of specific patient prescription
    public List<Prescription> detailPrescription(int bookingID) throws ClassNotFoundException, SQLException{
        List<Prescription> detailPrescription = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement smst = null;
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            
            String query = "SELECT booking.*, prescription.* "
                    + "FROM booking "
                    + "JOIN prescription ON booking.booking_id = prescription.booking_id "
                    + "WHERE booking.booking_id=?";
            
            ps = con.prepareStatement(query);
            
            ps.setInt(1, bookingID);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                String medicine = rs.getString("prescription.medicine");
                int dosage = rs.getInt("prescription.dosage");
                String dosage_type = rs.getString("prescription.dosage_type");
                String intake = rs.getString("prescription.intake");
                String frequency = rs.getString("prescription.frequency");
                
                Prescription add = new Prescription(dosage, frequency, medicine, intake, dosage_type);
                
                detailPrescription.add(add);
            }

        }finally{
            con.close();
        }
 
        return detailPrescription;
    }
    
    //complete task for one patient
    public void completeTask(int booking_id) throws ClassNotFoundException, SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement smst = null;
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            
            String query = "UPDATE booking "
                    + "SET prescription_status=? "
                    + "WHERE booking_id=?";
                   
            
            ps = con.prepareStatement(query);
            
            ps.setString(1, "Completed");
            ps.setInt(2, booking_id);
            
            ps.execute();            
            
        }finally{
            con.close();
        }
    }
    
    //view all completed prescription for pharmacist to see
    public List<Booking> completedPrescription() throws ClassNotFoundException, SQLException{
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
                    + "AND booking.prescription_status=? ";
            
            ps = con.prepareStatement(query);
            
            ps.setString(1, "Completed");
            ps.setString(2, "Completed");
            
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
    
    public void deletePrescriptionBooking(int book_id) throws ClassNotFoundException, SQLException{

        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, userName, password);

        String query = "DELETE FROM prescription "
                + "WHERE booking_id=? ";

        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, book_id);

        ps.execute();

        con.close();
        
    }
}
