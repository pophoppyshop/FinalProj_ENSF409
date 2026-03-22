package edu.ucalgary.oop;

import java.time.*;
import java.util.*;

public class Report {
	private List<CrisisCall> callList;
	private LocalDate date;
	
	public Report(LocalDate date, List<CrisisCall> callList) {
		this.date = date;
		this.callList = callList;
	}
	
	public String formatReport() {
		// TODO
		return "";
	}
	
	public List<CrisisCall> getPendingCalls() {
		// TODO
		return null;
	}
	
	public List<CrisisCall> getResolvedCalls() {
		// TODO
		return null;
	}
	
	public List<CrisisCall> getEscalatedCalls() {
		// TODO
		return null;
	}
	
	public int getTotalCalls() {
		return callList.size();
	}

	
	public int getResolvedCount() {
		// TODO
		return 0;
	}
	
	public int getEscalatedCount() {
		// TODO
		return 0;
	}
	
	public int getPendingCount() {
		// TODO
		return 0;
	}
}
