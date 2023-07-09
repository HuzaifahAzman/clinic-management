/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Adam
 */
public class Prescription {
    private int prescription_id, booking_id, user_id, dosage;
    private int stock;
    private String patient_name, medicine, intake, frequency, dosage_type;
    
    public Prescription(){
    
    }

    public Prescription(int dosage, String frequency, String medicine, String intake, String dosage_type) {
        this.dosage = dosage;
        this.frequency = frequency;
        this.medicine = medicine;
        this.intake = intake;
        this.dosage_type = dosage_type;
    }

    public void setDosage_type(String dosage_type) {
        this.dosage_type = dosage_type;
    }

    public String getDosage_type() {
        return dosage_type;
    }   
    

    public void setIntake(String intake) {
        this.intake = intake;
    }

    public String getIntake() {
        return intake;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public int getDosage() {
        return dosage;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }
        
    public int getPrescription_id() {
        return prescription_id;
    }

    public void setPrescription_id(int prescription_id) {
        this.prescription_id = prescription_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
    
    
    
}
