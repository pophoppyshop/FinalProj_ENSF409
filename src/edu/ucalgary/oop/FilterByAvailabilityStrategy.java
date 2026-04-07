package edu.ucalgary.oop;

import java.util.*;

public class FilterByAvailabilityStrategy implements Strategy<List<Volunteer>, List<Volunteer>> {
	boolean availability = false;
	
	public FilterByAvailabilityStrategy (boolean available) {
		availability = available;
	}

	@Override
	public List<Volunteer> execute(List<Volunteer> param) {
		// Results list
		List<Volunteer> result = new ArrayList<Volunteer>();
		
		// Check all specialties for each volunteer object
		for (Volunteer volunteer : param) {
			if (volunteer.getAvailability() == availability) {
				result.add(volunteer);
			}
		}
		
		return result;
	}
	
}
