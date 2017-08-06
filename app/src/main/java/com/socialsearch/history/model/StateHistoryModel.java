package com.socialsearch.history.model;

import com.socialsearch.core.model.Callback;
import com.socialsearch.entity.HistoryData;
import com.socialsearch.main.DemoUserStory;
import com.socialsearch.main.state.DemoStoryState;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * SocialSearchDemo
 * com.socialsearch.history.model
 * StateHistoryModel
 */

public class StateHistoryModel implements HistoryModel {

  private final DemoStoryState storyState;

  @Inject public StateHistoryModel(DemoUserStory demoUserStory) {
    storyState = demoUserStory.getStoryState();
  }

  @Override public void obtainHistoryData(Callback<List<HistoryData>> callback) {
    List<HistoryData> history = storyState.getHistory();
    if (history == null) {
      callback.onSuccess(new ArrayList<>());
    } else {
      callback.onSuccess(history);
    }
  }
}
