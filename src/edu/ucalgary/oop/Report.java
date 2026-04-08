package edu.ucalgary.oop;

import java.time.LocalDate;

import java.util.List;
import java.util.ArrayList;

public class Report {

    private List<CrisisCall> callList;
    private LocalDate date;

    // Constructor
    public Report(LocalDate date, List<CrisisCall> callList) {
        this.date = date;
        this.callList = new ArrayList<>(callList);
    }

    public String formatReport() {
        StringBuilder sb = new StringBuilder();

        // Add the types of calls and report date to the string builder
        sb.append("Report Date: ").append(date).append("\n");
        sb.append("Total Calls: ").append(getTotalCalls()).append("\n");
        sb.append("Resolved Calls: ").append(getResolvedCount()).append("\n");
        sb.append("Escalated Calls: ").append(getEscalatedCount()).append("\n");
        sb.append("Pending Calls: ").append(getPendingCount()).append("\n");

        return sb.toString();
    }

    public List<CrisisCall> getPendingCalls() {
        List<CrisisCall> result = new ArrayList<>();
        
        // Add call object if it is pending
        for (CrisisCall call : callList) {
            if (call.getStatus().equalsIgnoreCase("PENDING")) {
                result.add(call);
            }
        }
        return result;
    }

    public List<CrisisCall> getResolvedCalls() {
        List<CrisisCall> result = new ArrayList<>();
        
        // Add call object if it is resolved
        for (CrisisCall call : callList) {
            if (call.getStatus().equalsIgnoreCase("RESOLVED")) {
                result.add(call);
            }
        }
        return result;
    }

    public List<CrisisCall> getEscalatedCalls() {
        List<CrisisCall> result = new ArrayList<>();
        
        // Add call object if it is escalated
        for (CrisisCall call : callList) {
            if (call.getStatus().equalsIgnoreCase("ESCALATED")) {
                result.add(call);
            }
        }
        return result;
    }
    
    public LocalDate getDate() {
    	return date;
    }

    public int getTotalCalls() {
        return callList.size();
    }

    public int getResolvedCount() {
        return getResolvedCalls().size();
    }

    public int getEscalatedCount() {
        return getEscalatedCalls().size();
    }

    public int getPendingCount() {
        return getPendingCalls().size();
    }
}
