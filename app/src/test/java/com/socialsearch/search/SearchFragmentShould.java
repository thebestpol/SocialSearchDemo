package com.socialsearch.search;

import android.view.Menu;
import android.view.MenuItem;
import com.socialsearch.R;
import com.socialsearch.main.MainActivity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.robolectric.Shadows.shadowOf;

/**
 * SocialSearchDemo
 * com.socialsearch.search
 * SearchFragmentShould
 */
@RunWith(RobolectricTestRunner.class) public class SearchFragmentShould {

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
}