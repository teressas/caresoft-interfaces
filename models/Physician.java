package com.caresoft.models;

import java.util.ArrayList;
import java.util.Date;

import com.caresoft.clinicapp.HIPAACompliantUser;

public class Physician extends User implements HIPAACompliantUser {
	
	//... imports class definition...
	
	public Physician(Integer id) {
		super(id);
	}
	
	@Override
	public boolean assignPin(int pin) {
		int length = String.valueOf(pin).length();
		if(length == 4) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean accessAuthorized(Integer confirmedAuthID) {
		if (confirmedAuthID == super.getId()){
			return true;
		}
		return false;
	}
	
	// Inside class:    
    private ArrayList<String> patientNotes;
	
    // TO DO: Constructor that takes an ID
    // TO DO: Implement HIPAACompliantUser!
    /**
	 * @param id
	 * @param id2
	 */
	public Physician(Integer id, int id2) {
		super(id);
		id = id2;
	}

	
    public void newPatientNotes(String notes, String patientName, Date date) {
        String report = String.format(
            "Datetime Submitted: %s \n", date);
        report += String.format("Reported By ID: %s\n", this.id);
        report += String.format("Patient Name: %s\n", patientName);
        report += String.format("Notes: %s \n", notes);
        this.patientNotes.add(report);
    }

    // TO DO: Setters & Getters


	public ArrayList<String> getPatientNotes() {
		return patientNotes;
	}


	public void setPatientNotes(ArrayList<String> patientNotes) {
		this.patientNotes = patientNotes;
	}

}
