package edu.ucalgary.oop;

import java.util.*;

public class ScheduleManager{
 public static List<CrisisCall> prioritize(List<CrisisCall> calls) {
        calls.sort((c1, c2) -> c2.getUrgencyLevel() - c1.getUrgencyLevel());
        return calls;
    }

    public static void assign(List<CrisisCall> calls, List<Volunteer> volunteers) {
    	// For every call
        for (CrisisCall call : calls) {
        	// For every volunteer
            for (Volunteer v : volunteers) {
            	// Assign if volunteer is available and workload is possible
                if (v.getAvailability() && v.getCurrentCalls() < v.getMaxConcurrentCalls()) {
                	
                    System.out.println("Assigning call " + call.getCallID() + " to " + v.getName());
                    v.setCurrentCalls(v.getCurrentCalls() + 1);
                    
                    break;
                }
            }
        }
    }

    public static void escalate(List<CrisisCall> calls) {
    	// Escalate the call if urgency is max
        for (CrisisCall call : calls) {
            if (call.getUrgencyLevel() == 5) {
                System.out.println("Escalating call ID: " + call.getCallID());
            }
        }
    }


}
