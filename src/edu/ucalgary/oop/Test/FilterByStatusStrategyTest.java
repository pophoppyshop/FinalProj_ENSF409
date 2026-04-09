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
    private String notes;
    

    @Before
    public void setUp() {

        // caller setup
        notes = "Test notes";
        caller = new Caller(4, "111-1111", true, date, notes);

        // create calls list
        calls = new ArrayList<>();

        calls.add(new CrisisCall(1, 1, "PENDING", 3,
                LocalTime.now(), LocalDate.now(),
                10.0, "note1", caller));

        calls.add(new CrisisCall(2, 1, "RESOLVED", 2,
                LocalTime.now(), LocalDate.now(),
                5.0, "note2", caller));

        calls.add(new CrisisCall(3, 1, "PENDING", 4,
                LocalTime.now(), LocalDate.now(),
                15.0, "note3", caller));

        strategy = new FilterByStatusStrategy("PENDING");

        expectedCount = 2;
    }

    @Test
    public void testFilterMatchingStatus() {
        //test that executing strategy returns expected count
        List<CrisisCall> result = strategy.execute(calls);

        assertEquals("should return only PENDING calls",
                expectedCount, result.size());
    }

    @Test
    public void testNoMatches() {
        //filter for a status that does not exist and confirm that no matches are made
        strategy = new FilterByStatusStrategy("ESCALATED");

        List<CrisisCall> result = strategy.execute(calls);

        assertEquals("should return empty list when no matches",
                0, result.size());
    }

    @Test
    public void testCaseInsensitive() {
        //test that filter is insensitice to case
        strategy = new FilterByStatusStrategy("pending");

        List<CrisisCall> result = strategy.execute(calls);

        assertEquals("should ignore case when filtering",
                expectedCount, result.size());
    }

    @Test
    public void testEmptyList() {
        //test that an empty list is returned when executing an empty list
        calls = new ArrayList<>();

        List<CrisisCall> result = strategy.execute(calls);

        assertEquals("should return empty list for empty input",
                0, result.size());
    }
}
