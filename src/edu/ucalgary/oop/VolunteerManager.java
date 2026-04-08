package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class VolunteerManager implements Observer{
	private static List<Volunteer> volunteers = new ArrayList<>();
	private static VolunteerManager instance = new VolunteerManager();
	
	private VolunteerManager() {
	}
	
	public static VolunteerManager getInstance() {
		return instance;
	}
	
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

	@Override
	public void update() {
		System.out.println("Updating volunteers from database..."); 
		
		// Clear list to update
        volunteers.clear();
        
        try { 
        	// Prepare statement to extract all volunteers
			Connection conn = DatabaseManager.getConnection(); 
			
			String sql = "SELECT v.*, vs.*, s.* " +
			"FROM Volunteers v " + "JOIN VolunteerSpecialties vs ON v.VolunteerID = vs.VolunteerID" + 
					"JOIN Specialties s ON s.SpecialtyID = vs.SpecialtyID";
			
			Statement statement = conn.createStatement(); 
			
			// Execute statement
			ResultSet rs = statement.executeQuery(sql); 
			
			// Keep extracting until no more volunteer objects
			while (rs.next()) { 
				// Create volunteer specialty object
				VolunteerSpecialty specialty = new VolunteerSpecialty(
						rs.getString("SpecialtyName"),
						rs.getString("Description"),
						rs.getDate("CertificationDate").toLocalDate());
				
				// Skip if the phone number is not in the right format
				if (!CallManager.isPhoneFormat(rs.getString("PhoneNumber"))) {
					System.out.println("Invalid phone number format for " + rs.getString("Name"));
					continue;
				}
				
				// Create volunteer object
				Volunteer volunteer = new Volunteer(
						rs.getString("Name"),
						rs.getString("PhoneNumber"),
						rs.getBoolean("IsAvailable"),
						rs.getInt("MaxConcurrentCalls"),
						rs.getTimestamp("LastAvailableChange").toLocalDateTime().toLocalDate(),
						rs.getInt("CurrentCalls"));
			}
			
        } catch (SQLException e) {
        	
        }
	}
	
	
}
