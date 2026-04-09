package edu.ucalgary.oop;

import java.util.*;

public class FilterBySpecialtyStrategy implements Strategy<List<Volunteer>, List<Volunteer>> {
	private String specialtyName = "";
	
	public FilterBySpecialtyStrategy (String specialty) {
		specialtyName = specialty;
	}

	@Override
	public List<Volunteer> execute(List<Volunteer> param) {
		// Results list
		List<Volunteer> result = new ArrayList<Volunteer>();
		
		// Check all specialties for each volunteer object
		for (Volunteer volunteer : param) {
			for (VolunteerSpecialty specialty : volunteer.getSpecialties()) {
				if (specialty.getSpecialtyName().equalsIgnoreCase(specialtyName)) {
					result.add(volunteer);
				}
			}
		}
		
		return result;
	}
	
}
