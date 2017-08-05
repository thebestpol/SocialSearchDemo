package com.socialsearch;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import com.socialsearch.main.DemoUserStory;
import com.socialsearch.rules.RobolectricMockComponentRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.robolectric.Shadows.shadowOf;

/**
 * SocialSearchDemo
 * com.socialsearch
 * MainActivityShould
 */
@RunWith(RobolectricTestRunner.class) public class MainActivityShould {

  @Rule public RobolectricMockComponentRule rule =
      new RobolectricMockComponentRule();

  @Spy DemoUserStory mockUserStory = new DemoUserStory();

  @Test public void contain_fragment_container() {
    MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);

    View container = shadowOf(mainActivity).findViewById(R.id.container);
    assertThat(container, notNullValue());
  }

  @Test public void contain_search_fragment_on_start() {
    MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);

    FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();

    assertThat(fragmentManager.findFragmentById(R.id.container), notNullValue());
  }

  @Test public void initialize_user_story() {
    MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);

    verify(mockUserStory).initialize(any(MainActivity.class));
  }

  @Test public void start_user_story_on_creation() {
    Robolectric.setupActivity(MainActivity.class);

    verify(mockUserStory).start();
  }

  @Test public void handle_activity_recreation() {
    Bundle fakeBundle = new Bundle();
    Robolectric.buildActivity(MainActivity.class).create(fakeBundle).visible().get();

    verify(mockUserStory, never()).start();
    verify(mockUserStory).restoreState(eq(fakeBundle));
  }

  @Test public void delegate_save_instance_state_to_user_story() {
    MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);

    mainActivity.recreate();

    verify(mockUserStory).saveState(any(Bundle.class));
    verify(mockUserStory).restoreState(any(Bundle.class));
  }
}