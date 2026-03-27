package edu.ucalgary.oop;

public class Caller {
	private int callerID;
	private String phoneNumber;
	private boolean isAnonymous;
	private String lastContactDate;
	private StringBuilder notes;
	
	public Caller(int callerId, String phoneNumber, boolean isAnonymous, String lastContactDate,
			StringBuilder notes) {
		this.callerID = callerId;
		this.phoneNumber = phoneNumber;
		this.isAnonymous = isAnonymous;
		this.lastContactDate = lastContactDate;
		this.notes = notes;
	}
	
	public int getCallerId() {
		return callerID;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setLastContactDate(String lastContactDate) {
		this.lastContactDate = lastContactDate;
	}
}
