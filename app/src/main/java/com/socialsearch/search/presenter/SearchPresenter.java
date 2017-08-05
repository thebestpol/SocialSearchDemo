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

  public SearchPresenter(DemoUserStory demoUserStory) {
    this.demoUserStory = demoUserStory;
  }

  public void setView(SearchView view) {
  }

  public void start() {
    storyState = demoUserStory.getStoryState();
  }

  public void stop() {

  }
}
