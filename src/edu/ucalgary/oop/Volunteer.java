package edu.ucalgary.oop;

public class Volunteer {

    private int volunteerID;
    private String name;
    private boolean isAvailable;
    private int maxConcurrentCalls;
    private int currentCalls;
    private String lastAvailableChange;
    private VolunteerSpecialty specialty;
    private String phoneNumber;
    private List<VolunteerSpecialty> specialties;

    public Volunteer(String name, String phoneNumber, boolean isAvailable, int maxConcurrentCalls){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isAvailable = isAvailable;
        this.maxConcurrentCalls = maxConcurrentCalls;
    }

    public int getVolunteerID(){
        return volunteerID;
    }

    public String getName(){
        return name;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public boolean getAvailability(){
        return isAvailable;
    }

    public int getMaxConcurrentCalls(){
        return maxConcurrentCalls;
    }

    public int getCurrentCalls(){
        return currentCalls;
    }

    public String getLastAvailableChange(){
        return lastAvailableChange;
    }

    public void setAvailability(boolean isAvailable){
        this.isAvailable = isAvailable;
    }

    public void setCurrentCalls(int currentCalls){
        this.currentCalls = currentCalls;
    }

    public List<VolunteerSpecialty> getSpecialties(){
        return specialties;
    }    

}
