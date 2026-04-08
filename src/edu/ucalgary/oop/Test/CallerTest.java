package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;
import java.time.*;

public class CallerTest{

    private int expectedID;
    private String expectedPhoneNumber;
    private boolean expectedIsAnonymous;
    private LocalDate expectedLastContact;
    private StringBuilder expectedNotes;   //null, still pending

    private Caller caller;

    @Before
    public void setUp(){

        caller = new Caller(1, "111-1111-1111", true, LocalDate.now(), expectedNotes);

        expectedID = 1;
        expectedPhoneNumber = "111-1111-1111";
        expectedIsAnonymous = true;
        expectedLastContact = LocalDate.now();
        expectedNotes = new StringBuilder("null");
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