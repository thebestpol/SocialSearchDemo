package com.socialsearch.history;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.socialsearch.R;
import com.socialsearch.history.presenter.HistoryPresenter;
import com.socialsearch.history.view.adapter.HistoryDataAdapter;
import com.socialsearch.main.DemoUserStory;
import com.socialsearch.main.MainActivity;
import com.socialsearch.rules.RobolectricMockComponentRule;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
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
 * com.socialsearch.history
 * HistoryFragmentShould
 */
@RunWith(RobolectricTestRunner.class) public class HistoryFragmentShould {

  @Rule public RobolectricMockComponentRule rule = new RobolectricMockComponentRule();

  @Spy DemoUserStory mockUserStory = new DemoUserStory();

  @Mock HistoryPresenter mockPresenter;

  @Mock HistoryDataAdapter mockHistoryDataAdapter;

  private HistoryFragment historyFragment;

  @Before public void setUp() {
    // Disabling user story navigation
    doNothing().when(mockUserStory).start();

    historyFragment = new HistoryFragment();
    SupportFragmentTestUtil.startVisibleFragment(historyFragment, MainActivity.class,
        R.id.container);
  }

  @Test public void contain_one_menu_item() {
    Menu optionsMenu = shadowOf(historyFragment.getActivity()).getOptionsMenu();

    assertThat(optionsMenu, notNullValue());
    assertThat(optionsMenu.size(), is(equalTo(1)));
  }

  @Test public void contain_search_menu_item() {
    Menu optionsMenu = shadowOf(historyFragment.getActivity()).getOptionsMenu();
    MenuItem searchItem = optionsMenu.findItem(R.id.search);

    assertThat(searchItem, notNullValue());
    assertThat(searchItem.getTitle().toString(), is(equalTo("Search")));
  }

  @Test public void bind_lifecycle_start_to_presenter() {
    // Disabling user story behaviour to enable fragment isolated test
    verify(mockPresenter).setView(eq(historyFragment));
    verify(mockPresenter).start();
  }

  @Test public void bind_lifecycle_end_to_presenter() {
    // Disabling user story behaviour to enable fragment isolated test
    historyFragment.onStop();

    verify(mockPresenter).stop();
  }

  @Test public void start_fragment_view() {
    View fragmentView = historyFragment.getView();

    assertThat(fragmentView, notNullValue());
  }

  @Test public void contain_recycler_view() {
    View fragmentView = historyFragment.getView();

    assertThat(fragmentView.findViewById(R.id.recyclerView), notNullValue());
  }

  @Test public void contain_feedback_view() {
    View feedbackTextView = historyFragment.getView().findViewById(R.id.feedbackTextView);

    assertThat(feedbackTextView, notNullValue());
    assertThat(feedbackTextView.getVisibility(), is(equalTo(View.GONE)));
  }

  @Test public void show_feedback_on_method_call_and_modify_view_visibility() {
    View fragmentView = historyFragment.getView();

    historyFragment.showFeedbackMessage("Fake feedback message");

    TextView feedbackTextView = (TextView) fragmentView.findViewById(R.id.feedbackTextView);
    assertThat(feedbackTextView.getVisibility(), is(equalTo(View.VISIBLE)));
    assertThat(feedbackTextView.getText().toString(), is(equalTo("Fake feedback message")));
    assertThat(fragmentView.findViewById(R.id.recyclerView).getVisibility(),
        is(equalTo(View.GONE)));
  }

  @Test public void show_recyclerview_on_method_call_and_modify_view_visibility() {
    View fragmentView = historyFragment.getView();

    historyFragment.loadHistoryData(new ArrayList<>());

    TextView feedbackTextView = (TextView) fragmentView.findViewById(R.id.feedbackTextView);
    assertThat(feedbackTextView.getVisibility(), is(equalTo(View.GONE)));
    assertThat(fragmentView.findViewById(R.id.recyclerView).getVisibility(),
        is(equalTo(View.VISIBLE)));
    verify(mockHistoryDataAdapter).setItems(Mockito.anyList());
  }
}