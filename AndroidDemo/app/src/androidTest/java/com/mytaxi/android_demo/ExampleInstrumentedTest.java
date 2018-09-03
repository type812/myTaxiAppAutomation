package com.mytaxi.android_demo;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.mytaxi.android_demo.activities.MainActivity;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.constraint.Constraints.TAG;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.CursorMatchers.withRowString;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    //Espresso rule which tells which activity to start.
    @Rule
    @ClassRule
    public static ActivityTestRule activityRule = new ActivityTestRule(MainActivity.class,true,false);

    @BeforeClass
    public static void setUp(){
        activityRule.getActivity();
        Log.d(TAG,"setUp()");
    }
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.mytaxi.android_demo", appContext.getPackageName());
        //activityRule.
    }
    @Test
    public void test_myTaxiApp() throws InterruptedException {
        //start our activity.
        activityRule.launchActivity(null);
        //Click the username text input field and provide login info.
        onView(withId(R.id.edt_username)).perform(click());
        onView(withId(R.id.edt_username)).perform(ViewActions.typeText("crazydog335"), closeSoftKeyboard());
        onView(withId(R.id.edt_password)).perform(click());
        onView(withId(R.id.edt_password)).perform(ViewActions.typeText("venture"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());
        Thread.sleep(4000); //I know this is not a good idea ,used it due to challenges faced in switching between activities.
        //Search by string 'sa'
        onView(withId(R.id.textSearch)).perform(click());
        onView(withId(R.id.textSearch)).perform(clearText());
        onView(withId(R.id.textSearch)).perform(ViewActions.typeText("sa"),closeSoftKeyboard());
        Thread.sleep(5000);
        //Click on the second search result ,which is not picked by id.
        onView(withText("Sarah Scott")).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        Thread.sleep(4000);
        //Click the call button on the searched driver profile page.
        onView(withId(R.id.fab)).perform(click());
    }
    @AfterClass
    public static void tearDown(){
        Log.d(TAG,"tearDown()");
        activityRule.getActivity().finish();
    }

}
