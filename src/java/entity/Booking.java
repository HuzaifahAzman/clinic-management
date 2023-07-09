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
public class Booking {
    
    private String symptoms, time, status, doctorName, patientName, rejectingRemarks;
    private int user_id, doctor, booking_id;
    private Date date;

    public Booking(){
        
    }
    
    public Booking(int user_id, Date date, String time, String symptoms, int doctor, String status){
        this.user_id = user_id;
        this.date = date;
        this.symptoms = symptoms;
        this.time = time;
        this.doctor = doctor;
        this.status = status;
    }

    //get booking
    public Booking(Date date, String symptoms, String time, String status, String doctorName, int doctor, int booking_id) {
        this.date = date;
        this.symptoms = symptoms;
        this.time = time;
        this.status = status;
        this.doctorName = doctorName;
        this.doctor = doctor;
        this.booking_id = booking_id;
    }

    //get booking id 
    public Booking(Date date, String symptoms, String time, String status, int booking_id, String patientName) {
        this.date = date;
        this.symptoms = symptoms;
        this.time = time;
        this.status = status;
        this.patientName = patientName;
        this.booking_id = booking_id;
    }
    
    //get patient name
    public Booking(Date date, String symptoms, String time, String status, String patientName) {
        this.date = date;
        this.symptoms = symptoms;
        this.time = time;
        this.status = status;
        this.patientName = patientName;
    }
    
    public Booking(String patientName, String doctorName, int booking_id){
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.booking_id = booking_id;
    }

    public Booking(int booking_id, Date date, String time, String doctorName) {
        this.booking_id = booking_id;
        this.date = date;
        this.time = time;
        this.doctorName = doctorName;
    }
    
    public void setRejectingRemarks(String rejectingRemarks) {
        this.rejectingRemarks = rejectingRemarks;
    }

    public String getRejectingRemarks() {
        return rejectingRemarks;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public int getBooking_id() {
        return booking_id;
    } 

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }    
    
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorName() {
        return doctorName;
    }
    
    
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
        public int getDoctor() {
        return doctor;
    }

    public void setDoctor(int doctor) {
        this.doctor = doctor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
