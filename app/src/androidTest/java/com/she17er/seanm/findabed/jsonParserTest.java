package com.she17er.seanm.findabed;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.test.ActivityInstrumentationTestCase;

import com.google.android.gms.maps.model.Dash;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.regex.Pattern;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class jsonParserTest{

    @Rule
    public ActivityTestRule<Dashboard> rule = new ActivityTestRule<>(Dashboard.class);


    @Test (expected = StringIndexOutOfBoundsException.class)
    public void jsonParserTesterEmptyString() {
        Dashboard dashboardTest = rule.getActivity();
        ArrayList<Shelter> allShelter = new ArrayList<>();
        ArrayList<Shelter> equals1 = new ArrayList<>();
        allShelter = dashboardTest.jsonParser("");
        assertNull(allShelter);
    }

    @Test
    public void jsonParserTesterSomeString() {
        Dashboard dashboardTest = rule.getActivity();
        ArrayList<Shelter> allShelter = new ArrayList<>();
        ArrayList<Shelter> equals1 = new ArrayList<>();
        allShelter = dashboardTest.jsonParser("abcd");
        assertNull(allShelter);
    }

    @Test
    public void jsonParserTesterGood() {
        Dashboard dashboardTest = rule.getActivity();
        ArrayList<Shelter> allShelter = new ArrayList<>();
        String toTest = "[{aacceptedTypess:[k[Women,Children]l],aacceptedAge5:[2nil3],t_id2:85a9583fdd6e6d700144ca3bcp,name6:7The Atlanta Day Center for Women & Children9,lcoOrdinatesk:=-84.408771,33.7848895,hlocation3:l655 Ethel Street, Atlanta, Georgia 30318p,0currCapacityk:111,1maxCapacity2:140,3phoneNumber4:4045884007,9role0:shelterk,elogin0:false,3specialNotes9:2Career Facilitationp";
        ArrayList<Shelter> equals1 = new ArrayList<>();
        Shelter test = new Shelter();
        test.setGenderAndAge("women, children");
        test.setBackendID("5a9583fdd6e6d700144ca3bc");
        test.setCapacity("140");
        test.setName("The Atlanta Day Center for Women & Children");
        test.setLongitude("-84.408771");
        test.setLatitude("33.784889");
        test.setAddress("655 Ethel Street, Atlanta, Georgia 30318");
        test.setPhoneNumber("4045884007");
        test.setCurrentCapacity("111");
        equals1.add(test);
        allShelter = dashboardTest.jsonParser(toTest);
        assertEquals(allShelter.get(0), equals1.get(0));
    }
}
