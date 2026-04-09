package edu.ucalgary.oop;

import java.time.*;

public class Caller {
	private int callerID;
	private String phoneNumber;
	private boolean isAnonymous;
	private LocalDate lastContactDate;
	private String notes;
	
	public Caller(Caller caller) {
		callerID = caller.callerID;
		phoneNumber = caller.phoneNumber;
		isAnonymous = caller.isAnonymous;
		lastContactDate = caller.lastContactDate;
		notes = caller.notes;
	}
	
	public Caller(int callerID, String phoneNumber, boolean isAnonymous, LocalDate lastContactDate,
			String notes) {
		// Normalize phone number
		String phoneRegex = "^(\\d{3})[\\s.-]?(\\d{3})[\\s.-]?(\\d{4})$";
		String replacement = "$1-$2-$3";
		
		this.callerID = callerID;
		this.phoneNumber = phoneNumber.replaceAll(phoneRegex, replacement);
		this.isAnonymous = isAnonymous;
		this.lastContactDate = lastContactDate;
		this.notes = notes;
	}
	
	public int getCallerID() {
		return callerID;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public boolean getIsAnonymous() {
		return isAnonymous;
	}
	
	public LocalDate getLastContactDate () {
		return lastContactDate;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setLastContactDate(LocalDate lastContactDate) {
		this.lastContactDate = lastContactDate;
	}
	
	public String toString () {
		// Normalize isAnonymous output
		String anonymous = (isAnonymous)? "Yes" : "No";
		
		return 
				"\tPhone number: " + phoneNumber +
				"\n\tIs anonymous?: " + anonymous +
				"\n\tLast contact date: " + lastContactDate +
				"\n\tNotes: " + notes;
	}
}
