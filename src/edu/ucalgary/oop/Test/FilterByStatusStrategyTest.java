package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
import java.time.*;

public class FilterByStatusStrategyTest {

    private List<CrisisCall> calls;
    private FilterByStatusStrategy strategy;

    // expected values
    private int expectedCount;
    private LocalDate date = LocalDate.of(2026,04,06);

    // shared test data
    private Caller caller;
    private StringBuilder notes;
    

    @Before
    public void setUp() {

        // caller setup
        notes = new StringBuilder("Test notes");
        caller = new Caller(4, "111-1111", true, date, notes);

        // create calls list
        calls = new ArrayList<>();

        calls.add(new CrisisCall(1, "PENDING", 3,
                LocalTime.now(), LocalDate.now(),
                10.0, "note1", caller));

        calls.add(new CrisisCall(2, "RESOLVED", 2,
                LocalTime.now(), LocalDate.now(),
                5.0, "note2", caller));

        calls.add(new CrisisCall(3, "PENDING", 4,
                LocalTime.now(), LocalDate.now(),
                15.0, "note3", caller));

        strategy = new FilterByStatusStrategy("PENDING");

        expectedCount = 2;
    }

    @Test
    public void testFilterMatchingStatus() {
        List<CrisisCall> result = strategy.execute(calls);

        assertEquals("should return only PENDING calls",
                expectedCount, result.size());
    }

    @Test
    public void testNoMatches() {
        strategy = new FilterByStatusStrategy("ESCALATED");

        List<CrisisCall> result = strategy.execute(calls);

        assertEquals("should return empty list when no matches",
                0, result.size());
    }

    @Test
    public void testCaseInsensitive() {
        strategy = new FilterByStatusStrategy("pending");

        List<CrisisCall> result = strategy.execute(calls);

        assertEquals("should ignore case when filtering",
                expectedCount, result.size());
    }

    @Test
    public void testEmptyList() {
        calls = new ArrayList<>();

        List<CrisisCall> result = strategy.execute(calls);

        assertEquals("should return empty list for empty input",
                0, result.size());
    }
}
