package com.socialsearch.history.presenter;

import com.socialsearch.core.model.Callback;
import com.socialsearch.core.presenter.Presenter;
import com.socialsearch.entity.HistoryData;
import com.socialsearch.history.model.HistoryModel;
import com.socialsearch.history.view.HistoryView;
import java.util.List;

/**
 * SocialSearchDemo
 * com.socialsearch.history.presenter
 * HistoryPresenter
 */

public class HistoryPresenter extends Presenter<HistoryView> {

  private final HistoryModel model;

  public HistoryPresenter(HistoryModel model) {
    this.model = model;
  }

  @Override public void start() {
    model.obtainHistoryData(new Callback<List<HistoryData>>() {
      @Override public void onSuccess(List<HistoryData> response) {
        if (response.isEmpty()) {
          view.showFeedbackMessage("No search history found.");
        } else {
          view.loadHistoryData(response);
        }
      }

      @Override public void onError(String errorMessage) {
        view.showFeedbackMessage(errorMessage);
      }
    });
  }

  @Override public void stop() {

  }
}
