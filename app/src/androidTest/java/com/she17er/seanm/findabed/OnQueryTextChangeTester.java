package com.she17er.seanm.findabed;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.UiThreadTestRule;
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
public class OnQueryTextChangeTester{

    @Rule
    public ActivityTestRule<Dashboard> rule = new ActivityTestRule<>(Dashboard.class);

    @Test
    @UiThreadTest
    public void test1() {
        Dashboard textChangeTest = rule.getActivity();

        ArrayList<Shelter> correct = new ArrayList<Shelter>();
        correct.add(new Shelter());
        correct.add(new Shelter());
        correct.get(0).setName("abcabcabc");
        correct.get(1).setName("abca");

        textChangeTest.currentShelters.clear();
        textChangeTest.currentShelters.add(new Shelter());
        textChangeTest.currentShelters.add(new Shelter());
        textChangeTest.currentShelters.get(0).setName("abcabcabc");
        textChangeTest.currentShelters.get(1).setName("abca");

        textChangeTest.populateShelterList(correct);
        RecyclerView current = textChangeTest.shelterView;
        textChangeTest.onQueryTextChange("abc");
        assertEquals(current, textChangeTest.shelterView);
    }

    @Test
    @UiThreadTest
    public void test2() {
        Dashboard textChangeTest = rule.getActivity();

        ArrayList<Shelter> correct = new ArrayList<Shelter>();

        textChangeTest.currentShelters.clear();
        textChangeTest.currentSelters.add(new Shelter());
        textChangeTest.currentSelters.add(new Shelter());
        textChangeTest.currentShelters.get(0).setName("abcabcabc");
        textChangeTest.currentShelters.get(1).setName("abca");

        textChangeTest.populateShelterList(correct);
        RecyclerView current = textChangeTest.shelterView;
        textChangeTest.onQueryTextChange("ABC");
        assertEquals(current, textChangeTest.shelterView);
    }

    @Test
    @UiThreadTest
    public void test3() {
        Dashboard textChangeTest = rule.getActivity();

        ArrayList<Shelter> correct = new ArrayList<Shelter>();
        correct.add(new Shelter());
        correct.add(new Shelter());
        correct.get(0).setName("abCABcabc");
        correct.get(1).setName("abCa");

        textChangeTest.currentShelters.clear();
        textChangeTest.currentSelters.add(new Shelter());
        textChangeTest.currentSelters.add(new Shelter());
        textChangeTest.currentShelters.get(0).setName("abCABcabc");
        textChangeTest.currentShelters.get(1).setName("abCa");

        textChangeTest.populateShelterList(correct);
        RecyclerView current = textChangeTest.shelterView;
        textChangeTest.onQueryTextChange("abc");
        assertEquals(current, textChangeTest.shelterView);
    }

    @Test
    @UiThreadTest
    public void test4() {
        Dashboard textChangeTest = rule.getActivity();

        ArrayList<Shelter> correct = new ArrayList<Shelter>();
        correct.add(new Shelter());
        correct.get(0).setName("abcadbcabc");

        textChangeTest.currentShelters.clear();
        textChangeTest.currentSelters.add(new Shelter());
        textChangeTest.currentSelters.add(new Shelter());
        textChangeTest.currentShelters.get(0).setName("abcadbcabc");
        textChangeTest.currentShelters.get(1).setName("abca");

        textChangeTest.populateShelterList(correct);
        RecyclerView current = textChangeTest.shelterView;
        textChangeTest.onQueryTextChange("abcad");
        assertEquals(current, textChangeTest.shelterView);
    }

    @Test
    @UiThreadTest
    public void test5() {
        Dashboard textChangeTest = rule.getActivity();

        ArrayList<Shelter> correct = new ArrayList<Shelter>();

        textChangeTest.currentShelters.clear();
        textChangeTest.currentSelters.add(new Shelter());
        textChangeTest.currentSelters.add(new Shelter());
        textChangeTest.currentShelters.get(0).setName("abcabcabc");
        textChangeTest.currentShelters.get(1).setName("abca");

        textChangeTest.populateShelterList(correct);
        RecyclerView current = textChangeTest.shelterView;
        textChangeTest.onQueryTextChange("querty");
        assertEquals(current, textChangeTest.shelterView);
    }

    @Test
    @UiThreadTest
    public void test6() {
        Dashboard textChangeTest = rule.getActivity();

        ArrayList<Shelter> correct = new ArrayList<Shelter>();

        textChangeTest.currentShelters.clear();
        textChangeTest.currentSelters.add(new Shelter());
        textChangeTest.currentSelters.add(new Shelter());
        textChangeTest.currentShelters.get(0).setName("abcabcabc");
        textChangeTest.currentShelters.get(1).setName("abca");

        textChangeTest.populateShelterList(correct);
        RecyclerView current = textChangeTest.shelterView;
        textChangeTest.onQueryTextChange("QUERTY");
        assertEquals(current, textChangeTest.shelterView);
    }

    @Test
    @UiThreadTest
    public void test7() {
        Dashboard textChangeTest = rule.getActivity();

        ArrayList<Shelter> correct = new ArrayList<Shelter>();
        correct.add(new Shelter());
        correct.get(0).setName("abcadbcabc");

        textChangeTest.currentShelters.clear();
        textChangeTest.currentSelters.add(new Shelter());
        textChangeTest.currentSelters.add(new Shelter());
        textChangeTest.currentShelters.get(0).setName("abca");
        textChangeTest.currentShelters.get(1).setName("abcadbcabc");

        textChangeTest.populateShelterList(correct);
        RecyclerView current = textChangeTest.shelterView;
        textChangeTest.onQueryTextChange("abcad");
        assertEquals(current, textChangeTest.shelterView);
    }

    @Test
    @UiThreadTest
    public void test8() {
        Dashboard textChangeTest = rule.getActivity();

        ArrayList<Shelter> correct = new ArrayList<Shelter>();
        correct.add(new Shelter());
        correct.add(new Shelter());
        correct.get(0).setName("abca");
        correct.get(1).setName("abcabcabc");

        textChangeTest.currentShelters.clear();
        textChangeTest.currentSelters.add(new Shelter());
        textChangeTest.currentSelters.add(new Shelter());
        textChangeTest.currentShelters.get(0).setName("abca");
        textChangeTest.currentShelters.get(1).setName("abcabcabc");

        textChangeTest.populateShelterList(correct);
        RecyclerView current = textChangeTest.shelterView;
        textChangeTest.onQueryTextChange("abc");
        assertEquals(current, textChangeTest.shelterView);
    }
}
