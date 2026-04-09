package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDateTime;

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
		
		throw new IllegalArgumentException("Volunteer ID does not exist.");
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

	public static void updateAvailability(int volunteerID, boolean availability) {
		try {
			// Get connection and prepare statement
			Connection conn = DatabaseManager.getConnection();
			String sql = "UPDATE Volunteers SET IsAvailable=?, LastAvailableChange=? WHERE VolunteerID=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			// Update the database
			stmt.setBoolean(1, availability);
			stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
			stmt.setInt(3, volunteerID);

			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e){
			System.out.println("Error updating availability: " + e.getMessage());
		}
	}
	public static void addVolunteer(Volunteer v) {
		volunteers.add(v);
		try {
			// Get connection and statement
			Connection conn = DatabaseManager.getConnection();
			String sql = "INSERT INTO Volunteers (Name, PhoneNumber, IsAvailable, MaxConcurrentCalls, LastAvailableChange, CurrentCalls) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);

			// Add volunteer to database
			stmt.setString(1, v.getName());
			stmt.setString(2, v.getPhoneNumber());
			stmt.setBoolean(3, v.getAvailability());
			stmt.setInt(4, v.getMaxConcurrentCalls());
			stmt.setTimestamp(5, Timestamp.valueOf(v.getLastAvailableChange().atStartOfDay()));
			stmt.setInt(6, v.getCurrentCalls());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e){
			System.out.println("Error adding volunteer: " + e.getMessage());
		}
	}
	@Override
	public void update() {
		System.out.println("Updating volunteers from database..."); 
		
		// Clear list to update
        volunteers.clear();
        
        try { 
        	// Prepare statement to extract all volunteers
			Connection conn = DatabaseManager.getConnection(); 
			
			String sql = "SELECT v.*, vs.CertificationDate, s.SpecialtyName, s.Description " +
			"FROM Volunteers v " + "JOIN VolunteerSpecialties vs ON v.VolunteerID = vs.VolunteerID " + 
			"JOIN Specialties s ON s.SpecialtyID = vs.SpecialtyID";
			
			Statement statement = conn.createStatement(); 
			
			// Execute statement
			ResultSet rs = statement.executeQuery(sql); 

			Map<Integer, Volunteer> volunteerMap = new HashMap<>();
			
			// Keep extracting until no more volunteer objects
			while (rs.next()) { 
				int volunteerID = rs.getInt("VolunteerID");
				Volunteer volunteer = volunteerMap.get(volunteerID);

				// Add new volunteer object into map
				if (volunteer == null){
					// Skip if the phone number is not in the right format
					String phoneNumber = rs.getString("PhoneNumber");
					
					if (phoneNumber != null && !CallManager.isPhoneFormat(phoneNumber)) {
						System.out.println("Invalid phone number (" + phoneNumber + ") format for " + rs.getString("Name"));
						continue;
					}
					
					Timestamp time = rs.getTimestamp("LastAvailableChange");
					
					// Create volunteer object
					volunteer = new Volunteer(
							volunteerID,
							rs.getString("Name"),
							(phoneNumber == null) ? "" : phoneNumber,
							rs.getBoolean("IsAvailable"),
							rs.getInt("MaxConcurrentCalls"),
							(time == null) ? null : time.toLocalDateTime().toLocalDate(),
							rs.getInt("CurrentCalls"));
					
					volunteerMap.put(volunteerID, volunteer);
				}
				
				if (volunteer != null){
					// Create volunteer specialty object
					VolunteerSpecialty specialty = new VolunteerSpecialty(
							rs.getString("SpecialtyName"),
							rs.getString("Description"),
							(rs.getDate("CertificationDate") == null) ? null : rs.getDate("CertificationDate").toLocalDate());
					
					volunteer.addSpecialty(specialty);
				} 
			}
			
			// Convert to arrayList
			volunteers = new ArrayList<>(volunteerMap.values());
			rs.close();
			statement.close();
		} catch (SQLException e) {
        	System.out.println("Error updating volunteer: " + e.getMessage());
        }
	}
	
	
}
