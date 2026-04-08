package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.*;
import java.time.*;

public class ScheduleManagerTest {

    private List<CrisisCall> calls;
    private List<Volunteer> volunteers;

    private LocalDate date = LocalDate.of(2026,04,05);

    // expected values
    private int highestUrgency;
    private int secondUrgency;
    private int thirdUrgency;

    private int expectedAssignedCalls;
    private int expectedMaxCalls;

    // shared objects
    private Caller caller;
    private StringBuilder notes;

    private Volunteer v1;
    private Volunteer v2;

    @Before
    public void setUp() {

        // setup caller
        notes = new StringBuilder("Test notes");
        caller = new Caller(4,"111-1111", true, date, notes);

        // setup calls
        calls = new ArrayList<>();

        calls.add(new CrisisCall(1, "PENDING", 2,
                LocalTime.now(), LocalDate.now(),
                10.0, "note1", caller));

        calls.add(new CrisisCall(2, "PENDING", 5,
                LocalTime.now(), LocalDate.now(),
                5.0, "note2", caller));

        calls.add(new CrisisCall(3, "PENDING", 3,
                LocalTime.now(), LocalDate.now(),
                8.0, "note3", caller));

        // setup volunteers
        volunteers = new ArrayList<>();

        v1 = new Volunteer("Alice", "123", true, 2);
        v1.setCurrentCalls(0);

        v2 = new Volunteer("Bob", "456", true, 2);
        v2.setCurrentCalls(2); // already at max

        volunteers.add(v1);
        volunteers.add(v2);

        // expected values
        highestUrgency = 5;
        secondUrgency = 3;
        thirdUrgency = 2;

        expectedAssignedCalls = 1;
        expectedMaxCalls = 2;
    }

    @Test
    public void testPrioritize() {
        ScheduleManager.prioritize(calls);

        assertEquals("Highest urgency should be first",
                highestUrgency, calls.get(0).getUrgencyLevel());

        assertEquals("Second highest urgency should be second",
                secondUrgency, calls.get(1).getUrgencyLevel());

        assertEquals("Lowest urgency should be last",
                thirdUrgency, calls.get(2).getUrgencyLevel());
    }

    @Test
    public void testAssignUpdatesVolunteerCalls() {
        // use first call for clarity
        List<CrisisCall> singleCall = new ArrayList<>();
        singleCall.add(calls.get(0));

        ScheduleManager.assign(singleCall, volunteers);

        assertEquals("Volunteer should receive one call",
                expectedAssignedCalls, v1.getCurrentCalls());
    }

    @Test
    public void testAssignRespectsMaxCalls() {
        // Only use second volunteer (already at max)
        List<Volunteer> singleVolunteer = new ArrayList<>();
        singleVolunteer.add(v2);

        List<CrisisCall> singleCall = new ArrayList<>();
        singleCall.add(calls.get(0));

        ScheduleManager.assign(singleCall, singleVolunteer);

        assertEquals("Volunteer at max should not receive more calls",
                expectedMaxCalls, v2.getCurrentCalls());
    }

    @Test
    public void testEscalateLogic() {
        int count = 0;

        for (CrisisCall c : calls) {
            if (c.getUrgencyLevel() == 5) {
                count++;
            }
        }

        assertEquals("There should be one call with urgency level 5",
                1, count);
    }
}
