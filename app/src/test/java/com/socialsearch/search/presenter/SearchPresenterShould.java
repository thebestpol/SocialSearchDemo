package com.socialsearch.search.presenter;

import com.socialsearch.main.DemoUserStory;
import com.socialsearch.main.state.DemoStoryState;
import com.socialsearch.search.view.SearchView;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * SocialSearchDemo
 * com.socialsearch.search.presenter
 * SearchPresenterShould
 */
public class SearchPresenterShould {

  @Test public void obtain_story_state_on_start() {
    DemoUserStory mockDemoUserStory = mock(DemoUserStory.class);
    SearchPresenter searchPresenter = new SearchPresenter(mockDemoUserStory);

    searchPresenter.start();

    verify(mockDemoUserStory).getStoryState();
  }

  @Test public void show_hint_feedback_if_query_is_empty() {
    DemoStoryState mockStoryState = mock(DemoStoryState.class);
    when(mockStoryState.getQuery()).thenReturn("");

    DemoUserStory mockDemoUserStory = mock(DemoUserStory.class);
    when(mockDemoUserStory.getStoryState()).thenReturn(mockStoryState);
    SearchPresenter searchPresenter = new SearchPresenter(mockDemoUserStory);
    SearchView mockView = mock(SearchView.class);
    searchPresenter.setView(mockView);

    searchPresenter.start();

    verify(mockView).showFeedbackMessage("Click on menu item to make a social search");
  }
}