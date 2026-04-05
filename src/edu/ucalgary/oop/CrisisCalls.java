package edu.ucalgary.oop;

import java.time.*;

public class CrisisCall{
	private String status;
	private int urgencyLevel;
	private LocalTime callTime;
	private LocalDate callDate;
	private double callDuration; // in minutes
	private String notes;
	private Caller caller;
	
	public CrisisCall(String status, int urgencyLevel, LocalTime callTime, LocalDate callDate, 
			double callDuration, String notes, Caller caller) {

		this.status = status;
		this.urgencyLevel = urgencyLevel;
		this.callTime = callTime;
		this.callDate = callDate;
		this.callDuration = callDuration;
		this.notes = notes;
		this.caller = caller;
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
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setUrgencyLevel(int urgencyLevel) {
		this.urgencyLevel = urgencyLevel;
	}
	
	public void setCallTime(LocalTime callTime) {
		this.callTime = callTime;
	}
	
	public void setCallDate(LocalDate callDate) {
		this.callDate = callDate;
	}
	
	public void setCallDuration(double callDuration) {
		this.callDuration = callDuration;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public void setCaller(Caller caller) {
		this.caller = caller;
	}
	
	public String toString() {
		return 
				"\tStatus: " + status + 
				"\n\tUrgency: " + urgencyLevel +
				"\n\tCall time: " + callTime + 
				"\n\tCall date:" + callDate + 
				"\n\tDuration (mins): " + callDuration +
				"\n\tNotes: " + notes +
				"\nCaller information: \n" + caller.toString();
	}
}
