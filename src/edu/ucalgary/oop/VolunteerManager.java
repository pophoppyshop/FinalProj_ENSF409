package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.List;

public class VolunteerManager{
  private static List<Volunteer> volunteers = new ArrayList<>();
  public static List<Volunteer> getVolunteers(){
  return volunteers;
  }
  public static void addVolunteer(Volunteer v){
  volunteers.add(v);
  }
  public static void printVolunteers(List<Volunteer> list){
  for (Volunteer v : list){
    System.out.println(
      "Name: " + v.getName() + "\nPhone: " + v.getPhoneNumber() + "\nAvailable: " 
      + (v.getAvailability() ? "Yes" : "No")
    );
    System.out.println("------------");
  }
  }
}
