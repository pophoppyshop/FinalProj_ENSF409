package edu.ucalgary.oop;

import java.util.*;

public class Volunteer {

    private int volunteerID;
    private String name;
    private boolean isAvailable;
    private int maxConcurrentCalls;
    private int currentCalls;
    private String lastAvailableChange;
    private String phoneNumber;
    private List<VolunteerSpecialty> specialties;

    public Volunteer(String name, String phoneNumber, boolean isAvailable, int maxConcurrentCalls){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isAvailable = isAvailable;
        this.maxConcurrentCalls = maxConcurrentCalls;

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

    public String getLastAvailableChange(){
        return lastAvailableChange;
    }

    public void setAvailability(boolean isAvailable){
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
