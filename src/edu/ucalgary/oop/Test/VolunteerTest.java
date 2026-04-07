package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

public class VolunteerTest {
    //specialty information
    private String expectedSpecialtyName;
    private String expectedSpecialtyDescription;
    private LocalDate expectedCertificationDate;
    private VolunteerSpecialty expectedSpecialty;

    //volunteer info
    private int expectedVolunteerID;
    private String expectedName;
    private boolean expectedIsAvailable;
    private int expectedMaxConcurrentCalls;
    private int expectedCurrentCalls;
    private String expectedLastAvailableChange;
    private String expectedPhoneNumber;
    private Volunteer volunteer;


    @Before
    public void setUp(){
        expectedSpecialty = new VolunteerSpecialty(expectedSpecialtyName, expectedSpecialtyDescription, expectedCertificationDate);
        volunteer = new Volunteer(expectedName, expectedPhoneNumber, expectedIsAvailable,
                    expectedMaxConcurrentCalls);
    }

    @Test
    public void testGetVolunteerID(){
        assertEquals("Volunteer should have ID", expectedVolunteerID, volunteer.getVolunteerID());
    }

    @Test
    public void testGetName(){
        assertEquals("Volunteer should be assigned name", expectedName, volunteer.getName());
    }

    @Test
    public void testGetPhoneNumber(){
        assertEquals("Volunteer should have phone number", expectedPhoneNumber, volunteer.getPhoneNumber());
    }

    @Test
    public void testGetAvailability(){
        assertEquals("Volunteer should have availability status", expectedIsAvailable, volunteer.getAvaliability());
    }

    @Test
    public void testGetMaxConcurrentCalls(){
        assertEquals("Volunteer should be assigned max concurrent calls", expectedMaxConcurrentCalls, volunteer.getMaxConcurrentCalls());
    }

    @Test
    public void testGetCurrentCalls(){
        assertEquals("Volunteer should have a number of active calls", expectedCurrentCalls, volunteer.getCurrentCalls());
    }

    @Test
    public void testGetLastAvailableChange(){
        assertEquals("Volunteer should have last available change", expectedLastAvailableChange, volunteer.getLastAvailableChange());
    }

    @Test
    public void testAddSpecialty(){
        String expectedAddedSpecialty = "suicide prevention";
        assertEquals("Specialty should be added", expectedAddedSpecialty, volunteer.addSpecialty("suicide prevention"));
    }
}
