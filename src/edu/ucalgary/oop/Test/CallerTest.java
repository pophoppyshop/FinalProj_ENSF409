package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;
import java.time.*;

public class CallerTest{

    private int expectedID;
    private String expectedPhoneNumber;
    private boolean expectedIsAnonymous;
    private LocalDate expectedLastContact;
    private String expectedNotes;   //null, still pending

    private Caller caller;

    @Before
    public void setUp(){

        caller = new Caller(1, "111-1111-1111", true, LocalDate.now(), expectedNotes);
        //set up expected values to test files with
        expectedID = 1;
        expectedPhoneNumber = "111-1111-1111";
        expectedIsAnonymous = true;
        expectedLastContact = LocalDate.now();
        expectedNotes = "null";
    }

    @Test
    public void testGetCallerID(){
        //confirm that GetCallerID() returns expected value
        assertEquals("Caller should be given unique ID at first call", expectedID, 
            caller.getCallerID());
    }

    @Test
    public void testGetPhoneNumber(){
        //confirm that GetPhoneNumber() returns expected value
        assertEquals("Phone number should be set with constructor", expectedPhoneNumber,
            caller.getPhoneNumber());
    }

    @Test
    public void testToString(){
        //confirm that output strings match
        String expectedString = "\tPhone number: 111-1111-1111\n\tIs anonymous?: Yes\n\tLast contact date2026-04-08\n\tNotes: null";
        String callerString = caller.toString();
        assertEquals("toString should return caller information", expectedString, callerString);
    }

}