package edu.ucalgary.oop;

import java.util.*;

public class ScheduleManager{
	private ScheduleManager() {
	}
	
	public static void schedule(Strategy<List <CrisisCall>, Object> strategy) {
		strategy.execute(CallManager.getCallList());
	}

    public static void escalate(List<CrisisCall> calls) {
    	// Escalate the call if urgency is max
        for (CrisisCall call : calls) {
            if (call.getUrgencyLevel() == 5) {
                System.out.println("Escalating call ID: " + call.getCallID());
            }
        }
    }
    
	public static boolean specialtyMatch(CrisisCall call, Volunteer v) {
		List<VolunteerSpecialty> specialties = v.getSpecialties();

		// Match with preset specialties
		switch(call.getUrgencyLevel()) {
			case 1:
				return specialtyInVolunteer(specialties, "General Support");
			case 2:
				return specialtyInVolunteer(specialties, "Depression");
			case 3:
				return specialtyInVolunteer(specialties, "Substance Abuse");
			case 4:
				return specialtyInVolunteer(specialties, "Domestic Violence");
			case 5:
				return specialtyInVolunteer(specialties, "Suicide Risk");
		}
		
		return false;
	}
	
	public static boolean specialtyInVolunteer(List<VolunteerSpecialty> specialties, String specialtyName) {
		// Check if specialty name is in specialties
		for (VolunteerSpecialty specialty : specialties) {
			if (specialty.getSpecialtyName().equalsIgnoreCase(specialtyName)) {
				return true;
			}
		}
		
		return false;
	}
}
