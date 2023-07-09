/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author User
 */
public class User {
    private int userID;
    private String name;
    private String email;
    private String password;
    private String phone; 
    private String DOB;
    private String ic;
    private String gender;
    private String role;
    private int totalAppointment;

   
    public User(){
        
    }
    
    public User(int userID, String name, String email, String phone, String DOB, String ic, String role){
        this.userID = userID;
        this.name = name; 
        this.email = email;
        this.phone = phone;
        this.DOB = DOB;
        this.ic = ic;
        this.role = role;
    }
    
    public User(String name, String DOB, String gender, int userID){
        this.name = name;
        this.DOB = DOB;
        this.gender = gender;
        this.userID = userID;
    }
    
    public User(int userID, String name, String email, int totalAppointment){
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.totalAppointment = totalAppointment;
    }
    
    public User(int userID, String name, String email, String password, String phone, String DOB, String ic, String role, String gender){
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.DOB = DOB;
        this.ic = ic;
        this.role = role;
        this.gender = gender;
    }
    
    public void setUserID(int userID){this.userID = userID;}
    public int getUserID(){return userID;}
    
    public void setName(String name){this.name = name;}
    public String getName(){return name;}
    
    public void setEmail(String email){this.email = email;}
    public String getEmail(){return email;}
    
    public void setPassword(String password){this.password = password;}
    public String getPassword(){return password;}
    
    public void setPhone(String phone){this.phone = phone;}
    public String getPhone(){return phone;}
    
    public void setDOB(String DOB){this.DOB = DOB;}
    public String getDOB(){return DOB;}
    
    public void setIc(String ic){ this.ic = ic;}
    public String getIc(){return ic;}
    
    public void setRole(String role){this.role = role;}
    public String getRole(){return role;}
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the totalAppointment
     */
    public int getTotalAppointment() {
        return totalAppointment;
    }

    /**
     * @param totalAppointment the totalAppointment to set
     */
    public void setTotalAppointment(int totalAppointment) {
        this.totalAppointment = totalAppointment;
    }
   
    
    
}
