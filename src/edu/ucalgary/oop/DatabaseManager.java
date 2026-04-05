package edu.ucalgary.oop;

import java.util.*;
import java.sql.*;

public class DatabaseManager {
	private static List<Observer> observers = new ArrayList<Observer>();
	private static Connection dbConnect;
	
	private DatabaseManager() {}
	
	public static void addObserver(Observer observer) {
		// Add observer to observer list
		observers.add(observer);
	}
	
	public static void removeObserver(Observer observer) {
		// Remove observer from observer list
		observers.remove(observer);
	}
	
	public static void updateObservers() {
		// TODO Get the information from database and update each observer with the information
		// Update all observers
		for (Observer observer : observers) {
			observer.update();
		}
	}
	
	public static void initializeConnection(String username, String password) throws SQLException{
		dbConnect = DriverManager.getConnection("jdbc:postgresql://localhost/pets", username, password);
	}
	
	public static Connection getConnection() {
		return dbConnect;
	}
}
