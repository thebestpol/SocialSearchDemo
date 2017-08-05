package com.socialsearch.search.presenter;

import com.socialsearch.main.DemoUserStory;
import com.socialsearch.main.state.DemoStoryState;
import com.socialsearch.search.view.SearchView;

/**
 * SocialSearchDemo
 * com.socialsearch.search.presenter
 * SearchPresenter
 */

public class SearchPresenter {

  private DemoUserStory demoUserStory;
  private DemoStoryState storyState;
  private SearchView view;

  public SearchPresenter(DemoUserStory demoUserStory) {
    this.demoUserStory = demoUserStory;
  }

  public void setView(SearchView view) {
    this.view = view;
  }

  public void start() {
    storyState = demoUserStory.getStoryState();
    String query = storyState.getQuery();
    String errorMessage = storyState.getErrorMessage();
    if (errorMessage != null) {
      view.showFeedbackMessage(errorMessage);
    } else if (query == null || query.isEmpty()) {
      view.showFeedbackMessage("Click on Search menu item to make a social search.");
    }
  }

  public void stop() {

  }
}
