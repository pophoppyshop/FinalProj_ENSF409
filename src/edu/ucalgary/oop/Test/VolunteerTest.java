package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

import java.time.LocalDate;

public class VolunteerTest {
    //specialty information
    private String expectedSpecialtyName = "trauma counseling";
    private String expectedSpecialtyDescription = "expertise with counseling individuals experiencing trauma";
    private LocalDate expectedCertificationDate = LocalDate.now();
    private VolunteerSpecialty expectedSpecialty;

    //volunteer info
    
    private int expectedVolunteerID = 0;
    private String expectedName = "Susan Jones";
    private boolean expectedIsAvailable = true;
    private int expectedMaxConcurrentCalls = 4;
    private int expectedCurrentCalls = 0;
    private LocalDate expectedLastAvailableChange = LocalDate.of(2026,04,05);
    private String expectedPhoneNumber = "2222222";
    private Volunteer volunteer;


    @Before
    public void setUp(){
        //set up volunteer and volunteerspecialty with expected values
        expectedSpecialty = new VolunteerSpecialty(expectedSpecialtyName, expectedSpecialtyDescription, expectedCertificationDate);
        volunteer = new Volunteer(expectedVolunteerID, expectedName, expectedPhoneNumber, expectedIsAvailable, expectedMaxConcurrentCalls, expectedLastAvailableChange, expectedCurrentCalls);

    }

    @Test
    public void testGetVolunteerID(){
        //test that getVolunteerID() returns expected value
        assertEquals("Volunteer should have ID", expectedVolunteerID, volunteer.getVolunteerID());
    }

    @Test
    public void testGetName(){
        //test that getName() returns expected value
        assertEquals("Volunteer should be assigned name", expectedName, volunteer.getName());
    }

    @Test
    public void testGetPhoneNumber(){
        //test that getPhoneNumber() returns expected value
        assertEquals("Volunteer should have phone number", expectedPhoneNumber, volunteer.getPhoneNumber());
    }

    @Test
    public void testGetAvailability(){
        //test that getAvailability() returns expected value
        assertEquals("Volunteer should have availability status", expectedIsAvailable, volunteer.getAvailability());
    }

    @Test
    public void testGetMaxConcurrentCalls(){
        //test that getMaxConcurrentCalls() returns expected value
        assertEquals("Volunteer should be assigned max concurrent calls", expectedMaxConcurrentCalls, volunteer.getMaxConcurrentCalls());
    }

    @Test
    public void testGetCurrentCalls(){
        //test that getCurrentCalls() returns expected value
        assertEquals("Volunteer should have a number of active calls", expectedCurrentCalls, volunteer.getCurrentCalls());
    }

    @Test
    public void testGetLastAvailableChange(){
        //test that getLastAvailableChange() returns expected value
        assertEquals("Volunteer should have last available change", expectedLastAvailableChange, volunteer.getLastAvailableChange());
    }

    @Test
    public void testAddSpecialty(){
        //test that attempting to add a specialty to a volunteer successfully adds
        String expectedAddedSpecialty = "suicide prevention";
        volunteer.addSpecialty(expectedSpecialty);
        assertEquals("Specialty should be added", expectedAddedSpecialty, volunteer.getSpecialties());
    }
}
