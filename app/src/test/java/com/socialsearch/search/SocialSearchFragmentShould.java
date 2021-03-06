package com.socialsearch.search;

import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.socialsearch.R;
import com.socialsearch.entity.SocialData;
import com.socialsearch.main.DemoUserStory;
import com.socialsearch.main.MainActivity;
import com.socialsearch.rules.RobolectricMockComponentRule;
import com.socialsearch.search.presenter.SearchPresenter;
import java.util.Arrays;
import java.util.List;
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
 * SocialSearchFragmentShould
 */
@RunWith(RobolectricTestRunner.class) public class SocialSearchFragmentShould {

  @Rule public RobolectricMockComponentRule rule = new RobolectricMockComponentRule();

  @Spy DemoUserStory mockUserStory = new DemoUserStory();

  @Mock SearchPresenter mockPresenter;

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
    doNothing().when(mockUserStory).start();

    SocialSearchFragment fragment = new SocialSearchFragment();
    SupportFragmentTestUtil.startVisibleFragment(fragment, MainActivity.class, R.id.container);

    verify(mockPresenter).setView(eq(fragment));
    verify(mockPresenter).start();
  }

  @Test public void bind_lifecycle_end_to_presenter() {
    // Disabling user story behaviour to enable fragment isolated test
    doNothing().when(mockUserStory).start();

    SocialSearchFragment fragment = new SocialSearchFragment();
    SupportFragmentTestUtil.startVisibleFragment(fragment, MainActivity.class, R.id.container);

    fragment.onStop();

    verify(mockPresenter).stop();
  }

  @Test public void start_fragment_view() {
    SocialSearchFragment fragment = new SocialSearchFragment();
    SupportFragmentTestUtil.startVisibleFragment(fragment, MainActivity.class, R.id.container);

    assertThat(fragment.getView(), notNullValue());
  }

  @Test public void contain_recycler_view() {
    SocialSearchFragment fragment = new SocialSearchFragment();
    SupportFragmentTestUtil.startVisibleFragment(fragment, MainActivity.class, R.id.container);

    assertThat(fragment.getView().findViewById(R.id.recyclerView), notNullValue());
  }

  @Test public void contain_feedback_view() {
    SocialSearchFragment fragment = new SocialSearchFragment();
    SupportFragmentTestUtil.startVisibleFragment(fragment, MainActivity.class, R.id.container);

    View feedbackTextView = fragment.getView().findViewById(R.id.feedbackTextView);
    assertThat(feedbackTextView, notNullValue());
    assertThat(feedbackTextView.getVisibility(), is(equalTo(View.GONE)));
  }

  @Test public void contain_progress_view() {
    SocialSearchFragment fragment = new SocialSearchFragment();
    SupportFragmentTestUtil.startVisibleFragment(fragment, MainActivity.class, R.id.container);

    View progressView = fragment.getView().findViewById(R.id.progress);
    assertThat(progressView, notNullValue());
    //assertThat(progressView.getVisibility(), is(equalTo(View.GONE)));
  }

  @Test public void show_feedback_on_method_call_and_modify_view_visibility() {
    SocialSearchFragment fragment = new SocialSearchFragment();
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

  @Test public void show_progress_view_message_and_modify_view_visibility() {
    SocialSearchFragment fragment = new SocialSearchFragment();
    SupportFragmentTestUtil.startVisibleFragment(fragment, MainActivity.class, R.id.container);

    fragment.showProgress("Fake progress message");

    View progressView = fragment.getView().findViewById(R.id.progress);
    TextView progressTextView = (TextView) progressView.findViewById(R.id.progressTextView);

    assertThat(progressView.getVisibility(), is(equalTo(View.VISIBLE)));
    assertThat(progressTextView.getVisibility(), is(equalTo(View.VISIBLE)));
    assertThat(progressTextView.getText().toString(), is(equalTo("Fake progress message")));
    // Updates view visibility
    assertThat(fragment.getView().findViewById(R.id.feedbackTextView).getVisibility(),
        is(equalTo(View.GONE)));
    assertThat(fragment.getView().findViewById(R.id.recyclerView).getVisibility(),
        is(equalTo(View.GONE)));
  }

  @Test public void show_recycler_view_with_social_data() {
    List<SocialData> fakeSocialData = Arrays.asList(new SocialData("fakeUrl"));
    SocialSearchFragment fragment = new SocialSearchFragment();
    SupportFragmentTestUtil.startVisibleFragment(fragment, MainActivity.class, R.id.container);

    fragment.loadSocialData(fakeSocialData);

    View progressView = fragment.getView().findViewById(R.id.progress);
    RecyclerView recyclerView = (RecyclerView) fragment.getView().findViewById(R.id.recyclerView);

    assertThat(progressView.getVisibility(), is(equalTo(View.GONE)));
    assertThat(recyclerView.getVisibility(), is(equalTo(View.VISIBLE)));
    assertThat(recyclerView.getAdapter().getItemCount(), is(equalTo(1)));
  }

  @Test public void on_history_menu_item_clicked_navigates_to_history() {
    SocialSearchFragment fragment = new SocialSearchFragment();
    SupportFragmentTestUtil.startVisibleFragment(fragment, MainActivity.class, R.id.container);

    Menu optionsMenu = shadowOf(fragment.getActivity()).getOptionsMenu();
    MenuItem historyItem = optionsMenu.findItem(R.id.history);
    fragment.onOptionsItemSelected(historyItem);

    verify(mockPresenter).onHistoryItemSelected();
  }
}