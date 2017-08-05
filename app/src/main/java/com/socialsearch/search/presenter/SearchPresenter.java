package com.socialsearch.search.presenter;

import com.socialsearch.core.model.Callback;
import com.socialsearch.entity.SocialData;
import com.socialsearch.main.DemoUserStory;
import com.socialsearch.main.state.DemoStoryState;
import com.socialsearch.search.model.SearchModel;
import com.socialsearch.search.view.SocialSearchView;
import java.util.List;

/**
 * SocialSearchDemo
 * com.socialsearch.search.presenter
 * SearchPresenter
 */

public class SearchPresenter {

  private final SearchModel searchModel;
  private final DemoUserStory demoUserStory;
  private DemoStoryState storyState;
  private SocialSearchView view;

  public SearchPresenter(DemoUserStory demoUserStory, SearchModel searchModel) {
    this.demoUserStory = demoUserStory;
    this.searchModel = searchModel;
  }

  public void setView(SocialSearchView view) {
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
    } else {
      searchQueary(query);
    }
  }

  private void searchQueary(String query) {
    storyState.setQuery(query);
    view.showProgress("Searching " + query + " in social media...");
    searchModel.obtainSocialData(query, new Callback<List<SocialData>>() {
      @Override public void onSuccess(List<SocialData> response) {
        if (response.isEmpty()) {
          onFeedbackEvent("Any results found.");
        } else {
          storyState.setQueryResponse(response);
          view.loadSocialData(response);
        }
      }

      @Override public void onError(String errorMessage) {
        onFeedbackEvent(errorMessage);
      }
    });
  }

  private void onFeedbackEvent(String errorMessage) {
    storyState.setFeedbackMessage(errorMessage);
    storyState.clearSocialData();
    storyState.clearQuery();
    view.showFeedbackMessage(errorMessage);
  }

  public void stop() {

  }

  public void onQuerySubmitted(String query) {
    searchQueary(query);
  }
}
