package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class VolunteerSpecialtyTest {
    private String expectedSpecialtyName;
    private String expectedSpecialtyDescription;
    private LocalDate expectedCertificationDate;
    private LocalDate expectedCerificationExpiryDate = expectedCertificationDate.plusYears(2);


    @Before
    public void setUp(){
        volunteerSpecialty = new volunteerSpecialty(expectedSpecialtyName, expectedSpecialtyDescription, expectedCertificationDate);
    }

    @Test
    public void testGetSpecialtyName(){
        assertEquals("Volunteer should be given specialty", expectedSpecialtyName, volunteerSpecialty.getSpecialtyName());
    }

    @Test
    public void testGetCertificationDate(){
        assertEquals("Volunteer certification has date it was recieved", expectedCertificationDate, volunteerSpecialty.getCertificationDate());
    }

    @Test
    public void testGetCertificationExpiryDate(){
        assertEquals("Volunteer certification has an expiry date", expectedCertificationExpiryDate, volunteerSpecialty.getCertificationExpiryDate());
    }

    @Test
    public void testInvalidCertification() throws Exception{
        
    }

    @Test
    public void testValidCertification(){
        boolean expectedIsValid = true;
        volunteerSpecialty.isCertificationValid();
    }

}
