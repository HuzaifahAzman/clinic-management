/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.Booking;
import entity.User;
import java.sql.*; //Step 1 - import package
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class UserDAO {
    
    private String dbName = "clinicmanagement";
    private String url = "jdbc:mysql://localhost/" + dbName + "?";
    private String userName = "root";
    private String password = "";
    private String driver = "com.mysql.jdbc.Driver";
   
    
    public boolean findUser(User user) throws ClassNotFoundException, SQLException{ 
        
        //get connection 
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, userName, password);
        
        //set query
        String query = "SELECT * FROM user WHERE email=? and password=?";
        
        //create prepared statement
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, user.getEmail());
        st.setString(2, user.getPassword());
        
        //create result set
        ResultSet rs;
        
        //execute query
        rs = st.executeQuery();
        
        //validate user
        if(rs.next()){
            
            String role = rs.getString("role");
            user.setRole(role);   
            return true;
        }else{
            
        }
 
        return false;
    }
    
    public void registerUser(User user) throws ClassNotFoundException, SQLException{
        //get connection 
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, userName, password);
        
        //set query
        String query = "insert into user"
                + "(name, email, password, phone, DOB, ic, gender, role) "
                + "VALUES (?,?,?,?,?,?,?,?)";
        
        PreparedStatement ps = con.prepareStatement(query);
        
        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getPassword());
        ps.setString(4, user.getPhone());
        ps.setString(5, user.getDOB());
        ps.setString(6, user.getIc());
        ps.setString(7, user.getGender());
        ps.setString(8, user.getRole());
        
        //execute query
        ps.execute();
        con.close();
    }
    
    public User getUser(String email) throws ClassNotFoundException, SQLException{
        User user = new User();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
        //get connection 
        Class.forName(driver);
        con = DriverManager.getConnection(url, userName, password);
        
        //set query
        String query = "SELECT * FROM user "
                + "WHERE email=?";
        
        ps = con.prepareStatement(query);
        ps.setString(1, email);
        
        rs = ps.executeQuery();
        
        while(rs.next()){
            //get parameters
            int id = rs.getInt("ID");
            String role = rs.getString("role");

            //set parameter to class
            user.setUserID(id);
            user.setRole(role);
        }
        
        }finally{
            con.close();
        }
        
        return user;
    }
    
    public List<User> getPatientHistory() throws ClassNotFoundException, SQLException{
        List<User> userList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
        //get connection 
        Class.forName(driver);
        con = DriverManager.getConnection(url, userName, password);
        
        //set query
        String query = "SELECT user.ID, user.name, user.email, COUNT(booking.booking_id) AS totalAppointment "
                + "FROM user "
                + "JOIN booking ON user.ID=booking.user_id "
                + "WHERE booking.user_id=user.ID "
                + "GROUP BY user.ID";
        
        ps = con.prepareStatement(query);
        
        rs = ps.executeQuery();
        
        while(rs.next()){
            //get parameters
            int userID = rs.getInt("user.ID");
            String name = rs.getString("user.name");
            String email = rs.getString("user.email");
            int totalAppointment = rs.getInt("totalAppointment");

            //set parameter to class
            User user = new User(userID, name, email, totalAppointment);
            userList.add(user);
        }
        
        }finally{
            con.close();
        }
        
        return userList;
    }
    
    //get patient info
    public User patientInfo(int bookingID) throws SQLException, ClassNotFoundException{
        User user = new User();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement smst = null;
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            
            String query = "SELECT booking.*, user.* "
                    + "FROM booking "
                    + "JOIN user on booking.user_id=user.ID "
                    + "WHERE booking.booking_id=? ";
            
            ps = con.prepareStatement(query);
            
            ps.setInt(1, bookingID);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                String patient_name = rs.getString("user.name");
                String DOB = rs.getString("user.DOB");
                String gender = rs.getString("user.gender");
                int user_id = rs.getInt("user.ID");
                
                user.setName(patient_name);
                user.setDOB(DOB);
                user.setGender(gender);
                user.setUserID(user_id);
            }

        }finally{
            
        }
        
        return user;
    }
    
    public int getDoctorID(String name) throws ClassNotFoundException, SQLException {
        int doctorID = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            
            String query = "SELECT * FROM user"
                    + " WHERE name=?";
            
            ps = con.prepareStatement(query);
            
            ps.setString(1, name);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                doctorID = rs.getInt("ID");
            }
            
            
            
        }finally{
            con.close();
        }
        
        
        return doctorID;
        
    }
    
    public String getName(int doctorID) throws ClassNotFoundException, SQLException {
        String doctorName = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            
            String query = "SELECT name FROM user"
                    + " WHERE ID=?";
            
            ps = con.prepareStatement(query);
            
            ps.setInt(1, doctorID);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                doctorName = rs.getString("name");
            }
            
            
            
        }finally{
            con.close();
        }
        
        
        return doctorName;
        
    }
    
    public List<User> getStaff() throws ClassNotFoundException, SQLException {
        List<User> doctor = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            
            String query = "SELECT * FROM user "
                    + "WHERE role=? "
                    + "OR role=?";
            
            ps = con.prepareStatement(query);
            
            ps.setString(1, "doctor");
            ps.setString(2, "pharmacist");
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                int userID = rs.getInt("ID");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                String DOB = rs.getString("DOB");
                String ic = rs.getString("ic");
                String role = rs.getString("role");
                String gender = rs.getString("gender");
                
                User user = new User(userID, name, email, password, phone, DOB, ic, role, gender);
                
                doctor.add(user);
            }
            
            
        }finally{
            con.close();
        }
        
        
        return doctor;
        
    }
    
    public void updateUser(User user) throws ClassNotFoundException, SQLException{
        
        //get connection
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, userName, password);
        
        //define query 
        String query = "UPDATE user SET name = ?, email = ?, password = ?, phone = ?, DOB = ?, ic = ?, gender = ? , role = ? WHERE ID = ?";
            
        //prepared Statment
        PreparedStatement ps = con.prepareStatement(query);
        //insert values
        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getPassword());
        ps.setString(4, user.getPhone());
        ps.setString(5, user.getDOB());
        ps.setString(6, user.getIc());
        ps.setString(7, user.getGender());
        ps.setString(8, user.getRole());
        ps.setInt(9, user.getUserID());
        
        //execute query
        ps.execute();
        
        //close connection
        con.close();
       
    }
    
    public void deleteUser(int ID) throws ClassNotFoundException, SQLException{
        
        //get connection
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, userName, password);
        
        //define query 
        String query = "DELETE FROM user WHERE ID = ?";
        
        //prepared Statment
        PreparedStatement ps = con.prepareStatement(query);
        
        //insert values
        ps.setInt(1, ID);
        
        //execute query
        ps.execute();
        
        //close connection
        con.close();
       
    }
    
    public User viewUser(User user) throws ClassNotFoundException, SQLException {
        //User user = new User();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
        //get connection 
        Class.forName(driver);
        con = DriverManager.getConnection(url, userName, password);
        
        //set query
        String query = "SELECT * FROM user "
                + "WHERE ID=?";
        
        ps = con.prepareStatement(query);
        ps.setInt(1, user.getUserID());
        
        rs = ps.executeQuery();
        
        while(rs.next()){

                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String DOB = rs.getString("DOB");
                String ic = rs.getString("ic");
                String password = rs.getString("password");
                
                user.setName(name);
                user.setEmail(email);
                user.setPhone(phone);
                user.setDOB(DOB);
                user.setIc(ic);
                user.setPassword(password);
                
            }
        }finally{
            con.close();
        }
        
        return user;
    }
    
    public User updateUserProfile(User user) throws ClassNotFoundException, SQLException{
        //User user = new User();
        Connection con = null;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        
        try{
        //get connection 
        Class.forName(driver);
        con = DriverManager.getConnection(url, userName, password);
        
        //set query
        String query = "UPDATE user "
                + "SET name = ?, email = ?, phone = ?, DOB = ?, ic = ?, password = ?"
                + "WHERE ID= ?";
        
        ps = con.prepareStatement(query);
        
        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getPhone());
        ps.setString(4, user.getDOB());
        ps.setString(5, user.getIc());
        ps.setString(6, user.getPassword());
        ps.setInt(7, user.getUserID());
       
        
        ps.execute();

        
        }finally{
            con.close();
        }
        
        return user;
    }

}