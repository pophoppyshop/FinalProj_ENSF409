package edu.ucalgary.oop;

import java.util.*;

public class FilterByStatusStrategy implements Strategy<List<CrisisCall>, List<CrisisCall>>{
	String status;
	
	public FilterByStatusStrategy(String status) {
		this.status = status;
	}
	
	public List<CrisisCall> execute(List<CrisisCall> calls) {
		List<CrisisCall> result = new ArrayList<>();
		
		// Check for urgency and add to results while preserving index
		for (CrisisCall call : calls) {
			if (call.getStatus().equalsIgnoreCase(status)) {
				result.add(call);
			}
		}
		
		return result;
	}
}
