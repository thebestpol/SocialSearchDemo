package com.socialsearch.search;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.socialsearch.R;
import com.socialsearch.main.DemoUserStory;
import com.socialsearch.main.MainActivity;
import com.socialsearch.rules.RobolectricMockComponentRule;
import com.socialsearch.search.presenter.SearchPresenter;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
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

  @Spy DemoUserStory mockUsetStory = new DemoUserStory();

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

  @Test public void start_fragment_view() {
    SearchFragment fragment = new SearchFragment();
    SupportFragmentTestUtil.startVisibleFragment(fragment, MainActivity.class, R.id.container);

    assertThat(fragment.getView(), notNullValue());
  }

  @Test public void contain_recycler_view() {
    SearchFragment fragment = new SearchFragment();
    SupportFragmentTestUtil.startVisibleFragment(fragment, MainActivity.class, R.id.container);

    assertThat(fragment.getView().findViewById(R.id.recyclerView), notNullValue());
  }

  @Test public void contain_feedback_view() {
    SearchFragment fragment = new SearchFragment();
    SupportFragmentTestUtil.startVisibleFragment(fragment, MainActivity.class, R.id.container);

    View feedbackTextView = fragment.getView().findViewById(R.id.feedbackTextView);
    assertThat(feedbackTextView, notNullValue());
    assertThat(feedbackTextView.getVisibility(), is(equalTo(View.GONE)));
  }

  @Test public void contain_progress_view() {
    SearchFragment fragment = new SearchFragment();
    SupportFragmentTestUtil.startVisibleFragment(fragment, MainActivity.class, R.id.container);

    View progressView = fragment.getView().findViewById(R.id.progress);
    assertThat(progressView, notNullValue());
    assertThat(progressView.getVisibility(), is(equalTo(View.GONE)));
  }

  @Test public void show_feedback_on_method_call_and_modify_view_visibility() {
    SearchFragment fragment = new SearchFragment();
    SupportFragmentTestUtil.startVisibleFragment(fragment, MainActivity.class, R.id.container);

    fragment.showFeedbackMessage("Fake feedback message");

    TextView feedbackTextView = (TextView) fragment.getView().findViewById(R.id.feedbackTextView);
    assertThat(feedbackTextView.getVisibility(), is(equalTo(View.VISIBLE)));
    assertThat(feedbackTextView.getText().toString(), is(equalTo("Fake feedback message")));
    // Updates view visibility
    assertThat(fragment.getView().findViewById(R.id.progress).getVisibility(),
        is(equalTo(View.GONE)));
    assertThat(fragment.getView().findViewById(R.id.recyclerView).getVisibility(),
        is(equalTo(View.GONE)));
  }

  @Test public void show_progress_view_message_and_mofify_view_visibility() {
    SearchFragment fragment = new SearchFragment();
    SupportFragmentTestUtil.startVisibleFragment(fragment, MainActivity.class, R.id.container);

    fragment.showProgress("Fake progress message");

    View progressView = fragment.getView().findViewById(R.id.progress);
    TextView progressTextView = (TextView) progressView.findViewById(R.id.progressTextView);

    assertThat(progressView.getVisibility(), is(equalTo(View.VISIBLE)));
    assertThat(progressTextView.getVisibility(), is(equalTo(View.VISIBLE)));
    assertThat(progressTextView.getText().toString(), is(equalTo("Fake feedback message")));
    // Updates view visibility
    assertThat(fragment.getView().findViewById(R.id.feedbackTextView).getVisibility(),
        is(equalTo(View.GONE)));
    assertThat(fragment.getView().findViewById(R.id.recyclerView).getVisibility(),
        is(equalTo(View.GONE)));
  }
}