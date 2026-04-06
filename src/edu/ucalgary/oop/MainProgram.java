package edu.ucalgary.oop;

import java.sql.*;
import java.util.List;
import java.util.Scanner;


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
			
			// Check input option
			switch (userInput) {
				case "1":
					// TODO Call manager
					manageCall();
					
					break;
				
				case "2":
					// TODO volunteer manager
					break;
					
				case "3":
					// TODO scheduling
					break;
					
				case "4":
					// TODO report management
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
			
			// Check input option
			switch (userInput) {
				case "1":
					// Get filter option
					System.out.println("\nEnter 'u' to filter by urgency level, 's' to filter by status, or 'a' to view all calls.\n"
							+ "Enter anything else to go back:");
					userInput = scanner.nextLine();
					userInput = userInput.toLowerCase();	// normalize input
					
					switch (userInput) {
						case "u":{
							// Show urgency levels
							System.out.println(
									"\n" +
									"5 - Suicide risk\n" +
									"4 - Domestic Violence\n" +
									"3 - Substance Abuse\n" +
									"2 - Depression\n" +
									"1 - General Support\n" +
									"From the above, select a number to filter by the respective urgency level:");
							userInput = scanner.nextLine();
							
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
							
							// Filter by status and print each call info
							List<CrisisCall> results = CallManager.filter(new FilterByStatusStrategy(userInput));
									
							CallManager.printCallList(results);
							
							break;}
						case "a": {
							// Print all calls
	                        List<CrisisCall> calls = CallManager.getCallList();
	                        
	                        if (calls.size() == 0) {
	                            System.out.println("No calls available.");
	                        }
	                        else{
	                            CallManager.printCallList(calls);
	                        }
	                        
							break;}
					}
					
					break;
				
				case "2": {
					// Get caller info
                    System.out.println("\nEnter caller phone number:");
                    String phone = scanner.nextLine();
                    
                    System.out.println("Is the caller anonymous? (yes or no):");
					String input = scanner.nextLine().toLowerCase();
					boolean isAnonymous = input.equals("true") || input.equals("yes");

                    Caller caller = new Caller (
                        phone,
                        isAnonymous,
                        java.time.LocalDate.now().toString(),
                        new StringBuilder("N/A")
                    );
                    
                    // Get call info
                    System.out.println("Enter urgency level (1-5):");
                    int urgency = Integer.parseInt(scanner.nextLine());
                    
                    System.out.println("Enter notes:");
                    String notes = scanner.nextLine();

                    // Create new call object
                    CrisisCall newCall = new CrisisCall(
                    	CallManager.generateUniqueID(),
                        "Pending",
                        urgency,
                        java.time.LocalTime.now(),
                        java.time.LocalDate.now(),
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
                    List<CrisisCall> calls = CallManager.getCallList();

                    if (calls.size() == 0){
                        System.out.println("No calls available.");
                        break;
                    }
                    if (ID < 0 || ID > calls.size()){
                        System.out.println("Invalid index.");
                        break;
                    }
                    
                    // Get call object
					CrisisCall newCall = new CrisisCall(CallManager.getCall(ID));

					System.out.println("Enter new urgency level (1-5):");
					int newUrgency = Integer.parseInt(scanner.nextLine());

					System.out.println("Enter new notes:");
					String newNotes = scanner.nextLine();

					// Update urgency and notes 
					newCall.setUrgencyLevel(newUrgency);
					newCall.setNotes(newNotes);
					
					CallManager.modifyCallDetails(newCall);
                    
                    break;}
					
				case "4":{
					// Find the call object
                    System.out.println("\nEnter call ID:");
                    int ID = Integer.parseInt(scanner.nextLine());
                    
                    List<CrisisCall >calls = CallManager.getCallList();

                    if (calls.size() == 0){
                        System.out.println("No calls available.");
                        break;
                    }
                    if (ID < 0 || ID > calls.size()){
                        System.out.println("Invalid index.");
                        break;
                    }
                    
                    // Get updated status
                    System.out.println("Enter new status (Pending/Active/Resolved/Escalated)");
                    String status = scanner.nextLine();
                    
                    // Updating
					CallManager.updateStatus(CallManager.getCall(ID), status);
					System.out.println("Status updated successfully.");
					break;}
					
				case "0":
					break callLoop;
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
			
			// Check input option
			switch (userInput) {
				case "1":	
					// Get filter option
					System.out.println("\nEnter 's' to filter by specialty, 'a' to filter by availability, "
							+ "or 'v' to view all volunteers.\n"
							+ "Enter anything else to go back:");
					userInput = scanner.nextLine();
					userInput = userInput.toLowerCase();	// normalize input
					
					// TODO volunteer filtering
					switch (userInput) {
						case "s":
							break;
						case "a":
							break;
						case "v":
							break;
					}
					
					break;
					
				case "2":
					// TODO modify availability (also triggers call reassignment)
					break;
					
				case "0":
					break volunteerLoop;
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
			
			switch (userInput) {
				case "1":
					// TODO execute the schedule by urgency strategy
					break;
					
				case "2":
					// TODO execute the schedule by workload and specialty strategy
					break;
				case "0":
					break scheduleLoop;
			}
		}
	}
	
	public static void manageReport() {
		// TODO automatically generate a day report when its 11:59pm

		reportLoop:
		while (true) {
			System.out.println("\n----- Report Manager -----");
			System.out.println("A day report will be automatically generated at 11:59 pm. "
					+ "You can create a current day report or view past reports here.");
			System.out.println("Please select an option or '0' to go back:\n" +
					"(1) Create a current day report\n" + 
					"(2) View past reports");
			
			String userInput = scanner.nextLine();
			
			switch(userInput) {
				case "1":
					// TODO create current day report and display it
					break;
					
				case "2":{
					// TODO display past reports
					System.out.println("\nPlease enter a date in the valid format (YYYY-MM-DD):");
					userInput = scanner.nextLine();
					
					break;}
				case "0":
					break reportLoop;
			}
		}
		
	}
}
