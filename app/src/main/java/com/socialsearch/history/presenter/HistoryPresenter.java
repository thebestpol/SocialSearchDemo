package com.socialsearch.history.presenter;

import com.socialsearch.core.model.Callback;
import com.socialsearch.core.presenter.Presenter;
import com.socialsearch.entity.HistoryData;
import com.socialsearch.history.model.HistoryModel;
import com.socialsearch.history.view.HistoryView;
import com.socialsearch.main.DemoUserStory;
import com.socialsearch.main.state.DemoStoryState;
import java.util.List;

/**
 * SocialSearchDemo
 * com.socialsearch.history.presenter
 * HistoryPresenter
 */

public class HistoryPresenter extends Presenter<HistoryView> {

  private final HistoryModel model;
  private final DemoStoryState storyState;
  private final DemoUserStory demoUserStory;

  public HistoryPresenter(HistoryModel model, DemoUserStory demoUserStory) {
    this.model = model;
    this.demoUserStory = demoUserStory;
    storyState = demoUserStory.getStoryState();
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
    demoUserStory.updateState(storyState);
  }

  public void onQuerySubmitted(String query) {
    storyState.setQuery(query);
    storyState.clearQueryResponse();
    storyState.clearFeedbackMessage();

    demoUserStory.navigateToSearch();
  }
}
