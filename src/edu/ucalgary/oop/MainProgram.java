package edu.ucalgary.oop;

import java.sql.*;
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
			try {
				DatabaseManager.initializeConnection(username, password);
			} catch (SQLException e) {
				e.printStackTrace();
				
				System.out.println("Invalid username or password!");
				
				continue;
			}
			break;
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
		
		System.out.println(DatabaseManager.getInstance());
		System.out.println(DatabaseManager.getConnection());
		
		scanner.close();
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
					System.out.println("\nEnter 'u' to filter by urgency level or 's' to filter by status. "
							+ "Enter anything else to go back:");
					userInput = scanner.nextLine();
					userInput = userInput.toLowerCase();	// normalize input
					
					// Show urgency levels
					if (userInput.equals("u")) {
						System.out.println(
								"\n" +
								"1 - Low urgency (Stress, loneliness, other general support, etc)\n" +
								"2 - Medium urgency (Depression, increased substance use, eating disorders, etc)" +
								"3 - High urgency (Suicidal ideation/self-harm, domestic violence, sexual violence, etc\n" +
								"From the above, select a number to filter by the respective urgency level:");
						userInput = scanner.nextLine();
						
						// TODO list calls based on urgency level
					}
					// Show statuses
					else if(userInput.equals("s")) {
						System.out.println("\n" +
								"Calls are either 'pending', 'active', 'resolved', or 'escalated'. "
								+ "Please enter a valid status to filter by:");
						userInput = scanner.nextLine();
						userInput = userInput.toLowerCase();	// normalize input
						
						// TODO list calls based on status
					}
					
					break;
				
				case "2":
					// TODO Add new call
					break;
					
				case "3":
					// TODO Modify call details (triggers rescheduling if urgency updates)
					break;
					
				case "4":
					// TODO Update call status
					break;
					
				case "0":
					break callLoop;
			}
		}
	}
}
