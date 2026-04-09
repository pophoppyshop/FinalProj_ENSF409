package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.*;
import java.time.*;

public class FilterByUrgencyStrategyTest {

    private List<CrisisCall> calls;
    private FilterByUrgencyStrategy strategy;
    private LocalDate date = LocalDate.of(2026,04,06);

    // expected values
    private int expectedCount;
    private int testUrgency;

    // shared objects
    private Caller caller;
    private String notes;

    @Before
    public void setUp() {

        // setup caller
        notes = "Test notes";
        caller = new Caller(4, "111-1111", true, date, notes);

        // setup calls
        calls = new ArrayList<>();

        calls.add(new CrisisCall(1, 1, "PENDING", 3,
                LocalTime.now(), LocalDate.now(),
                10.0, "note1", caller));

        calls.add(new CrisisCall(2, 1, "RESOLVED", 5,
                LocalTime.now(), LocalDate.now(),
                5.0, "note2", caller));

        calls.add(new CrisisCall(3, 1, "PENDING", 3,
                LocalTime.now(), LocalDate.now(),
                8.0, "note3", caller));

        calls.add(new CrisisCall(4, 1, "ESCALATED", 2,
                LocalTime.now(), LocalDate.now(),
                6.0, "note4", caller));

        // strategy setup
        testUrgency = 3;
        strategy = new FilterByUrgencyStrategy(testUrgency);

        expectedCount = 2;
    }

    @Test
    public void testMatchingUrgency() {
        //test that strategy returns calls with matching urgency
        List<CrisisCall> result = strategy.execute(calls);

        assertEquals("Should return calls with matching urgency",
                expectedCount, result.size());
    }

    @Test
    public void testNoMatches() {
        //test that filtering for an urgency that has not been added returns an empty list
        strategy = new FilterByUrgencyStrategy(10);

        List<CrisisCall> result = strategy.execute(calls);

        assertEquals("Should return empty list when no matches",
                0, result.size());
    }

    @Test
    public void testSingleMatch() {
        //test that filtering for one match returns a list with one object
        strategy = new FilterByUrgencyStrategy(5);

        List<CrisisCall> result = strategy.execute(calls);

        assertEquals("Should return one matching call",
                1, result.size());
    }

    @Test
    public void testEmptyList() {
        //test that empty list is returned with an empty input for execute
        calls = new ArrayList<>();

        List<CrisisCall> result = strategy.execute(calls);

        assertEquals("Should return empty list for empty input",
                0, result.size());
    }
}
