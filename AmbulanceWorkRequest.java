/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.workqueue;

import business.useraccount.UserAccount;

/**
 *
 * @author darshan
 */
public class AmbulanceWorkRequest extends WorkRequest {
    
    private String patientDetails;
    private String ambulanceType;

    // Constructor
    public AmbulanceWorkRequest(String message, UserAccount sender, String patientDetails) {
        super(message, sender);
        this.patientDetails = patientDetails;
    }

    // Getters and Setters
    public String getPatientDetails() {
        return patientDetails;
    }

    public void setPatientDetails(String patientDetails) {
        this.patientDetails = patientDetails;
    }

    public String getAmbulanceType() {
        return ambulanceType;
    }

    public void setAmbulanceType(String ambulanceType) {
        this.ambulanceType = ambulanceType;
    }
}
