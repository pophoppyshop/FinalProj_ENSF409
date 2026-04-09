package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

import java.time.*;
import java.util.*;

public class ReportTest {

    private Report report;
    private List<CrisisCall> calls;

    // expected values
    private int expectedTotal;
    private int expectedPending;
    private int expectedResolved;
    private int expectedEscalated;
    private LocalDate expectedDate;
    private LocalDate date = LocalDate.of(2026,04,06);

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
                LocalTime.now(), LocalDate.of(2026, 4, 5),
                10.0, "note1", caller));

        calls.add(new CrisisCall(2, 1, "RESOLVED", 2,
                LocalTime.now(), LocalDate.of(2026, 4, 5),
                5.0, "note2", caller));

        calls.add(new CrisisCall(3, 1, "ESCALATED", 5,
                LocalTime.now(), LocalDate.of(2026, 4, 5),
                8.0, "note3", caller));

        calls.add(new CrisisCall(4, 1, "PENDING", 4,
                LocalTime.now(), LocalDate.of(2026, 4, 5),
                12.0, "note4", caller));

        calls.add(new CrisisCall(5, 1, "RESOLVED", 1,
                LocalTime.now(), LocalDate.of(2026, 4, 5),
                6.0, "note5", caller));

        expectedDate = LocalDate.of(2026, 4, 5);

        report = new Report(expectedDate, calls);

        expectedTotal = 5;
        expectedPending = 2;
        expectedResolved = 2;
        expectedEscalated = 1;
    }

    @Test
    public void testTotalCalls() {
        //test that getTotalCalls returns expected values
        assertEquals("total calls should match",
                expectedTotal, report.getTotalCalls());
    }

    @Test
    public void testPendingCalls() {
        //test that getPendingCount() and getPendingCalls() return expected counts and sizes
        assertEquals("pending count should match",
                expectedPending, report.getPendingCount());

        assertEquals("pending list size should match",
                expectedPending, report.getPendingCalls().size());
    }

    @Test
    public void testResolvedCalls() {
        //test that getResolvedCount() and getResolvedCalls return expected counts
        assertEquals("resolved count should match",
                expectedResolved, report.getResolvedCount());

        assertEquals("resolved list size should match",
                expectedResolved, report.getResolvedCalls().size());
    }

    @Test
    public void testEscalatedCalls() {
        //test that getEscalatedCount() and getEscalatedCalls() return expected counts
        assertEquals("escalated count should match",
                expectedEscalated, report.getEscalatedCount());

        assertEquals("escalated list size should match",
                expectedEscalated, report.getEscalatedCalls().size());
    }

    @Test
    public void testDate() {
        //test that getDate() returns expected value
        assertEquals("report date should match",
                expectedDate, report.getDate());
    }

    @Test
    public void testFormatReport() {
        //test that formatReport() returns correct format
        String output = report.formatReport();

        assertTrue("should contain correct date",
                output.contains("Report Date: 2026-04-05"));

        assertTrue("should contain total calls",
                output.contains("Total Calls: 5"));

        assertTrue("should contain resolved calls",
                output.contains("Resolved Calls: 2"));

        assertTrue("should contain escalated calls",
                output.contains("Escalated Calls: 1"));

        assertTrue("should contain pending calls",
                output.contains("Pending Calls: 2"));
    }
}
