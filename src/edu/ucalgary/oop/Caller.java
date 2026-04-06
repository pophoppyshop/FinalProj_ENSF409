package edu.ucalgary.oop;

public class Caller {
	private int callerID;
	private String phoneNumber;
	private boolean isAnonymous;
	private String lastContactDate;
	private StringBuilder notes;
	
	public Caller(Caller caller) {
		callerID = caller.callerID;
		phoneNumber = caller.phoneNumber;
		isAnonymous = caller.isAnonymous;
		lastContactDate = caller.lastContactDate;
		notes = new StringBuilder(caller.notes);
	}
	
	public Caller(String phoneNumber, boolean isAnonymous, String lastContactDate,
			StringBuilder notes) {
		this.phoneNumber = phoneNumber;
		this.isAnonymous = isAnonymous;
		this.lastContactDate = lastContactDate;
		this.notes = notes;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setLastContactDate(String lastContactDate) {
		this.lastContactDate = lastContactDate;
	}
	
	public String toString () {
		String anonymous = (isAnonymous)? "Yes" : "No";
		
		return 
				"\tPhone number: " + phoneNumber +
				"\n\tIs anonymous?: " + anonymous +
				"\n\tLast contact date" + lastContactDate +
				"\n\tNotes: " + notes;
	}
}
