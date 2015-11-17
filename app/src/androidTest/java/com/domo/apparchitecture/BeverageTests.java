package com.domo.apparchitecture;

import android.content.ContentResolver;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.domo.apparchitecture.activities.BeveragesActivity;
import com.domo.apparchitecture.contracts.Beverage;
import com.domo.apparchitecture.injection.components.ApplicationComponentTest;
import com.domo.apparchitecture.services.BeverageService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertTrue;

/**
 * Created by dallindyer on 11/17/15.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class BeverageTests extends BaseTests{
    @Rule public ActivityTestRule<BeveragesActivity> mBeverageActivity = new ActivityTestRule<>(BeveragesActivity.class, true, // initialTouchMode
            false);

    @Before public void setup() {
        BeveragesActivity beveragesActivity = mBeverageActivity.launchActivity(new Intent());
        ContentResolver contentResolver = beveragesActivity.getContentResolver();
        contentResolver.delete(Beverage.CONTENT_URI, null, null);
    }

    @Test public void testAddingABeverage() {

        // insert a bunch of drinks
        int i;
        for (i = 0; i < 10; i++) {
            onView(withId(R.id.fab)).perform(click());
            onView(withId(R.id.drink_name)).check(matches(isDisplayed())).perform(typeText("Beverage #" + i));
            onView(withId(R.id.save)).perform(click());
            assertTrue(waitForRecyclerAdapterToBePopulated(R.id.beverage_recycle_view, 1, i+1));
        }

        // edit a drink..
        onView(withText("Beverage #2")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.drink_name)).perform(replaceText("Beverage #2 - Modified"));
        onView(withId(R.id.save)).perform(click());

        onView(withText("Beverage #2 - Modified")).check(matches(isDisplayed())).perform(click());

        // delete a drink
        onView(withId(R.id.delete)).perform(click());
        assertTrue(waitForRecyclerAdapterToBePopulated(R.id.beverage_recycle_view, 1, i - 1));
    }


    @Test public void testAddingABeverageMocked() {
        // setup for mocks..
        BeverageApplication.initializeWithInjector(ApplicationComponentTest.Initializer.init());

        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.drink_name)).check(matches(isDisplayed())).perform(typeText("Beverage"));
        onView(withId(R.id.save)).perform(click());

        BeverageService beverageServiceMock = BeverageApplication.getApplicationComponent().getBeverageService();
        Mockito.verify(beverageServiceMock).insert("Beverage");

    }

}
