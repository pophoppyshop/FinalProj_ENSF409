package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class VolunteerSpecialtyTest {
    private String expectedSpecialtyName;
    private String expectedSpecialtyDescription;
    private LocalDate expectedCertificationDate;


    @Before
    public void setUp(){
        //TO DO
    }

    @Test
    public void testGetSpecialtyName(){
        //TO DO
    }

    @Test
    public void testGetCertificationDate(){
        //TO DO
    }

    @Test
    public void testGetCertificationExpiryDate(){
        //TO DO
    }

    @Test
    public void testInvalidCertification() throws Exception{
        //TO DO
        //should isCertificationValid throw error for invalid cert?
    }

    @Test
    public void testValidCertification(){
        //TO DO
    }

}
