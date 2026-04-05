package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;
import java.time.*;

public class CallerTest{

    private int expectedID = 1;
    private String expectedPhoneNumber = "111-1111-1111";
    private boolean expectedIsAnonymous = true;
    private String expectedLastContact = "null";
    private String expectedNotes = "null";   //null, still pending

    @Before
    public void setUp(){
        caller = new Caller(expectedID, expectedPhoneNumber, expectedIsAnonymous, expectedLastContact,
        expectedNotes);
    }

    @Test
    public void testGetCallerID(){
        assertEquals("Caller should be given unique ID at first call", expectedID, 
            caller.getCallerID());
    }

    @Test
    public void testGetPhoneNumber(){
            assertEquals("Phone number should be set with constructor", expectedPhoneNumber,
            caller.getPhoneNumber());
    }

    @Test
    public void testToString(){
        String expectedString = "\tPhone number: 111-1111-1111\n\tIs anonymous?: Yes\n\tLast contact date: null\n\tNotes: null";
        String callerString = caller.toString();
        assertEquals("toString should return caller information", expectedString, callerString);
    }

}