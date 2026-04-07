package edu.ucalgary.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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

public class FilterByStatusStrategyTest {

    @Test
    public void testFilterMatchingStatus() {
        List<CrisisCall> calls = new ArrayList<>();
        calls.add(new CrisisCall("PENDING"));
        calls.add(new CrisisCall("RESOLVED"));
        calls.add(new CrisisCall("PENDING"));

        FilterByStatusStrategy strategy = new FilterByStatusStrategy("PENDING");
        List<CrisisCall> result = strategy.execute(calls);

        assertEquals(2, result.size());
    }

    @Test
    public void testFilterNoMatches() {
        List<CrisisCall> calls = new ArrayList<>();
        calls.add(new CrisisCall("RESOLVED"));
        calls.add(new CrisisCall("ESCALATED"));

        FilterByStatusStrategy strategy = new FilterByStatusStrategy("PENDING");
        List<CrisisCall> result = strategy.execute(calls);

        assertEquals(0, result.size());
    }

    @Test
    public void testCaseInsensitiveMatching() {
        List<CrisisCall> calls = new ArrayList<>();
        calls.add(new CrisisCall("pending"));
        calls.add(new CrisisCall("PENDING"));

        FilterByStatusStrategy strategy = new FilterByStatusStrategy("PeNdInG");
        List<CrisisCall> result = strategy.execute(calls);

        assertEquals(2, result.size());
    }

    @Test
    public void testEmptyList() {
        List<CrisisCall> calls = new ArrayList<>();

        FilterByStatusStrategy strategy = new FilterByStatusStrategy("PENDING");
        List<CrisisCall> result = strategy.execute(calls);

        assertEquals(0, result.size());
    }
}
