//package com.she17er.seanm.findabed;
//import android.support.v7.widget.RecyclerView;
//
//import org.junit.rules.ExpectedException;
//import org.junit.Rule;
//import org.junit.Test;
//
//import java.util.ArrayList;
//
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.CoreMatchers.not;
//import static org.hamcrest.CoreMatchers.nullValue;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.junit.Assert.assertEquals;
//
//public class onQueryTextChangeTester {
//
//    @Test
//    public void test1() {
//        ArrayList<Shelter> correct = new ArrayList<Shelter>();
//        correct.add(new Shelter());
//        correct.add(new Shelter());
//        correct.get(0).setName("abcabcabc");
//        correct.get(1).setName("abca");
//
//        currentShelters.clear();
//        currentSelters.add(new Shelter());
//        currentSelters.add(new Shelter());
//        currentShelters.get(0).setName("abcabcabc");
//        currentShelters.get(1).setName("abca");
//
//        populateShelterList(correct);
//        RecyclerView current = shelterView;
//        onQueryTextChange("abc");
//        assertEquals(current, shelterView);
//    }
//    @Test
//    public void test2() {
//        ArrayList<Shelter> correct = new ArrayList<Shelter>();
//
//        currentShelters.clear();
//        currentSelters.add(new Shelter());
//        currentSelters.add(new Shelter());
//        currentShelters.get(0).setName("abcabcabc");
//        currentShelters.get(1).setName("abca");
//
//        populateShelterList(correct);
//        RecyclerView current = shelterView;
//        onQueryTextChange("ABC");
//        assertEquals(current, shelterView);
//    }
//    @Test
//    public void test3() {
//        ArrayList<Shelter> correct = new ArrayList<Shelter>();
//        correct.add(new Shelter());
//        correct.add(new Shelter());
//        correct.get(0).setName("abCABcabc");
//        correct.get(1).setName("abCa");
//
//        currentShelters.clear();
//        currentSelters.add(new Shelter());
//        currentSelters.add(new Shelter());
//        currentShelters.get(0).setName("abCABcabc");
//        currentShelters.get(1).setName("abCa");
//
//        populateShelterList(correct);
//        RecyclerView current = shelterView;
//        onQueryTextChange("abc");
//        assertEquals(current, shelterView);
//    }
//    @Test
//    public void test4() {
//        ArrayList<Shelter> correct = new ArrayList<Shelter>();
//        correct.add(new Shelter());
//        correct.get(0).setName("abcadbcabc");
//
//        currentShelters.clear();
//        currentSelters.add(new Shelter());
//        currentSelters.add(new Shelter());
//        currentShelters.get(0).setName("abcadbcabc");
//        currentShelters.get(1).setName("abca");
//
//        populateShelterList(correct);
//        RecyclerView current = shelterView;
//        onQueryTextChange("abcad");
//        assertEquals(current, shelterView);
//    }
//    @Test
//    public void test5() {
//        ArrayList<Shelter> correct = new ArrayList<Shelter>();
//
//        currentShelters.clear();
//        currentSelters.add(new Shelter());
//        currentSelters.add(new Shelter());
//        currentShelters.get(0).setName("abcabcabc");
//        currentShelters.get(1).setName("abca");
//
//        populateShelterList(correct);
//        RecyclerView current = shelterView;
//        onQueryTextChange("querty");
//        assertEquals(current, shelterView);
//    }
//    @Test
//    public void test6() {
//        ArrayList<Shelter> correct = new ArrayList<Shelter>();
//
//        currentShelters.clear();
//        currentSelters.add(new Shelter());
//        currentSelters.add(new Shelter());
//        currentShelters.get(0).setName("abcabcabc");
//        currentShelters.get(1).setName("abca");
//
//        populateShelterList(correct);
//        RecyclerView current = shelterView;
//        onQueryTextChange("QUERTY");
//        assertEquals(current, shelterView);
//    }
//    @Test
//    public void test7() {
//        ArrayList<Shelter> correct = new ArrayList<Shelter>();
//        correct.add(new Shelter());
//        correct.get(0).setName("abcadbcabc");
//
//        currentShelters.clear();
//        currentSelters.add(new Shelter());
//        currentSelters.add(new Shelter());
//        currentShelters.get(0).setName("abca");
//        currentShelters.get(1).setName("abcadbcabc");
//
//        populateShelterList(correct);
//        RecyclerView current = shelterView;
//        onQueryTextChange("abcad");
//        assertEquals(current, shelterView);
//    }
//    @Test
//    public void test8() {
//        ArrayList<Shelter> correct = new ArrayList<Shelter>();
//        correct.add(new Shelter());
//        correct.add(new Shelter());
//        correct.get(0).setName("abca");
//        correct.get(1).setName("abcabcabc");
//
//        currentShelters.clear();
//        currentSelters.add(new Shelter());
//        currentSelters.add(new Shelter());
//        currentShelters.get(0).setName("abca");
//        currentShelters.get(1).setName("abcabcabc");
//
//        populateShelterList(correct);
//        RecyclerView current = shelterView;
//        onQueryTextChange("abc");
//        assertEquals(current, shelterView);
//    }
//}