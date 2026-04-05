package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

public class CrisisCallsTest{
    private String expectedStatus = "Pending";
	private int expectedUrgencyLevel = "General support";
	private LocalTime expectedCallTime = LocalTime.now();
	private LocalDate expectedCallDate = LocalDate.now();
	private double expectedCallDuration = 0; // still pending
}