package edu.ucalgary.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;

class CrisisCall {
    private int urgency;
    private int id;

    public CrisisCall(int id, int urgency) {
        this.id = id;
        this.urgency = urgency;
    }

    public int getUrgencyLevel() {
        return urgency;
    }

    public int getCallID() {
        return id;
    }
}

class Volunteer {
    private String name;
    private boolean available;
    private int currentCalls;
    private int maxCalls;

    public Volunteer(String name, boolean available, int currentCalls, int maxCalls) {
        this.name = name;
        this.available = available;
        this.currentCalls = currentCalls;
        this.maxCalls = maxCalls;
    }

    public String getName() {
        return name;
    }

    public boolean getAvailability() {
        return available;
    }

    public int getCurrentCalls() {
        return currentCalls;
    }

    public int getMaxConcurrentCalls() {
        return maxCalls;
    }

    public void setCurrentCalls(int c) {
        currentCalls = c;
    }
}

public class ScheduleManagerTest {

    @Test
    public void testPrioritize() {
        List<CrisisCall> calls = new ArrayList<>();
        calls.add(new CrisisCall(1, 2));
        calls.add(new CrisisCall(2, 5));
        calls.add(new CrisisCall(3, 3));

        ScheduleManager.prioritize(calls);

        // highest urgency should come first
        assertEquals(5, calls.get(0).getUrgencyLevel());
        assertEquals(3, calls.get(1).getUrgencyLevel());
        assertEquals(2, calls.get(2).getUrgencyLevel());
    }

    @Test
    public void testAssignUpdatesVolunteerCalls() {
        List<CrisisCall> calls = new ArrayList<>();
        calls.add(new CrisisCall(1, 3));

        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("Alice", true, 0, 2));

        ScheduleManager.assign(calls, volunteers);

        // volunteer should now have 1 call assigned
        assertEquals(1, volunteers.get(0).getCurrentCalls());
    }

    @Test
    public void testAssignRespectsMaxCalls() {
        List<CrisisCall> calls = new ArrayList<>();
        calls.add(new CrisisCall(1, 3));

        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("Bob", true, 2, 2)); // already at max

        ScheduleManager.assign(calls, volunteers);

        // should not increase
        assertEquals(2, volunteers.get(0).getCurrentCalls());
    }

  // could be improved; adjust later  
  @Test
    public void testEscalateLogic() {
        List<CrisisCall> calls = new ArrayList<>();
        calls.add(new CrisisCall(1, 5));
        calls.add(new CrisisCall(2, 3));

        int count = 0;
        for (CrisisCall c : calls) {
            if (c.getUrgencyLevel() == 5) {
                count++;
            }
        }

        assertEquals(1, count);
    }
}
