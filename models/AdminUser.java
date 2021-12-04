package com.caresoft.models;

import java.util.ArrayList;
import java.util.Date;

import com.caresoft.clinicapp.HIPAACompliantAdmin;
import com.caresoft.clinicapp.HIPAACompliantUser;

public class AdminUser extends User implements HIPAACompliantUser, HIPAACompliantAdmin {

//... imports class definition...

    
    // Inside class:
    private Integer employeeID;
    private String role;
    private ArrayList<String> securityIncidents;
    
	@Override
	public ArrayList<String> reportSecurityIncidents() {
		return securityIncidents;
	}
	
	public AdminUser(int id, String role) {
		super(id);
		this.role = role;
		securityIncidents = new ArrayList<String>();
	}
	
	// FROM HIPAACompliantUser 
    

	@Override
	public boolean assignPin(int pin) {
		int length = String.valueOf(pin).length();
		if(length >= 6) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean accessAuthorized(Integer confirmedAuthID) {
		if (confirmedAuthID != this.id){
			this.authIncident();
			return false;
		}
		
		return true;
	}
	
    // TO DO: Implement a constructor that takes an ID and a role
    /**
	 * @param id
	 * @param employeeID
	 * @param role
	 */
	public AdminUser(Integer id, Integer employeeID, String role) {
		super(id);
		this.employeeID = employeeID;
		this.role = role;
	}
    
    // TO DO: Implement HIPAACompliantUser!
    // TO DO: Implement HIPAACompliantAdmin!
    
    public void newIncident(String notes) {
        String report = String.format(
            "Datetime Submitted: %s \n,  Reported By ID: %s\n Notes: %s \n", 
            new Date(), this.id, notes
        );
        securityIncidents.add(report);
    }
    public void authIncident() {
        String report = String.format(
            "Datetime Submitted: %s \n,  ID: %s\n Notes: %s \n", 
            new Date(), this.id, "AUTHORIZATION ATTEMPT FAILED FOR THIS USER"
        );
        securityIncidents.add(report);
    }
    
// TO DO: Setters & Getters

	public Integer getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public ArrayList<String> getSecurityIncidents() {
		return securityIncidents;
	}

	public void setSecurityIncidents(ArrayList<String> securityIncidents) {
		this.securityIncidents = securityIncidents;
	}

}
