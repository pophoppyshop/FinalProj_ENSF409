package edu.ucalgary.oop;

import java.util.*;

public class ScheduleByUrgencyStrategy implements Strategy<List<CrisisCall>, Object> {
	List<Volunteer> volunteers;
	
	public ScheduleByUrgencyStrategy(List<Volunteer> volunteers) {
		this.volunteers = volunteers;
	}

	@Override
	public Object execute(List<CrisisCall> calls) {
		// Sort from greatest urgency to least
        calls.sort((c1, c2) -> c2.getUrgencyLevel() - c1.getUrgencyLevel());
        
        // For every call
        for (CrisisCall call : calls) {
        	// For every volunteer
            for (Volunteer v : volunteers) {
            	// Assign if volunteer is available and workload is possible and call is not resolved
                if (v.getAvailability() && v.getCurrentCalls() < v.getMaxConcurrentCalls() && 
                		!call.getStatus().equalsIgnoreCase("RESOLVED")) {
                	
                	// Check if specialty matches
                	if (!ScheduleManager.specialtyMatch(call, v)){
                		continue;
                	}
                	
                	if (call.getStatus().equalsIgnoreCase("PENDING")) {
                		// Update status if its pending
                		call.setStatus("Active");
                	}
                	
                	if (call.getCurrentVolunteer() != null) {
	            		// Subtract from previous volunteer
	            		call.getCurrentVolunteer().setCurrentCalls(v.getCurrentCalls() - 1);
                	}
            		
            		call.setCurrentVolunteer(v);
                	
                    System.out.println("Assigning call " + call.getCallID() + " to " + v.getName());
                    v.setCurrentCalls(v.getCurrentCalls() + 1);
                    
                    break;
                }
            }
        }
        
		return null;
	}

}
