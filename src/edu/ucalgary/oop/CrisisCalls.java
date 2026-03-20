package ucalgary.edu.oop

public class CrisisCall{
	private String status;
	private int urgencyLevel;
	private String callTime;
	private double callDuration;
	private String notes;
	private Caller caller;
	
	public CrisisCall(String status, int urgencyLevel, String callTime, double callDuration, String notes, Caller caller) {
		this.status = status;
		this.urgencyLevel = urgencyLevel;
		this.callTime = callTime;
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
	
	public String getCallTime() {
		return callTime;
	}
	
	public double getCallDuration() {
		return callDuration;
	}
	
	public String getNotes() {
		return callDuration;
	}
	
	public Caller getCaller() {
		return caller;
	}
	
	public setStatus(String status) {
		this.status = status;
	}
	
	public setUrgencyLevel(int urgencyLevel) {
		this.urgencyLevel = urgencyLevel;
	}
	
	public setCallTime(int callTime) {
		this.callTime = callTime;
	}
	
	public setCallDuration(double callDuration) {
		this.callDuration = callDuration;
	}
	
	public setNotes(String notes) {
		this.notes = notes;
	}
	
	public setCaller(Caller caller) {
		this.caller = caller;
	}
	
}
