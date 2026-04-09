package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.*;
import java.time.LocalDate;

public class VolunteerManagerTest {

    private List<Volunteer> volunteers;

    // test volunteers
    private Volunteer v1;
    private Volunteer v2;
    private Volunteer v3;

    // expected values
    private int expectedSize;
    private String expectedName;
    private String expectedPhone;
    private boolean expectedAvailability;

    @Before
    public void setUp() {

        // clear static list before each test
        VolunteerManager.getVolunteers().clear();

        // create volunteers
        v1 = new Volunteer(1, "Alice", "1234567890", true, 2, LocalDate.of(2026,04,05), 1);
        v2 = new Volunteer(2, "Bob", "1111111111", false, 3, LocalDate.of(2026,04,05), 1);
        v3 = new Volunteer(3, "Phil", "77777", true, 2, LocalDate.of(2026,04,05), 1);

        // default expectations
        expectedSize = 1;
        expectedName = "Alice";
        expectedPhone = "1234567890";
        expectedAvailability = true;
    }

    @Test
    public void testAddVolunteer() {
        VolunteerManager.addVolunteer(v1);

        volunteers = VolunteerManager.getVolunteers();

        assertEquals("Volunteer list size should match",
                expectedSize, volunteers.size());

        assertEquals("Volunteer name should match",
                expectedName, volunteers.get(0).getName());
    }

    @Test
    public void testGetVolunteers() {
        VolunteerManager.addVolunteer(v1);
        VolunteerManager.addVolunteer(v2);

        volunteers = VolunteerManager.getVolunteers();

        assertEquals("Volunteer list should contain two entries",
                2, volunteers.size());
    }

    @Test
    public void testGetVolunteerException() throws Exception{
        VolunteerManager.addVolunteer(v1);
        VolunteerManager.addVolunteer(v2);

        assertEquals("Exception for non-existant Call ID", v3, VolunteerManager.getVolunteer(3));
    }

    @Test
    public void testVolunteerDataForPrint() {
        VolunteerManager.addVolunteer(v1);

        volunteers = VolunteerManager.getVolunteers();

        assertEquals("Name should match",
                expectedName, volunteers.get(0).getName());

        assertEquals("Phone number should match",
                expectedPhone, volunteers.get(0).getPhoneNumber());

        assertEquals("Availability should match",
                expectedAvailability, volunteers.get(0).getAvailability());
    }
}
