package com.socialsearch.history.presenter;

import com.socialsearch.core.model.Callback;
import com.socialsearch.entity.HistoryData;
import com.socialsearch.history.model.HistoryModel;
import com.socialsearch.history.view.HistoryView;
import com.socialsearch.main.DemoUserStory;
import com.socialsearch.main.state.DemoStoryState;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * SocialSearchDemo
 * com.socialsearch.history.presenter
 * HistoryPresenterShould
 */
public class HistoryPresenterShould {

  @Mock HistoryModel mockHistoryModel;
  @Mock HistoryView mockView;
  @Mock DemoUserStory mockUserStory;
  @Mock DemoStoryState mockState;

  private HistoryPresenter historyPresenter;

  @Before public void setUp() {
    MockitoAnnotations.initMocks(this);
    when(mockUserStory.getStoryState()).thenReturn(mockState);
    historyPresenter = new HistoryPresenter(mockHistoryModel, mockUserStory);
    historyPresenter.setView(mockView);
  }

  @Test public void obtain_history_on_start_from_model() {
    historyPresenter.start();

    verify(mockHistoryModel).obtainHistoryData(any(Callback.class));
  }

  @Test public void load_model_response_to_view() {
    doAnswer(invocation -> {
      ((Callback<List<HistoryData>>) invocation.getArguments()[0]).onSuccess(
          Arrays.asList(mock(HistoryData.class)));
      return null;
    }).when(mockHistoryModel).obtainHistoryData(any(Callback.class));

    historyPresenter.start();

    verify(mockView).loadHistoryData(Mockito.anyListOf(HistoryData.class));
  }

  @Test public void load_empty_feedback_on_model_response() {
    doAnswer(invocation -> {
      ((Callback<List<HistoryData>>) invocation.getArguments()[0]).onSuccess(new ArrayList<>());
      return null;
    }).when(mockHistoryModel).obtainHistoryData(any(Callback.class));

    historyPresenter.start();

    verify(mockView, never()).loadHistoryData(Mockito.anyListOf(HistoryData.class));
    verify(mockView).showFeedbackMessage("No search history found.");
  }

  @Test public void load_error_feedback_on_model_error_response() {
    doAnswer(invocation -> {
      ((Callback<List<HistoryData>>) invocation.getArguments()[0]).onError("Fake error feedback");
      return null;
    }).when(mockHistoryModel).obtainHistoryData(any(Callback.class));

    historyPresenter.start();

    verify(mockView, never()).loadHistoryData(Mockito.anyListOf(HistoryData.class));
    verify(mockView).showFeedbackMessage("Fake error feedback");
  }

  @Test public void update_story_state_and_trigger_navigation() {
    historyPresenter.onQuerySubmitted("Fake query");

    verify(mockState).setQuery(eq("Fake query"));
    verify(mockState).clearFeedbackMessage();
    verify(mockState).clearQueryResponse();

    verify(mockUserStory).navigateToSearch();
  }
}