package edu.ucalgary.oop;

import java.time.*;

public class CrisisCall{
	private int callID;
	private String status;
	private int issueID;
	private int urgencyLevel;
	private LocalTime callTime;
	private LocalDate callDate;
	private double callDuration; // in minutes
	private String notes;
	private Caller caller;
	private Volunteer currentVolunteer;
	
	public CrisisCall(CrisisCall call) {
		callID = call.callID;
		issueID = call.issueID;
		status = call.status;
		urgencyLevel = call.urgencyLevel;
		callTime = call.callTime;
		callDate = call.callDate;
		callDuration = call.callDuration;
		notes = call.notes;
		caller = new Caller(call.getCaller());
		currentVolunteer = call.currentVolunteer;
	}
	
	public CrisisCall(int callID, int issueID, String status, int urgencyLevel, LocalTime callTime, LocalDate callDate, 
			double callDuration, String notes, Caller caller) {
		this.callID = callID;
		this.issueID = issueID;
		this.status = status;
		this.urgencyLevel = urgencyLevel;
		this.callTime = callTime;
		this.callDate = callDate;
		this.callDuration = callDuration;
		this.notes = notes;
		this.caller = caller;
		currentVolunteer = null;
	}
	
	public Volunteer getCurrentVolunteer() {
		return currentVolunteer;
	}
	
	public int getCallID() {
		return callID;
	}
	
	public int getIssueID() {
    return issueID;
	}
	
	public String getStatus() {
		return status;
	}
	
	public int getUrgencyLevel() {
		return urgencyLevel;
	}
	
	public LocalTime getCallTime() {
		return callTime;
	}
	
	public LocalDate getCallDate() {
		return callDate;
	}
	
	public double getCallDuration() {
		return callDuration;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public Caller getCaller() {
		return caller;
	}
	
	public void setCurrentVolunteer(Volunteer newVolunteer) {
		currentVolunteer = newVolunteer;
	}
	
	public void setCallID(int callID) {
		this.callID = callID;
	}
	
	public void setIssueID(int issueID) {
    this.issueID = issueID;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setUrgencyLevel(int urgencyLevel) {
		this.urgencyLevel = urgencyLevel;
	}
	
	public void setCallDuration(double callDuration) {
		this.callDuration = callDuration;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public String toString() {
		return 
				"\nID: " + callID + 
				"\n\tStatus: " + status + 
				"\n\tUrgency: " + urgencyLevel +
				"\n\tCall time: " + callTime + 
				"\n\tCall date:" + callDate + 
				"\n\tDuration (mins): " + callDuration +
				"\n\tNotes: " + notes +
				"\nCaller information: \n" + caller.toString();
	}
}
