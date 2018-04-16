package com.she17er.seanm.findabed;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(value = BlockJUnit4ClassRunner.class)
public class SetLongitudeTester{

    public static final double DIFFERENCE = 0.0001;

    @Test
    public void testNormal() {
        Shelter testShelter = new Shelter();
        testShelter.setLongitude("12.45");
        assertEquals( 12.45, testShelter.getLongitude(), DIFFERENCE);
    }

    @Test
    public void testInvalid() {
        Shelter testShelter = new Shelter();
        testShelter.setLongitude("adfsjk");
    }

    @Test (expected = NullPointerException.class)
    public void testNull() {
        Shelter testShelter = new Shelter();
        testShelter.setLongitude(null);
    }

    @Test
    public void testNegative() {
        Shelter testShelter = new Shelter();
        testShelter.setLongitude("-45");
        assertEquals(-45, testShelter.getLongitude(), DIFFERENCE);
    }

    @Test
    public void testComma() {
        Shelter testShelter = new Shelter();
        testShelter.setLongitude("12, 45");
    }

    @Test
    public void testComma1() {
        Shelter testShelter = new Shelter();
        testShelter.setLongitude("12.45,6");
        assertEquals(0.0, testShelter.getLongitude(), DIFFERENCE);
    }

    @Test
    public void testColumn() {
        Shelter testShelter = new Shelter();
        testShelter.setLongitude("12:45");
        assertEquals(0.0, testShelter.getLongitude(), DIFFERENCE);
    }

}
