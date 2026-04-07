package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;
import java.time.*;

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
        assertEquals("Call should be given status", expectedStatus, 
            crisisCall.getStatus());
    }

    @Test
    public void testGetUrgencyLevel(){
        assertEquals("Call should be given urgency level", expectedUrgencyLevel, 
            crisisCall.getUrgencyLevel());
    }

    @Test
    public void testGetCallDuration(){
        assertEquals("Call should be given duration", expectedDuration, 
            crisisCall.getCallDuration());
    }

    @Test
    public void testgetCaller(){
        assertEquals("Call should have caller information", expectedCaller, 
            crisisCall.getCaller());
    }

    @Test
    public void testToString(){
        String expectedString = "\tStatus: Pending\n\tUrgency: General support\n\tCall time: "
            +LocalTime.now()+"\n\tCall date: "+LocalDate.now()+"\n\tDuration (mins): 0\n\tNotes: null"
            +"\nCaller information: \n" + expectedCaller.toString();
        String callString = crisiCall.toString();
        assertEquals("toString should return call information", expectedString, crisisCallString);
    }

}