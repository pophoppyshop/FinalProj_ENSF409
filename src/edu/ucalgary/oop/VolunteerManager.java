package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.List;

public class VolunteerManager{
	private static List<Volunteer> volunteers = new ArrayList<>();
	
	public static List<Volunteer> getVolunteers(){
		return volunteers;
	}
	
	public static void addVolunteer(Volunteer v){
		volunteers.add(v);
	}
	
	public static Volunteer getVolunteer(int id) throws IllegalArgumentException, IllegalStateException{
		// Throw exception if empty call list
		if (volunteers.size() == 0) {
			throw new IllegalStateException("Volunteer list is empty!");
		}
		
		// Return call if ID matches
		for (Volunteer volunteer: volunteers) {
			if (volunteer.getVolunteerID() == id) {
				return volunteer;
			}
		}
		
		throw new IllegalArgumentException("Call ID does not exist in call list.");
	}
	
	public static void printVolunteers(List<Volunteer> list){
		// Indicate empty volunteer list
		if (list.size() == 0) {
			System.out.println("No volunteers in list");
			return;
		}
		
		// Display volunteer information
		for (Volunteer v : list){
			System.out.println(v);
			System.out.println("--------------------------");
		}
	}
	
	public static List<Volunteer> filter(Strategy<List <Volunteer>, List <Volunteer>> strategy) {
		return strategy.execute(volunteers);
	}
}
