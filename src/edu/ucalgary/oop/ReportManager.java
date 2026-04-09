package edu.ucalgary.oop;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReportManager {
    private ReportManager() {
    }

    public static List<CrisisCall> filterCallsByDate(LocalDate date) {
        List<CrisisCall> result = new ArrayList<>();
        List<CrisisCall> calls = CallManager.getCallList();

        // Add calls with same call date to results
        for (CrisisCall call:calls) {
            if (call.getCallDate().equals(date)) {
                result.add(call);
            }
        }

        return result;
    }

    public static Report generateDailyReport(LocalDate date) throws IllegalArgumentException{
    	// Return a new report filtered for the date
        List<CrisisCall> dailyCalls = filterCallsByDate(date);
        
        if (dailyCalls.size() == 0) {
        	throw new IllegalArgumentException("No calls on this date!");
        }
        
        return new Report(date, dailyCalls);
    }


    public static void exportReport(Report report) {
        String filename = "daily_log_" + report.getDate() + ".txt";
        
        // Attempt to write to the file
        try (FileWriter writer = new FileWriter("reports/" + filename)) {
            writer.write(report.formatReport());
            
            System.out.println("Report has been written to the file.");
        } catch (IOException e) {
            System.out.println("Error writing report: " + e.getMessage());
        }
    }
}
