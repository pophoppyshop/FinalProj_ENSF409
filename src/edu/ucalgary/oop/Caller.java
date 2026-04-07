package edu.ucalgary.oop;

import java.time.*;

public class Caller {
	private int callerID;
	private String phoneNumber;
	private boolean isAnonymous;
	private LocalDate lastContactDate;
	private StringBuilder notes;
	
	public Caller(Caller caller) {
		callerID = caller.callerID;
		phoneNumber = caller.phoneNumber;
		isAnonymous = caller.isAnonymous;
		lastContactDate = caller.lastContactDate;
		notes = new StringBuilder(caller.notes);
	}
	
	public Caller(int callerID, String phoneNumber, boolean isAnonymous, LocalDate lastContactDate,
			StringBuilder notes) {
		this.callerID = callerID;
		this.phoneNumber = phoneNumber;
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
	
	public void setLastContactDate(LocalDate lastContactDate) {
		this.lastContactDate = lastContactDate;
	}
	
	public String toString () {
		// Normalize isAnonymous output
		String anonymous = (isAnonymous)? "Yes" : "No";
		
		return 
				"\tPhone number: " + phoneNumber +
				"\n\tIs anonymous?: " + anonymous +
				"\n\tLast contact date" + lastContactDate +
				"\n\tNotes: " + notes;
	}
}
