package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDate;

public class VolunteerSpecialtyTest {
    private String expectedSpecialtyName = "trauma counseling";
    private String expectedSpecialtyDescription = "expertise with counseling individuals experiencing trauma";
    private LocalDate expectedCertificationDateValid = LocalDate.now();
    private LocalDate expectedCertificationExpiry = LocalDate.now().plusYears(2);
    //private LocalDate expiryValid = expectedCertificationDateValid.plusYears(2);
    //private LocalDate expiryInvalid = expectedCertificationDateInvalid.plusYears(2);

    private VolunteerSpecialty volunteerSpecialtyValid;
    private VolunteerSpecialty volunteerSpecialtyInvalid;


    @Before
    public void setUp(){
        //set new VolunteerSepcialty information
        volunteerSpecialtyValid = new VolunteerSpecialty(expectedSpecialtyName, expectedSpecialtyDescription, expectedCertificationDateValid);
        volunteerSpecialtyInvalid = new VolunteerSpecialty(expectedSpecialtyName, expectedSpecialtyDescription, expectedCertificationDateValid.minusYears(3));
    }

    @Test
    public void testGetSpecialtyName(){
        //test that getSpecialtyName() returns expected value
        assertEquals("Volunteer should be given specialty", expectedSpecialtyName, volunteerSpecialtyValid.getSpecialtyName());
    }

    @Test
    public void testGetCertificationDateValid(){
        //test that getCertificationDate for a valid certificate returns correct value
        assertEquals("Volunteer certification has date it was recieved", expectedCertificationDateValid, volunteerSpecialtyValid.getCertificationDate());
    }

    @Test
    public void testGetCertificationExpiryDate(){
        //test that getCertificationExpiry returns correct value
        assertEquals("Volunteer certification has an expiry date", expectedCertificationExpiry, volunteerSpecialtyValid.getCertificationExpiryDate());
    }


    @Test
    public void testValidCertification(){
        //test that valid certification returns valid result
        boolean expectedIsValid = true;
        assertEquals("Certification should be valid", expectedIsValid, volunteerSpecialtyValid.isCertificationValid());
    }

    @Test
    public void testInvalidCertification(){
        //test that invalid exception returns false
        boolean expectedIsValid = false;
        assertEquals("Certification should be invalid", expectedIsValid, volunteerSpecialtyInvalid.isCertificationValid());
    }
}
