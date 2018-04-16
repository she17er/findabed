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

    private Dashboard testDashboard;

    @Rule
    public ActivityTestRule<Dashboard> rule = new ActivityTestRule<>(Dashboard.class);


    @Test
    public void jsonParser() {
        Dashboard dashboardTest = rule.getActivity();
        ArrayList<Shelter> allShelter = new ArrayList<>();
        ArrayList<Shelter> equals1 = new ArrayList<>();
        try {
            allShelter = dashboardTest.jsonParser("");
        } catch (Exception e) {
            assertEquals(equals1, allShelter);
        }
        RecyclerView testView = (RecyclerView) dashboardTest.findViewById(R.id.shelterRecyclerView);
    }
}
