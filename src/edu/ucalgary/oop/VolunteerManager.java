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
}
