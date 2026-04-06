package edu.ucalgary.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.io.File;


class CrisisCall {
    private String status;
    private LocalDate callDate;

    public CrisisCall(String status, LocalDate callDate) {
        this.status = status;
        this.callDate = callDate;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getCallDate() {
        return callDate;
    }
}

public class ReportManagerTest {

    private List<CrisisCall> createSampleCalls() {
        List<CrisisCall> calls = new ArrayList<>();
        calls.add(new CrisisCall("PENDING", LocalDate.of(2026, 4, 5)));
        calls.add(new CrisisCall("RESOLVED", LocalDate.of(2026, 4, 5)));
        calls.add(new CrisisCall("ESCALATED", LocalDate.of(2026, 4, 4)));
        calls.add(new CrisisCall("PENDING", LocalDate.of(2026, 4, 5)));
        return calls;
    }

    @Test
    public void testFilterCallsByDate() {
        ReportManager manager = new ReportManager(createSampleCalls());

        List<CrisisCall> result = manager.filterCallsByDate(LocalDate.of(2026, 4, 5));

        assertEquals(3, result.size());
        for (CrisisCall call : result) {
            assertEquals(LocalDate.of(2026, 4, 5), call.getCallDate());
        }
    }

    @Test
    public void testGenerateDailyReport() {
        ReportManager manager = new ReportManager(createSampleCalls());

        Report report = manager.generateDailyReport(LocalDate.of(2026, 4, 5));

        assertEquals(3, report.getTotalCalls());
        assertEquals(2, report.getPendingCount());
        assertEquals(1, report.getResolvedCount());
        assertEquals(0, report.getEscalatedCount());
        assertEquals(LocalDate.of(2026, 4, 5), report.getDate());
    }

    @Test
    public void testExportReportRuns() {
        ReportManager manager = new ReportManager(createSampleCalls());
        Report report = manager.generateDailyReport(LocalDate.of(2026, 4, 5));
    
        manager.exportReport(report);
    
        assertTrue(true); // test passes if no error occurs
    }
}
