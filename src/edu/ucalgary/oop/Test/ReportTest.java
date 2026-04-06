package edu.ucalgary.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

class CrisisCall {
    private String status;

    public CrisisCall(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

public class ReportTest {

    private Report createSampleReport() {
        List<CrisisCall> calls = new ArrayList<>();
        calls.add(new CrisisCall("PENDING"));
        calls.add(new CrisisCall("RESOLVED"));
        calls.add(new CrisisCall("ESCALATED"));
        calls.add(new CrisisCall("PENDING"));
        calls.add(new CrisisCall("RESOLVED"));

        return new Report(LocalDate.of(2026, 4, 5), calls);
    }

    @Test
    public void testTotalCalls() {
        Report report = createSampleReport();
        assertEquals(5, report.getTotalCalls());
    }

    @Test
    public void testPendingCalls() {
        Report report = createSampleReport();
        assertEquals(2, report.getPendingCount());
        assertEquals(2, report.getPendingCalls().size());
    }

    @Test
    public void testResolvedCalls() {
        Report report = createSampleReport();
        assertEquals(2, report.getResolvedCount());
        assertEquals(2, report.getResolvedCalls().size());
    }

    @Test
    public void testEscalatedCalls() {
        Report report = createSampleReport();
        assertEquals(1, report.getEscalatedCount());
        assertEquals(1, report.getEscalatedCalls().size());
    }

    @Test
    public void testDate() {
        Report report = createSampleReport();
        assertEquals(LocalDate.of(2026, 4, 5), report.getDate());
    }

    @Test
    public void testFormatReport() {
        Report report = createSampleReport();
        String output = report.formatReport();

        assertTrue(output.contains("Report Date: 2026-04-05"));
        assertTrue(output.contains("Total Calls: 5"));
        assertTrue(output.contains("Resolved Calls: 2"));
        assertTrue(output.contains("Escalated Calls: 1"));
        assertTrue(output.contains("Pending Calls: 2"));
    }
}
