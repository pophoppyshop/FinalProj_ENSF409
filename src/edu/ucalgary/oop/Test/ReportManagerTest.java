package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

import java.time.*;
import java.util.*;

public class ReportManagerTest {

    private ReportManager manager;
    private List<CrisisCall> calls;

    // expected values
    private LocalDate testDate;
    private int expectedFilteredCount;
    private int expectedPending;
    private int expectedResolved;
    private int expectedEscalated;
    private LocalDate date = LocalDate.of(2026,04,06);

    // shared objects
    private Caller caller;
    private StringBuilder notes;

    @Before
    public void setUp() {

        // Setup caller
        notes = "Test notes";
        caller = new Caller(4, "111-1111", true, date, notes);

        // Setup date
        testDate = LocalDate.of(2026, 4, 5);

        // Setup calls
        calls = new ArrayList<>();

        calls.add(new CrisisCall(1, 1, "PENDING", 3,
                LocalTime.now(), testDate,
                10.0, "note1", caller));

        calls.add(new CrisisCall(2, 1, "RESOLVED", 2,
                LocalTime.now(), testDate,
                5.0, "note2", caller));

        calls.add(new CrisisCall(3, 1, "ESCALATED", 5,
                LocalTime.now(), LocalDate.of(2026, 4, 4),
                8.0, "note3", caller));

        calls.add(new CrisisCall(4, 1, "PENDING", 4,
                LocalTime.now(), testDate,
                12.0, "note4", caller));

        manager = new ReportManager(calls);

        // Expected values
        expectedFilteredCount = 3;
        expectedPending = 2;
        expectedResolved = 1;
        expectedEscalated = 0;
    }

    @Test
    public void testFilterCallsByDate() {
        //test that filtering expected calls returns expected count and that the returned calls match 
        //the date that they were filtered for
        List<CrisisCall> result = manager.filterCallsByDate(testDate);

        assertEquals("Filtered calls count should match",
                expectedFilteredCount, result.size());

        for (CrisisCall call : result) {
            assertEquals("All calls should match the filter date",
                    testDate, call.getCallDate());
        }
    }

    @Test
    public void testGenerateDailyReport() {
        //test that daily report matches required parameters
        Report report = manager.generateDailyReport(testDate);

        assertEquals("Total calls should match",
                expectedFilteredCount, report.getTotalCalls());

        assertEquals("Pending calls should match",
                expectedPending, report.getPendingCount());

        assertEquals("Resolved calls should match",
                expectedResolved, report.getResolvedCount());

        assertEquals("Escalated calls should match",
                expectedEscalated, report.getEscalatedCount());

        assertEquals("Report date should match",
                testDate, report.getDate());
    }

    @Test
    public void testExportReportRuns() {
        //test that exporting report succeeds
        Report report = manager.generateDailyReport(testDate);

        manager.exportReport(report);

        assertTrue("Export should run without crashing", true);
    }
}
