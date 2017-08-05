package com.socialsearch.history.presenter;

import com.socialsearch.core.model.Callback;
import com.socialsearch.entity.HistoryData;
import com.socialsearch.history.model.HistoryModel;
import com.socialsearch.history.view.HistoryView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * SocialSearchDemo
 * com.socialsearch.history.presenter
 * HistoryPresenterShould
 */
public class HistoryPresenterShould {

  @Mock HistoryModel mockHistoryModel;
  @Mock HistoryView mockView;

  private HistoryPresenter historyPresenter;

  @Before public void setUp() {
    MockitoAnnotations.initMocks(this);
    historyPresenter = new HistoryPresenter(mockHistoryModel);
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
}