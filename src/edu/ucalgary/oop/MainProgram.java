package edu.ucalgary.oop;

import java.util.List;
import java.util.Scanner;
import java.time.*;
import java.util.ArrayList;


public class MainProgram {
	private static Scanner scanner;
	
	public static void main(String[] args) {
		scanner = new Scanner(System.in);

		// Ask for login information until a database connection is made
		while (true) {
			System.out.println("Please enter the username for the database: ");
			
			String username = scanner.nextLine();
			
			System.out.println("Please enter the password for the database: ");
			
			String password = scanner.nextLine();
			
			// Try connecting to the database
			if (DatabaseManager.initializeConnection(username, password)) {
				break; // stop loop if connection successful
			}
		}
		
		DatabaseManager.updateObservers();
		
		// Main loop
		mainLoop:
		while (true) {
			System.out.println("\n----- Welcome to the Calgary Crisis Connect Management System! -----");
			System.out.println("Please select an option or '0' to quit:\n"
					+ "(1) Manage calls\n"
					+ "(2) Manage volunteers\n"
					+ "(3) Manage Schedules\n"
					+ "(4) Generate Report");
			
			String userInput = scanner.nextLine();
			DatabaseManager.countAction();
			
			// Check input option
			switch (userInput) {
				case "1":
					manageCall();
					break;
				
				case "2":
					manageVolunteer();
					break;
					
				case "3":
					// TODO scheduling
					scheduling();
					break;
					
				case "4":
					generateReport();
					break;
					
				// Quits program
				case "0":
					break mainLoop;
					
				default:
					System.out.println("Invalid option! Please try again.");
			}
		}
		
		System.out.println(DatabaseManager.getConnection());
		
		scanner.close();
		DatabaseManager.disconnect();
	}
	
	public static void manageCall() {
		callLoop:
		while (true) {
			System.out.println("\n----- Call Manager -----");
			System.out.println("Please select an option or '0' to go back:\n"
					+ "(1) View calls \n"
					+ "(2) Add new calls\n"
					+ "(3) Modify call details\n"
					+ "(4) Update status");
			
			String userInput = scanner.nextLine();
			
			DatabaseManager.countAction();
			
			// Check input option
			switch (userInput) {
				case "1":
					// Get filter option
					System.out.println("\nEnter 'u' to filter by urgency level, 's' to filter by status, or 'a' to view all calls.\n"
							+ "Enter anything else to go back:");
					userInput = scanner.nextLine();
					userInput = userInput.toLowerCase();	// normalize input
					
					DatabaseManager.countAction();
					
					switch (userInput) {
						case "u":{
							// Show urgency levels
							System.out.println(
									"\n" +
									"5 - Suicide Risk\n" +
									"4 - Domestic Violence\n" +
									"3 - Substance Abuse\n" +
									"2 - Depression\n" +
									"1 - General Support\n" +
									"From the above, select a number to filter by the respective urgency level:");
							userInput = scanner.nextLine();
							
							DatabaseManager.countAction();
							
							// Filter by urgency strategy and print each call info
							int urgency = Integer.parseInt(userInput);
							List<CrisisCall> results = CallManager.filter(new FilterByUrgencyStrategy(urgency));
							
							CallManager.printCallList(results);
							
							break;}
						case "s": {
							// Show statuses
							System.out.println("\n" +
									"Calls are either 'pending', 'active', 'resolved', or 'escalated'. "
									+ "Please enter a valid status to filter by:");
							userInput = scanner.nextLine();
							
							DatabaseManager.countAction();
							
							// Filter by status and print each call info
							List<CrisisCall> results = CallManager.filter(new FilterByStatusStrategy(userInput));
									
							CallManager.printCallList(results);
							
							break;}
						case "a": {
							// Print all calls
	                        List<CrisisCall> calls = CallManager.getCallList();
	                        CallManager.printCallList(calls);
	                        
							break;}
						
						default:
							System.out.println("Invalid option! Please try again.");
					}
					
					break;
				
				case "2": {
					// Get caller info
                    System.out.println("\nEnter caller phone number (XXX-XXX-XXXX):");
                    String phone = scanner.nextLine();
                    
                    DatabaseManager.countAction();
                    
                    // Check if phone format is correct
                    if (!CallManager.isPhoneFormat(phone)) {
                    	System.out.println("Invalid phone format!");
                    	
                    	break;
                    }
                    
                    System.out.println("Is the caller anonymous? (yes or no):");
					String input = scanner.nextLine().toLowerCase();
					boolean isAnonymous = input.equals("true") || input.equals("yes");
					
					DatabaseManager.countAction();

					// Create new caller object
                    Caller caller = new Caller (
                    	CallManager.generateUniqueCallerID(),	
                        phone,
                        isAnonymous,
                        LocalDate.now(),
                        "N/A"
                    );
                    
                    // Get call info
					System.out.println("Select issue type:");
					System.out.println("1 - Suicide Risk");
					System.out.println("2 - Domestic Violence");
					System.out.println("3 - Mental Health Crisis");
					System.out.println("4 - Substance Abuse");
					System.out.println("5 - General Support");
					
					int issueID = Integer.parseInt(scanner.nextLine());

                    System.out.println("Enter urgency level (1-5):");
                    int urgency = Integer.parseInt(scanner.nextLine());
                    
                    DatabaseManager.countAction();
                    
                    System.out.println("Enter notes:");
                    String notes = scanner.nextLine();
                    
                    DatabaseManager.countAction();

                    // Create new call object
                    CrisisCall newCall = new CrisisCall(
                    	CallManager.generateUniqueCallID(),
						issueID,
                        "Pending",
                        urgency,
                        LocalTime.now(),
                        LocalDate.now(),
                        0.0,
                        notes,
                        caller
                    );
                    
                    CallManager.addCall(newCall);
                    
                    System.out.println("Call added successfully.");
                    
					break;}
					
				case "3":{
					// TODO Modify call details (triggers rescheduling if urgency updates)
					System.out.println("\nEnter call ID:");
                    int ID = Integer.parseInt(scanner.nextLine());
                    
                    DatabaseManager.countAction();
                    
                    CrisisCall newCall;
                    
                    try {
	                    // Get call object
						newCall = new CrisisCall(CallManager.getCall(ID));
                    } catch (IllegalArgumentException | IllegalStateException e) {
                    	//Return to menu
                    	e.printStackTrace();
                    	break;
                    }

                    // Get updated information
					System.out.println("Enter new urgency level (1-5):");
					int newUrgency = Integer.parseInt(scanner.nextLine());
					
					DatabaseManager.countAction();

					System.out.println("Enter new notes:");
					String newNotes = scanner.nextLine();
					
					DatabaseManager.countAction();
					
					System.out.println("Enter updated call duration (mins):");
					double callDuration = Double.parseDouble(scanner.nextLine());
					
					DatabaseManager.countAction();

					// Update urgency and notes 
					newCall.setUrgencyLevel(newUrgency);
					newCall.setNotes(newNotes);
					newCall.setCallDuration(callDuration);
					
					CallManager.modifyCallDetails(newCall);
                    
                    break;}
					
				case "4":{
					// Find the call object
                    System.out.println("\nEnter call ID:");
                    int ID = Integer.parseInt(scanner.nextLine());
                    
                    DatabaseManager.countAction();
                    
                    // Get updated status
                    System.out.println("Enter new status (Pending/Active/Resolved/Escalated)");
                    String status = scanner.nextLine();
                    
                    DatabaseManager.countAction();
                    
                    // Updating
                    try {
                    	CallManager.updateStatus(CallManager.getCall(ID), status);
                    } catch (IllegalArgumentException | IllegalStateException e) {
                    	// Return to menu
                    	e.printStackTrace();
                    	break;
                    }
                    
					System.out.println("Status updated successfully.");
					break;}
					
				case "0":
					break callLoop;
					
				default:
					System.out.println("Invalid option! Please try again.");
			}
		}
	}
	
	public static void manageVolunteer() {
		volunteerLoop:
		while (true) {
			System.out.println("\n----- Volunteer Manager -----");
			System.out.println("Please select an option or '0' to go back:\n"
					+ "(1) View counselors \n"
					+ "(2) Modify availability");
			
			String userInput = scanner.nextLine();
			
			DatabaseManager.countAction();
			
			// Check input option
			switch (userInput) {
				case "1":	
					// Get filter option
					System.out.println("\nEnter 's' to filter by specialty, 'a' to filter by availability, "
							+ "or 'v' to view all volunteers.\n"
							+ "Enter anything else to go back:");
					userInput = scanner.nextLine();
					userInput = userInput.toLowerCase();	// normalize input
					
					DatabaseManager.countAction();
					
					switch (userInput) {
						// Filter by specialty
						case "s":{
							System.out.println("Enter specialty name:");
							String special = scanner.nextLine();
							
							DatabaseManager.countAction();
							
							// Use filter strategy
							List<Volunteer> results = VolunteerManager.filter(new FilterBySpecialtyStrategy(special));
							VolunteerManager.printVolunteers(results);
							
							break;}
						// Filter by availability
						case "a":{
							System.out.println("Enter availability (Yes|No):");
							String input = scanner.nextLine();
							
							DatabaseManager.countAction();
							
							boolean availability = input.equalsIgnoreCase("True") || input.equalsIgnoreCase("Yes");
							
							// Use filter strategy
							List<Volunteer> results = VolunteerManager.filter(new FilterByAvailabilityStrategy(availability));
							VolunteerManager.printVolunteers(results);
							
							break;}
						// Show all volunteers
						case "v":
							VolunteerManager.printVolunteers(VolunteerManager.getVolunteers());
							
							break;
							
						default:
							System.out.println("Invalid option! Please try again.");
					}
					
					break;
					
				case "2":
					// Get index
					System.out.println("Enter volunteer index:");
					int index = Integer.parseInt(scanner.nextLine());
					
					DatabaseManager.countAction();
					
					// Update availability
					System.out.println("Is volunteer available? (yes/no):");
					String input = scanner.nextLine();
					boolean newAvailability = input.equalsIgnoreCase("True") || input.equalsIgnoreCase("Yes");
					
					DatabaseManager.countAction();
					
					try {
						VolunteerManager.getVolunteer(index).setAvailability(newAvailability);
						System.out.println("Availability updated.");
					} catch (IllegalArgumentException | IllegalStateException e) {
						e.printStackTrace();
						
						return;
					}

					break;
					
				case "0":
					break volunteerLoop;
					
				default:
					System.out.println("Invalid option! Please try again.");
			}
		}
	}
	
	public static void scheduling() {
		scheduleLoop:
		while (true) {
			System.out.println("\n----- Schedule Manager -----");
			System.out.println("Please select an option or '0' to go back:\n" +
					"(1) Assign calls based on urgency\n" +
					"(2) Assign smallest available counselor workload");
			
			String userInput = scanner.nextLine();
			
			DatabaseManager.countAction();
			
			switch (userInput) {
				case "1":{
					// Assign calls based on highest urgency
					ScheduleManager.schedule(new ScheduleByUrgencyStrategy(VolunteerManager.getVolunteers()));
					break;}
					
				case "2":{
					// Assign calls based on smaller available workload
					ScheduleManager.schedule(new ScheduleByWorkloadStrategy(VolunteerManager.getVolunteers()));
					break;}
				
				case "0":
					break scheduleLoop;
					
				default:
					System.out.println("Invalid option! Please try again.");
			}
		}
	}
	
	public static void generateReport(){
		// Generate the report
		 Report currentReport = ReportManager.generateDailyReport(LocalDate.now());
		
		// Display report
	    System.out.println("\n----- Daily Report (" + LocalDate.now() + ") -----");
	    System.out.println(currentReport.formatReport());
	    
	    // Write to file
	    ReportManager.exportReport(currentReport);
	}
}
