package com.example.cs449_project;

import android.content.Context;

import androidx.test.espresso.Espresso;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
public class SpinnerTest {
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.example.cs449_project", appContext.getPackageName());
    }

    //tests to make sure that each item in the scale spinner is an item in the scale spinner's array
    //with which it was initialized
    //the for loop goes through each entry in the scale spinner, and then checks
    //it with the scales_array to make sure it is a valid entry
    @Test
    public void iterateScaleItems(){
        String[] myArray = mActivityRule.getActivity().getResources().getStringArray(R.array.scales_array);
        int size = myArray.length;
        for (int i=0; i<size; i++){
            onView(withId(R.id.scaleSelection)).perform(click());
            onData(is(myArray[i])).perform(click());
            onView(withId(R.id.scaleSelection)).check(matches(withSpinnerText(containsString(myArray[i]))));
        }
    }

    //tests to make sure that each item in the key spinner is an item in the key spinner's array
    //with which it was initialized
    //the for loop goes through each entry in the key spinner, and then checks
    //it with the keys_array to make sure it is a valid entry
    @Test
    public void iterateKeyItems(){
        String[] myArray = mActivityRule.getActivity().getResources().getStringArray(R.array.keys_array);
        int size = myArray.length;
        for (int i=0; i<size; i++){
            onView(withId(R.id.keySelection)).perform(click());
            onData(is(myArray[i])).perform(click());
            onView(withId(R.id.keySelection)).check(matches(withSpinnerText(containsString(myArray[i]))));
        }
    }
}
