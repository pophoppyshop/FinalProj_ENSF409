package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class VolunteerSpecialtyTest {
    private String expectedSpecialtyName = "trauma counseling";
    private String expectedSpecialtyDescription = "expertise with counseling individuals experiencing trauma";
    private LocalDate expectedCertificationDateValid = LocalDate.now();
    private LocalDate expectedCertificationDateInvalid = LocalDate.now().minusyears(3);
    private LocalDate expiryValid = expectedCertificationDateValid.plusYears(2);
    privateLocalDate expiryInvalid = expectedCertificationDateInvalid.plusYears(2);


    @Before
    public void setUp(){
        volunteerSpecialtyValid = new volunteerSpecialty(expectedSpecialtyName, expectedSpecialtyDescription, expectedCertificationDateValid);
        volunteerSpecialtyInvalid = new volunteerSpecialty(expectedSpecialtyName, expectedSpecialtyDescription, expectedCertificationDateInvalid);
    }

    @Test
    public void testGetSpecialtyName(){
        assertEquals("Volunteer should be given specialty", expectedSpecialtyName, volunteerSpecialtyValid.getSpecialtyName());
    }

    @Test
    public void testGetCertificationDate(){
        assertEquals("Volunteer certification has date it was recieved", expectedCertificationDate, volunteerSpecialtyValid.getCertificationDate());
    }

    @Test
    public void testGetCertificationExpiryDateValid(){
        assertEquals("Volunteer certification has an expiry date", expectedCertificationExpiryDateValid, volunteerSpecialtyValid.getCertificationExpiryDate());
    }

    @Test
    public void testGetCertificationExpiryDateExpired(){
        assertEquals("Volunteer certification has an expiry date (is expired)", expectedCertificationDateInvalid, volunteerSpecialtyInvalid.getCertificationExpiryDate());
    }

    @Test
    public void testValidCertification(){
        boolean expectedIsValid = true;
        volunteerSpecialtyValid.isCertificationValid();
    }

    @Test
    public void testInvalidCertification() throws Exception{
        boolean expectedIsValid = false;
        volunteerSpecialtyInvalid.isCertificationValid();
    }
}
