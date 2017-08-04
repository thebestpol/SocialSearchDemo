package com.socialsearch;

import android.app.FragmentManager;
import android.view.View;
import com.socialsearch.main.DemoUserStory;
import com.socialsearch.rules.RobolectricMockApplicationComponentRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.robolectric.Shadows.shadowOf;

/**
 * SocialSearchDemo
 * com.socialsearch
 * MainActivityShould
 */
@RunWith(RobolectricTestRunner.class) public class MainActivityShould {

  @Rule public RobolectricMockApplicationComponentRule rule =
      new RobolectricMockApplicationComponentRule();

  @Mock DemoUserStory mockUserStory;

  @Test public void contain_fragment_container() {
    MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);

    View container = shadowOf(mainActivity).findViewById(R.id.container);
    assertThat(container, notNullValue());
  }

  @Test public void contain_search_fragment_on_start() {
    MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);

    FragmentManager fragmentManager = mainActivity.getFragmentManager();

    assertThat(fragmentManager.findFragmentById(R.id.container), notNullValue());
  }

  @Test public void initialize_user_story() {
    MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);

    verify(mockUserStory).initialize();
  }
}