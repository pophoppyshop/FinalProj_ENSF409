package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

public class CrisisCallsTest{
    private String expectedStatus = "Pending";
	private int expectedUrgencyLevel = "General support";
	private LocalTime expectedCallTime = LocalTime.now();
	private LocalDate expectedCallDate = LocalDate.now();
	private double expectedCallDuration = 0; // still pending
    private String expectedNotes = "null";   //null, still pending
    private CrisisCall crisisCall;

    //caller information
    private int expectedID = 1;
    private String expectedPhoneNumber = "111-1111-1111";
    private boolean expectedIsAnonymous = true;
    private String expectedLastContact = "null";
    private Caller expectedCaller;

    @Before
    public void setUp(){
        expectedCaller = new Caller(expectedID, expectedPhoneNumber, expectedIsAnonymous, expectedLastContact,
        expectedNotes);
        crisisCall = new CrisisCall(expectedStatus, expectedUrgencyLevel, expectedCallTime,
            expectedCallDate, expectedCallDuration, expectedNotes, expectedCaller);
    }

    @Test
    public void testGetStatus(){
        //TO DO
    }

    @Test
    public void testGetUrgencyLevel(){
        //TO DO
    }

    @Test
    public void testGetCallDuration(){
        //TO DO
    }

    @Test
    public void testgetCaller(){
        //TO DO
    }

    @Test
    public void testToString(){
        //TO DO
    }

}