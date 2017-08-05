package com.socialsearch.main;

import android.support.test.rule.ActivityTestRule;
import com.socialsearch.R;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * SocialSearchDemo
 * com.socialsearch.main
 * MainActivityShould
 */
public class MainActivityShould {

  @Rule public ActivityTestRule<MainActivity> activityTestRule =
      new ActivityTestRule<>(MainActivity.class, false, false);

  @Test public void show_search_fragment_on_creation() {
    activityTestRule.launchActivity(null);

    onView(withId(R.id.container)).check(matches(isDisplayed()));
    onView(withText("Search")).check(matches(isDisplayed()));
  }
}