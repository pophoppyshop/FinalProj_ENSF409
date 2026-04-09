package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;
import java.time.*;

public class CrisisCallsTest{
    //call information
    private int expectedCallID = 4;
    private String expectedStatus = "Pending";
	private int expectedUrgencyLevel = 1;
	private LocalTime expectedCallTime = LocalTime.now();
	private LocalDate expectedCallDate = LocalDate.now();
	private double expectedCallDuration = 0; // still pending
    private String expectedCallNotes = "null";   //null, still pending
    private int expectedIssueID = 1;
    private CrisisCall crisisCall;

    //caller information
    private int expectedID = 1;
    private String expectedPhoneNumber = "111-1111-1111";
    private boolean expectedIsAnonymous = true;
    private LocalDate expectedLastContact = LocalDate.of(2025,01,01);
    private String expectedCallerNotes = new StringBuilder("null");   //null, still pending
    private Caller expectedCaller;


    @Before
    public void setUp(){
        //create expectedCaller and crisisCall with expected values
        expectedCaller = new Caller(expectedID, expectedPhoneNumber, expectedIsAnonymous, expectedLastContact,
        expectedCallerNotes);
        crisisCall = new CrisisCall(expectedCallID, expectedIssueID, expectedStatus, expectedUrgencyLevel, expectedCallTime,
            expectedCallDate, expectedCallDuration, expectedCallNotes, expectedCaller);
    }

    @Test
    public void testGetStatus(){
        //confirm that getStatus() returns expected value
        assertEquals("Call should be given status", expectedStatus, 
            crisisCall.getStatus());
    }

    @Test
    public void testGetUrgencyLevel(){
        //confirm that getUrgencyLevel() returns expected value
        assertEquals("Call should be given urgency level", expectedUrgencyLevel, 
            crisisCall.getUrgencyLevel());
    }

    @Test
    public void testGetCallDuration(){
        //confirm that getCallDuration() returns expected value
        assertEquals("Call should be given duration", expectedCallDuration, 
            crisisCall.getCallDuration(), 0.1);
    }

    @Test
    public void testgetCaller(){
        //confirm that getCaller() returns expected value
        assertEquals("Call should have caller information", expectedCaller, 
            crisisCall.getCaller());
    }

    @Test
    public void testToString(){
        //test that toString matches expected
        String expectedString = "\nID: 4\n\tStatus: Pending\n\tUrgency: 1\n\tCall time: "
            +expectedCallTime+"\n\tCall date:"+LocalDate.now()+"\n\tDuration (mins): 0.0\n\tNotes: null"
            +"\nCaller information: \n" + expectedCaller.toString();
        String callString = crisisCall.toString();
        assertEquals("toString should return call information", expectedString, callString);
    }

}