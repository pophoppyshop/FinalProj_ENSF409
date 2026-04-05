package edu.ucalgary.oop;

import java.util.List;
import java.util.ArrayList;

public class CallManager{
	private static List <CrisisCall> callList =  new ArrayList<>();
	
	private CallManager() {
	}
	
	public static void addCall(CrisisCall call) {
		callList.add(call);
	}
	
	public static void modifyCallDetails(CrisisCall call) {
		// still need to implement
	}
	
	public static void updateStatus(CrisisCall call, String status) {
		call.setStatus(status);
	}
	
	public static List<CrisisCall> getCallList() {
		return callList;
	}
	
	public static List<CrisisCall> filter(Strategy<List <CrisisCall>, List <CrisisCall>> strategy) {
		return strategy.execute(callList);
	}
	
	public static void printCallList(List<CrisisCall> calls) {
		// Display the call details with indices
		for (CrisisCall call : calls) {
			System.out.println(call.toString());
			System.out.println("--------------------------"); // separation line
		}
	}
	
	public static int generateUniqueID() {
		int id = 1;
		
		while (true) {
			// Check if index already exists in callList
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
	
	public static CrisisCall getCall(int index) throws IllegalArgumentException{
		for (CrisisCall call: callList) {
			if (call.getCallID() == index) {
				return call;
			}
		}
		
		throw new IllegalArgumentException("Call ID does not exist.");
	}
}
