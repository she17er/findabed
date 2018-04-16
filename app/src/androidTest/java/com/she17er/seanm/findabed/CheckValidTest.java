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
public class CheckValidTest{

    @Rule
    public ActivityTestRule<SignupScreen> rule = new ActivityTestRule<>(SignupScreen.class);

    @Test
    @UiThreadTest
    public void correctUserInfo() {
        SignupScreen signupTest = rule.getActivity();
        signupTest.username.setText("username");
        signupTest.email.setText("test@example.com");
        signupTest.phone.setText("1234567890");
        signupTest.password.setText("password");
        signupTest.passwordCheck.setText("password");
        signupTest.age.setText("20");
        signupTest.allNames = new ArrayList<>();
        signupTest.allNames.add("othername");
        assertEquals(true, signupTest.checkValid());
    }

    @Test
    @UiThreadTest
    public void emptyUsername() {
        SignupScreen signupTest = rule.getActivity();
        signupTest.username.getText().clear();
        signupTest.email.setText("test@example.com");
        signupTest.phone.setText("1234567890");
        signupTest.password.setText("password");
        signupTest.passwordCheck.setText("password");
        signupTest.age.setText("20");
        assertEquals(false, signupTest.checkValid());
    }

    @Test
    @UiThreadTest
    public void noRepeatedUsers() {
        SignupScreen signupTest = rule.getActivity();
        signupTest.username.setText("username");
        signupTest.email.setText("test@example.com");
        signupTest.phone.setText("1234567890");
        signupTest.password.setText("password");
        signupTest.passwordCheck.setText("password");
        signupTest.age.setText("20");
        signupTest.allNames = new ArrayList<>();
        assertEquals(true, signupTest.checkValid());
    }

    @Test
    @UiThreadTest
    public void repeatedUser() {
        SignupScreen signupTest = rule.getActivity();
        signupTest.username.setText("username");
        signupTest.email.setText("test@example.com");
        signupTest.phone.setText("1234567890");
        signupTest.password.setText("password");
        signupTest.passwordCheck.setText("password");
        signupTest.age.setText("20");
        signupTest.allNames = new ArrayList<>();
        signupTest.allNames.add("username");
        assertEquals(false, signupTest.checkValid());
    }

    @Test
    @UiThreadTest
    public void emptyEmail() {
        SignupScreen signupTest = rule.getActivity();
        signupTest.username.setText("name");
        signupTest.email.getText().clear();
        signupTest.phone.setText("1234567890");
        signupTest.password.setText("password");
        signupTest.passwordCheck.setText("password");
        signupTest.age.setText("20");
        assertEquals(false, signupTest.checkValid());
    }

    @Test
    @UiThreadTest
    public void wrongEmailFormat() {
        SignupScreen signupTest = rule.getActivity();
        signupTest.username.setText("name");
        signupTest.email.setText("exampletestcom");
        signupTest.phone.setText("1234567890");
        signupTest.password.setText("password");
        signupTest.passwordCheck.setText("password");
        signupTest.age.setText("20");
        assertEquals(false, signupTest.checkValid());
    }

    @Test
    @UiThreadTest
    public void emptyPhone() {
        SignupScreen signupTest = rule.getActivity();
        signupTest.username.setText("name");
        signupTest.email.setText("test@example.com");
        signupTest.phone.getText().clear();
        signupTest.password.setText("password");
        signupTest.passwordCheck.setText("password");
        signupTest.age.setText("20");
        assertEquals(false, signupTest.checkValid());
    }

    @Test
    @UiThreadTest
    public void emptyPassword() {
        SignupScreen signupTest = rule.getActivity();
        signupTest.username.setText("name");
        signupTest.email.setText("test@example.com");
        signupTest.phone.setText("1234567890");
        signupTest.password.getText().clear();
        signupTest.passwordCheck.setText("password");
        signupTest.age.setText("20");
        assertEquals(false, signupTest.checkValid());
    }

    @Test
    @UiThreadTest
    public void shortPassword() {
        SignupScreen signupTest = rule.getActivity();
        signupTest.username.setText("name");
        signupTest.email.setText("test@example.com");
        signupTest.phone.setText("1234567890");
        signupTest.password.setText("pass");
        signupTest.passwordCheck.setText("pass");
        signupTest.age.setText("20");
        assertEquals(false, signupTest.checkValid());
    }

    @Test
    @UiThreadTest
    public void emptyPasswordCheck() {
        SignupScreen signupTest = rule.getActivity();
        signupTest.username.setText("name");
        signupTest.email.setText("test@example.com");
        signupTest.phone.setText("1234567890");
        signupTest.password.setText("password");
        signupTest.passwordCheck.getText().clear();
        signupTest.age.setText("20");
        assertEquals(false, signupTest.checkValid());
    }

    @Test
    @UiThreadTest
    public void mismatchedPasswordCheck() {
        SignupScreen signupTest = rule.getActivity();
        signupTest.username.setText("name");
        signupTest.email.setText("test@example.com");
        signupTest.phone.setText("1234567890");
        signupTest.password.setText("password");
        signupTest.passwordCheck.setText("pass");
        signupTest.age.setText("20");
        assertEquals(false, signupTest.checkValid());
    }

    @Test
    @UiThreadTest
    public void emptyAge() {
        SignupScreen signupTest = rule.getActivity();
        signupTest.username.setText("name");
        signupTest.email.setText("test@example.com");
        signupTest.phone.setText("1234567890");
        signupTest.password.setText("password");
        signupTest.passwordCheck.setText("password");
        signupTest.age.getText().clear();
        assertEquals(false, signupTest.checkValid());
    }

    @Test
    @UiThreadTest
    public void impossibleAge() {
        SignupScreen signupTest = rule.getActivity();
        signupTest.username.setText("name");
        signupTest.email.setText("test@example.com");
        signupTest.phone.setText("1234567890");
        signupTest.password.setText("password");
        signupTest.passwordCheck.setText("password");
        signupTest.age.setText("2000");
        assertEquals(false, signupTest.checkValid());
    }
}
