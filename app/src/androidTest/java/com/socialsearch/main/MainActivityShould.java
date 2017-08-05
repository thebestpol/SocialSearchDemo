package com.socialsearch.main;

import android.support.test.rule.ActivityTestRule;
import com.socialsearch.R;
import com.socialsearch.main.state.DemoStoryState;
import com.socialsearch.rule.EspressoMockComponentRule;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Spy;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.when;

/**
 * SocialSearchDemo
 * com.socialsearch.main
 * MainActivityShould
 */
public class MainActivityShould {

  @Rule public ActivityTestRule<MainActivity> activityTestRule =
      new ActivityTestRule<>(MainActivity.class, false, false);

  @Rule public EspressoMockComponentRule mockComponentRule = new EspressoMockComponentRule();

  @Spy DemoUserStory spyDemoStoryState = new DemoUserStory();

  @Test public void show_search_fragment_on_creation() {
    activityTestRule.launchActivity(null);

    onView(withId(R.id.container)).check(matches(isDisplayed()));
    onView(withText("Search")).check(matches(isDisplayed()));
  }

  @Test public void display_feedback_message() {
    DemoStoryState stubStoryState = new DemoStoryState();
    stubStoryState.setQuery("");
    when(spyDemoStoryState.getStoryState()).thenReturn(stubStoryState);

    activityTestRule.launchActivity(null);

    onView(withId(R.id.feedbackTextView)).check(matches(isDisplayed()));
    onView(withId(R.id.feedbackTextView)).check(
        matches(withText("Click on Search menu item to make a social search.")));
  }

  @Test public void display_error_message() {
    DemoStoryState stubStoryState = new DemoStoryState();
    stubStoryState.setErrorMessage("Fake error message");
    when(spyDemoStoryState.getStoryState()).thenReturn(stubStoryState);

    activityTestRule.launchActivity(null);

    onView(withId(R.id.feedbackTextView)).check(matches(isDisplayed()));
    onView(withId(R.id.feedbackTextView)).check(matches(withText("Fake error message")));
  }
}