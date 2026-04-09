package edu.ucalgary.oop;

import java.util.*;
import java.time.*;

public class Volunteer {

    private int volunteerID;
    private String name;
    private boolean isAvailable;
    private int maxConcurrentCalls;
    private int currentCalls;
    private LocalDate lastAvailableChange;
    private String phoneNumber;
    private List<VolunteerSpecialty> specialties;

    public Volunteer(int volunteerID, String name, String phoneNumber, boolean isAvailable, int maxConcurrentCalls, 
    		LocalDate lastAvailableChange, int currentCalls){
    	// Normalize phone number
    	String phoneRegex = "^(\\d{3})[\\s.-]?(\\d{3})[\\s.-]?(\\d{4})$";
		String replacement = "$1-$2-$3";
    	
		this.volunteerID = volunteerID;
        this.name = name;
        this.phoneNumber = phoneNumber.replaceAll(phoneRegex, replacement);
        this.isAvailable = isAvailable;
        this.maxConcurrentCalls = maxConcurrentCalls;
        this.currentCalls = currentCalls;
        this.lastAvailableChange = lastAvailableChange;

        this.specialties = new ArrayList<>();
    }

    public int getVolunteerID(){
        return volunteerID;
    }

    public String getName(){
        return name;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public boolean getAvailability(){
        return isAvailable;
    }

    public int getMaxConcurrentCalls(){
        return maxConcurrentCalls;
    }

    public int getCurrentCalls(){
        return currentCalls;
    }

    public LocalDate getLastAvailableChange(){
        return lastAvailableChange;
    }

    public void setAvailability(boolean isAvailable){
    	// If availability changes, update lastAvaiableChange
    	if (this.isAvailable != isAvailable) {
    		lastAvailableChange = LocalDate.now();
    	}
    	
        this.isAvailable = isAvailable;
    }

    public void setCurrentCalls(int currentCalls){
        this.currentCalls = currentCalls;
    }

    public List<VolunteerSpecialty> getSpecialties(){
        return specialties;
    }   
    
    public void addSpecialty(VolunteerSpecialty specialty){
        specialties.add(specialty);
    }
    
    public String toString() {
    	String specialtiesString = "";
    	
    	// Add specialties to the string if there are any
    	if (specialties.size() == 0) {
    		specialtiesString = "No specialty";
    	}
    	else {
			for (VolunteerSpecialty specialty : specialties) {
				specialtiesString += specialty.toString();
			}
    	}
    	
    	return "\nID: " + volunteerID +
    			"\n\tName: " + name +
    			"\n\tPhone number:" + phoneNumber +
    			"\n\tIs available: " + ((isAvailable) ? "Yes" : "No") + 
    			"\n\tMax concurrent calls: " + maxConcurrentCalls +
    			"\n\tNumber of current calls: " + currentCalls +
    			"\n\tLast available change: " + lastAvailableChange +
    			"\nSpecialty:" + specialtiesString;
    }
}
