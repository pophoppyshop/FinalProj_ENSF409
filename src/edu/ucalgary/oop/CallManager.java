package edu.ucalgary.oop;

import java.util.*;
import java.sql.*;
import java.time.*;

public class CallManager implements Observer{
	private static List <CrisisCall> callList =  new ArrayList<>();
	private static CallManager instance = new CallManager();
	private static final String[] ALL_STATUSES = {"Pending", "Active", "Resolved", "Escalated"};
	
	private CallManager() {
	}
	
	public static CallManager getInstance() {
		return instance;
	}
	
	public static void addCall(CrisisCall call) {
		callList.add(call);
	}
	
	public static boolean isPhoneFormat(String phoneNumber) {
		// Compare phone number input with regex
		String phoneRegex = "^(\\d{3})[\\s.-_,]*(\\d{3})[\\s.-_,]*(\\d{4})$";
		
		return phoneNumber.matches(phoneRegex);
	}
	
	public static void modifyCallDetails(CrisisCall newCall) {
		CrisisCall oldCall;
		
		// Last check for any exceptions
		try {
			oldCall = CallManager.getCall(newCall.getCallID());
		} catch (IllegalArgumentException | IllegalStateException e) {
			e.printStackTrace();
			
			System.out.println("Call details were not updated.");
			
			return;
		}
		
		if (oldCall.getUrgencyLevel() != newCall.getUrgencyLevel()) {
			// TODO: If urgency level changes, trigger rescheduling using ScheduleManager
		}
		
		// Replace old call with new call
		callList.set(callList.indexOf(oldCall), newCall);
		
		System.out.println("Call details successfully updated.");
		// TODO: update database
	}
	
	public static void updateStatus(CrisisCall call, String status) throws IllegalArgumentException{
		// Set status only if it's valid
		for (String validStatus : ALL_STATUSES) {
			if (validStatus.equalsIgnoreCase(status)) {
				call.setStatus(validStatus);
			}
		}
		
		throw new IllegalArgumentException("Invalid status!");
	}
	
	public static List<CrisisCall> getCallList() {
		return callList;
	}

	public static List<CrisisCall> getCallList(LocalDate date) {
		List<CrisisCall> results = new ArrayList<CrisisCall>();
		
		// Returns a list of crisis calls based on given date
		for (CrisisCall call : callList) {
			if (call.getCallDate().isEqual(date)) {
				results.add(call);
			}
		}
		
		return results;
	}
	
	public static List<CrisisCall> filter(Strategy<List <CrisisCall>, List <CrisisCall>> strategy) {
		return strategy.execute(callList);
	}
	
	public static void printCallList(List<CrisisCall> calls) {
		// Return if no calls can be printed
		if (calls.size() == 0) {
			System.out.println("No calls found!");
			return;
		}
		
		// Display the call details with indices
		for (CrisisCall call : calls) {
			System.out.println(call);
			System.out.println("--------------------------"); // separation line
		}
	}
	
	public static int generateUniqueCallID() {
		int id = 1;
		
		while (true) {
			// Check if id already exists in callList
			for (CrisisCall call : callList) {
				if (call.getCallID() == id) {
					// Continue to next index
					id++;
					
					continue;
				}
			}
			
			return id;
		}
	}
	
	public static int generateUniqueCallerID() {
		int id = 1;
		
		while (true) {
			// Check if id already exists in callList
			for (CrisisCall call : callList) {
				if (call.getCaller().getCallerID() == id) {
					id++;
					
					continue;
				}
			}
			
			return id;
		}
	}
	
	public static CrisisCall getCall(int id) throws IllegalArgumentException, IllegalStateException{
		// Throw exception if empty call list
		if (callList.size() == 0) {
			throw new IllegalStateException("Call list is empty!");
		}
		
		// Return call if ID matches
		for (CrisisCall call: callList) {
			if (call.getCallID() == id) {
				return call;
			}
		}
		
		throw new IllegalArgumentException("Call ID does not exist in call list.");
	}

	@Override
	public void update() { 
		System.out.println("Updating calls from database..."); 
		// Clear list to update
        callList.clear(); 
        
        try { 
        	// Prepare statement to extract all calls and callers
			Connection conn = DatabaseManager.getConnection(); 
			
			String sql = "SELECT c.*, ca.CallerID, ca.PhoneNumber, ca.IsAnonymous, ca.LastContactDate, ca.Notes AS CallerNotes " +
			"FROM CrisisCalls c " + "JOIN Callers ca ON c.CallerID = ca.CallerID";
			
			Statement statement = conn.createStatement(); 
			
			// Execute statement
			ResultSet rs = statement.executeQuery(sql); 
			
			// Keep extracting until no more call objects
			while (rs.next()) { 
				// Get last contact date of caller
			    LocalDate lastContactDate = rs.getDate("LastContactDate").toLocalDate();
			    
			    // Create Caller 
			    Caller caller = new Caller( 
				    rs.getInt("CallerID"), 
				    rs.getString("PhoneNumber"), 
				    rs.getBoolean("IsAnonymous"), 
				    lastContactDate, 
				    new StringBuilder(rs.getString("CallerNotes"))
				); 
			    
				// Timestamp to date + time 
				Timestamp ts = rs.getTimestamp("CallTime"); 
				
				// Convert SQL Interval to Duration
				Duration interval = (Duration) rs.getObject("CallDuration");
			
				// Create call
				CrisisCall call = new CrisisCall(
					rs.getInt("CallID"),
					rs.getString("Status"), 
					rs.getInt("UrgencyLevel"), 
					ts.toLocalDateTime().toLocalTime(), 
					ts.toLocalDateTime().toLocalDate(), 
					interval.toMinutes(), 
					rs.getString("Notes"), 
					caller
				); 
				
		        callList.add(call); 
		        
		    } 
			
			// Close statements
		    rs.close();
		    statement.close();
		}
		catch (SQLException e){ 
		    System.out.println("Error updating calls: " +  e.getMessage()); 
		} 
	} 
}
