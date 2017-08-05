package com.socialsearch.search;

import android.view.Menu;
import android.view.MenuItem;
import com.socialsearch.R;
import com.socialsearch.main.DemoUserStory;
import com.socialsearch.main.MainActivity;
import com.socialsearch.rules.RobolectricMockComponentRule;
import com.socialsearch.search.presenter.SearchPresenter;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.robolectric.Shadows.shadowOf;

/**
 * SocialSearchDemo
 * com.socialsearch.search
 * SearchFragmentShould
 */
@RunWith(RobolectricTestRunner.class) public class SearchFragmentShould {

  @Rule public RobolectricMockComponentRule rule = new RobolectricMockComponentRule();

  @Mock DemoUserStory mockUsetStory;

  @Mock SearchPresenter presenter;

  @Test public void contain_two_menu_item() {
    MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);

    Menu optionsMenu = shadowOf(mainActivity).getOptionsMenu();
    assertThat(optionsMenu, notNullValue());
    assertThat(optionsMenu.size(), is(equalTo(2)));
  }

  @Test public void contain_search_and_history_menu_item() {
    MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);

    Menu optionsMenu = shadowOf(mainActivity).getOptionsMenu();
    MenuItem searchItem = optionsMenu.findItem(R.id.search);
    MenuItem historyItem = optionsMenu.findItem(R.id.history);

    assertThat(searchItem, notNullValue());
    assertThat(searchItem.getTitle().toString(), is(equalTo("Search")));
    assertThat(historyItem, notNullValue());
    assertThat(historyItem.getTitle().toString(), is(equalTo("History")));
  }

  @Test public void bind_lifecycle_start_to_presenter() {
    // Disabling user story behaviour to enable fragment isolated test
    doNothing().when(mockUsetStory).start();

    SearchFragment fragment = new SearchFragment();
    SupportFragmentTestUtil.startVisibleFragment(fragment, MainActivity.class, R.id.container);

    verify(presenter).setView(eq(fragment));
    verify(presenter).start();
  }

  @Test public void bind_lifecycle_end_to_presenter() {
    // Disabling user story behaviour to enable fragment isolated test
    doNothing().when(mockUsetStory).start();

    SearchFragment fragment = new SearchFragment();
    SupportFragmentTestUtil.startVisibleFragment(fragment, MainActivity.class, R.id.container);

    fragment.onStop();
    verify(presenter).stop();
  }
}