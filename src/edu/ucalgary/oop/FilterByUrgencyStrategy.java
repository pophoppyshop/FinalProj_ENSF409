package edu.ucalgary.oop;

import java.util.*;

public class FilterByUrgencyStrategy implements Strategy<List<CrisisCall>, List<CrisisCall>>{
	int urgency;
	
	public FilterByUrgencyStrategy(int urgency) {
		this.urgency = urgency;
	}
	
	public List<CrisisCall> execute(List<CrisisCall> calls) {
		List<CrisisCall> result = new ArrayList<>();
		
		// Check for urgency and add to results while preserving index
		for (CrisisCall call : calls) {
			if (call.getUrgencyLevel() == urgency) {
				result.add(call);
			}
		}
		
		return result;
	}
}
