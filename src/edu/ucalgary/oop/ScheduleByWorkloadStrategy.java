package edu.ucalgary.oop;

import java.util.List;

public class ScheduleByWorkloadStrategy implements Strategy<List<CrisisCall>, Object>{
	private List<Volunteer> volunteers;
	
	public ScheduleByWorkloadStrategy(List<Volunteer> volunteers) {
		this.volunteers = volunteers;
	}

	@Override
	public Object execute(List<CrisisCall> calls) {
        // For every call
        for (CrisisCall call : calls) {
        	// For every volunteer
            for (Volunteer v : volunteers) {
            	// Assign if volunteer is available, workload is possible, call is not resolved, and volunteer has less than average
                if (v.getAvailability() && v.getCurrentCalls() < v.getMaxConcurrentCalls() && 
                		!call.getStatus().equalsIgnoreCase("RESOLVED") && v.getCurrentCalls() < getAverageCalls()) {
                	
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
	
	private int getAverageCalls() {
		// Get the average rounded to nearest whole number
		double sum = 0;
		
		for (Volunteer v:volunteers) {
			sum += v.getCurrentCalls();
		}
		
		return (int) Math.round(sum / volunteers.size());
	}
}
