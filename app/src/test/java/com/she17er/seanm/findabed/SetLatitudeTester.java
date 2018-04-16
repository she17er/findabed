package com.she17er.seanm.findabed;
import org.junit.rules.ExpectedException;
import org.junit.Rule;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class SetLatitudeTester {

    public static final double DELTA = .0000001;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void setLatitudeGood() throws IllegalArgumentException {
        Shelter s = new Shelter();
        s.setLatitude("12.454352");
        double correct = 12.454352;
        assertEquals(correct, s.getLatitude(), DELTA);
    }
    @Test
    public void testExceptions() {
        Shelter s = new Shelter();
        exception.expect(IllegalArgumentException.class);
        s.setLatitude(null);
    }
    @Test
    public void testExceptions2() {
        Shelter s = new Shelter();
        s.setLatitude("abc");

    }
    @Test
    public void testExceptions3() {
        Shelter s = new Shelter();
        s.setLatitude("ab234c");

    }
    @Test
    public void setLatitudeGoodWithSemiColon() {
        Shelter s = new Shelter();
        s.setLatitude("12.45;4352");
        double correct = 0.0;
        assertEquals(correct, s.getLatitude(), DELTA);
    }

    public void setLatitudeGoodWithComma() {
        Shelter s = new Shelter();
        s.setLatitude("12.45,4352");
        double correct = 0.0;
        assertEquals(correct, s.getLatitude(), DELTA);
    }
    @Test
    public void setLatitudeBadWithComma() {
        Shelter s = new Shelter();
        s.setLatitude("1233.45,4352");
    }
    @Test
    public void setLatitudeBadWithSemicolons() {
        Shelter s = new Shelter();
        s.setLatitude("1233.45;4352");
    }
    @Test
    public void setLatitudeBad() {
        Shelter s = new Shelter();
        s.setLatitude("1233.454352");
    }
    //Boundary Checks
    @Test
    public void setLatitude90() {
        Shelter s = new Shelter();
        s.setLatitude("90");
        double correct = 90;
        assertEquals(correct, s.getLatitude(), DELTA);
    }
    @Test
    public void setLatitudeNegative90() {
        Shelter s = new Shelter();
        s.setLatitude("-90");
        double correct = -90;
        assertEquals(correct, s.getLatitude(), DELTA);
    }
    @Test
    public void setLatitudeNegative90Less() {
        Shelter s = new Shelter();
        s.setLatitude("-90.1");
    }
    @Test
    public void setLatitude90Greater() {
        Shelter s = new Shelter();
        s.setLatitude("90.1");
    }
}
