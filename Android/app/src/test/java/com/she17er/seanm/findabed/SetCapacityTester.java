package com.she17er.seanm.findabed;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by krrishdholakia on 4/15/18.
 */
public class SetCapacityTester {
    @Test(expected = IllegalArgumentException.class)
    public void setCapacity() throws Exception {
        Shelter shelterCap = new Shelter();

        shelterCap.setCapacity(" ");
        assertEquals(shelterCap.getCapacity(), 0);

<<<<<<< HEAD
=======

>>>>>>> 934e34f0a2821a134a5464613181e60785e54cc3
        shelterCap.setCapacity("87");
        assertEquals(shelterCap.getCapacity(), 87);

        shelterCap.setCapacity("fail");

    }

<<<<<<< HEAD
    @Test (expected = NullPointerException.class)
    public void setCapacityNull() throws Exception {
        Shelter shelterCap = new Shelter();
        shelterCap.setCapacity(null);
        assertEquals(shelterCap.getCapacity(), 0);

=======
    @Test(expected = NullPointerException.class)
    public void setCapNull() throws Exception {
        Shelter shelterCap = new Shelter();

        shelterCap.setCapacity(null);
        assertEquals(shelterCap.getCapacity(), 0);
>>>>>>> 934e34f0a2821a134a5464613181e60785e54cc3
    }

}