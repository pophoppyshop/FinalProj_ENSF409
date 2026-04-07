package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.*;
import java.time.*;

public class FilterByUrgencyStrategyTest {

    private List<CrisisCall> calls;
    private FilterByUrgencyStrategy strategy;

    // expected values
    private int expectedCount;
    private int testUrgency;

    // shared objects
    private Caller caller;
    private StringBuilder notes;

    @Before
    public void setUp() {

        // setup caller
        notes = new StringBuilder("Test notes");
        caller = new Caller("111-1111", true, "2026-04-05", notes);

        // setup calls
        calls = new ArrayList<>();

        calls.add(new CrisisCall(1, "PENDING", 3,
                LocalTime.now(), LocalDate.now(),
                10.0, "note1", caller));

        calls.add(new CrisisCall(2, "RESOLVED", 5,
                LocalTime.now(), LocalDate.now(),
                5.0, "note2", caller));

        calls.add(new CrisisCall(3, "PENDING", 3,
                LocalTime.now(), LocalDate.now(),
                8.0, "note3", caller));

        calls.add(new CrisisCall(4, "ESCALATED", 2,
                LocalTime.now(), LocalDate.now(),
                6.0, "note4", caller));

        // strategy setup
        testUrgency = 3;
        strategy = new FilterByUrgencyStrategy(testUrgency);

        expectedCount = 2;
    }

    @Test
    public void testMatchingUrgency() {
        List<CrisisCall> result = strategy.execute(calls);

        assertEquals("Should return calls with matching urgency",
                expectedCount, result.size());
    }

    @Test
    public void testNoMatches() {
        strategy = new FilterByUrgencyStrategy(10);

        List<CrisisCall> result = strategy.execute(calls);

        assertEquals("Should return empty list when no matches",
                0, result.size());
    }

    @Test
    public void testSingleMatch() {
        strategy = new FilterByUrgencyStrategy(5);

        List<CrisisCall> result = strategy.execute(calls);

        assertEquals("Should return one matching call",
                1, result.size());
    }

    @Test
    public void testEmptyList() {
        calls = new ArrayList<>();

        List<CrisisCall> result = strategy.execute(calls);

        assertEquals("Should return empty list for empty input",
                0, result.size());
    }
}
