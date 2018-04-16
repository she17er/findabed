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
public class CheckValidTest{

    @Rule
    public ActivityTestRule<SignupScreen> rule = new ActivityTestRule<>(SignupScreen.class);

    @Test
    public void checkValid() {
        SignupScreen signupTest = rule.getActivity();
        try {
            signupTest.username.getText().clear();
            signupTest.email.getText().clear();
            signupTest.phone.getText().clear();
            signupTest.password.getText().clear();
            signupTest.passwordCheck.getText().clear();
            signupTest.age.getText().clear();
        } catch (Exception e) {
            assertEquals(false, signupTest.checkValid());
        }
    }

}
