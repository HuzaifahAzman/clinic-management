/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.text.DateFormatSymbols;
import java.time.Month;
import java.util.List;

/**
 *
 * @author user
 */
public class AppointmentHistory {
    private int month, year, appointmentCount;
    private int rejectedCount, approvedCount, completedCount, pendingCount;
    private String doctorName;
    
    public AppointmentHistory(){
        
    }
    
    public AppointmentHistory(int year, int month, int appointmentCount){
        this.month = month;
        this.year = year;
        this.appointmentCount = appointmentCount;
    }
    
    public AppointmentHistory(String doctorName, int year, int month, int appointmentCount, int rejectedCount, 
            int approvedCount, int completedCount, int pendingCount){
        this.doctorName = doctorName;
        this.month = month;
        this.year = year;
        this.appointmentCount = appointmentCount;
        this.rejectedCount = rejectedCount;
        this.approvedCount = approvedCount;
        this.completedCount = completedCount;
        this.pendingCount = pendingCount;
    }

    /**
     * @return the month
     */
    public int getMonth() {
        return month;
    }
    
    /**
     * @return the month
     */
    public String getMonthString() {
        return Month.of(month).name();
    }

    /**
     * @param month the month to set
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the AppointmentCount
     */
    public int getAppointmentCount() {
        return appointmentCount;
    }

    /**
     * @param appointmentCount the AppointmentCount to set
     */
    public void setAppointmentCount(int appointmentCount) {
        this.appointmentCount = appointmentCount;
    }

    /**
     * @return the rejectedCount
     */
    public int getRejectedCount() {
        return rejectedCount;
    }

    /**
     * @param rejectedCount the rejectedCount to set
     */
    public void setRejectedCount(int rejectedCount) {
        this.rejectedCount = rejectedCount;
    }

    /**
     * @return the approvedCount
     */
    public int getApprovedCount() {
        return approvedCount;
    }

    /**
     * @param approvedCount the approvedCount to set
     */
    public void setApprovedCount(int approvedCount) {
        this.approvedCount = approvedCount;
    }

    /**
     * @return the completedCount
     */
    public int getCompletedCount() {
        return completedCount;
    }

    /**
     * @param completedCount the completedCount to set
     */
    public void setCompletedCount(int completedCount) {
        this.completedCount = completedCount;
    }

    /**
     * @return the pendingCount
     */
    public int getPendingCount() {
        return pendingCount;
    }

    /**
     * @param pendingCount the pendingCount to set
     */
    public void setPendingCount(int pendingCount) {
        this.pendingCount = pendingCount;
    }

    /**
     * @return the doctorName
     */
    public String getDoctorName() {
        return doctorName;
    }

    /**
     * @param doctorName the doctorName to set
     */
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

}
