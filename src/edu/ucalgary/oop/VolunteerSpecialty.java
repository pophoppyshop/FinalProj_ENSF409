package edu.ucalgary.oop;
import java.time.LocalDate;
import java.time.LocalTime;
public class VolunteerSpecialty {
    private String specialtyName;
    private String specialtyDescription; 
    private LocalDate certificationDate;

    public VolunteerSpecialty(String specialtyName, String specialtyDescription, LocalDate certificationDate){
        this.specialtyName = specialtyName;
        this.specialtyDescription = specialtyDescription;
        this.certificationDate = certificationDate;
    }

    public String getSpecialtyName(){
        return specialtyName;
    }
    public String getSpecialtyDescription(){
        return specialtyDescription;
    }
    public LocalDate getCertificationDate(){
        return certificationDate;
    }
    public LocalDate getCertificationExpiryDate(){
        return certificationDate.plusYears(2);
    }
    public boolean isCertificationValid(){
        if (LocalDate.now().isAfter(getCertificationExpiryDate())){ return false;}
        return true;
    }
    public void setSpecialty(String name){
        this.specialtyName = name;
    }
    public void setSpecialtyDescription(String specialtyDesc){
        this.specialtyDescription = specialtyDesc;
    }
    public void setCertificationDate(LocalDate certDate){
        this.certificationDate = certDate;
    }
    
    public String toString() {
    	return "\n\tSpecialty name: " + specialtyName +
    			"\n\tSpecialty description: " + specialtyDescription +
    			"\n\tCertificate expiry date: " + certificationDate;
    }
}
