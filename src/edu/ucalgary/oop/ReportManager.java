package edu.ucalgary.oop;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReportManager {

    private List<CrisisCall> calls;

    public ReportManager(List<CrisisCall> calls) {
        this.calls = calls;
    }

    public List<CrisisCall> filterCallsByDate(LocalDate date) {
        List<CrisisCall> result = new ArrayList<>();

        for (CrisisCall call:calls) {
            if (call.getCallDate().equals(date)) {
                result.add(call);
            }
        }

        return result;
    }

    public Report generateDailyReport(LocalDate date) {
        List<CrisisCall> dailyCalls = filterCallsByDate(date);
        return new Report(date, dailyCalls);
    }


    public void exportReport(Report report) {
        String filename = "daily_log_" + report.getDate() + ".txt";
        try (FileWriter writer = new FileWriter("data/" + filename)) {
            writer.write(report.formatReport());
        } catch (IOException e) {
            System.out.println("Error writing report: " + e.getMessage());
        }
    }
}
