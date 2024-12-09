package com.Model;

import java.sql.Date;

public class Prescription {
    private int id;
    private String patientName;
    private String doctorName;
    private String medicineName;
    private String dosage;
    private int quantity;
    private Date prescriptionDate;
    private String notes;

    public Prescription() {}

    public Prescription(int id, String patientName, String doctorName, String medicineName, String dosage, int quantity, Date prescriptionDate, String notes) {
        this.id = id;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.medicineName = medicineName;
        this.dosage = dosage;
        this.quantity = quantity;
        this.prescriptionDate = prescriptionDate;
        this.notes = notes;
    }

    public Prescription(String patientName, String doctorName, String medicineName, String dosage, int quantity, Date prescriptionDate, String notes) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.medicineName = medicineName;
        this.dosage = dosage;
        this.quantity = quantity;
        this.prescriptionDate = prescriptionDate;
        this.notes = notes;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(Date prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
