package edu.ucalgary.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class Volunteer {
    private String name;
    private String phone;
    private boolean available;

    public Volunteer(String name, String phone, boolean available) {
        this.name = name;
        this.phone = phone;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phone;
    }

    public boolean getAvailability() {
        return available;
    }
}

public class VolunteerManagerTest {

    @Test
    public void testAddVolunteer() {
        // clear list since it's static
        VolunteerManager.getVolunteers().clear();

        Volunteer v = new Volunteer("Alice", "1234567890", true);
        VolunteerManager.addVolunteer(v);

        List<Volunteer> list = VolunteerManager.getVolunteers();

        assertEquals(1, list.size());
        assertEquals("Alice", list.get(0).getName());
    }

    @Test
    public void testGetVolunteers() {
        VolunteerManager.getVolunteers().clear();

        Volunteer v1 = new Volunteer("Bob", "111", true);
        Volunteer v2 = new Volunteer("Charlie", "222", false);

        VolunteerManager.addVolunteer(v1);
        VolunteerManager.addVolunteer(v2);

        List<Volunteer> list = VolunteerManager.getVolunteers();

        assertEquals(2, list.size());
    }

    @Test
    public void testVolunteerDataForPrint() {
        VolunteerManager.getVolunteers().clear();
    
        Volunteer v = new Volunteer("Dana", "333", true);
        VolunteerManager.addVolunteer(v);
    
        List<Volunteer> list = VolunteerManager.getVolunteers();
    
        assertEquals("Dana", list.get(0).getName());
        assertEquals("333", list.get(0).getPhoneNumber());
        assertTrue(list.get(0).getAvailability());
    }
}
