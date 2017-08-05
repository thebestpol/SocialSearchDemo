package com.socialsearch.search.presenter;

import com.socialsearch.main.DemoUserStory;
import com.socialsearch.main.state.DemoStoryState;
import com.socialsearch.search.view.SearchView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * SocialSearchDemo
 * com.socialsearch.search.presenter
 * SearchPresenterShould
 */
public class SearchPresenterShould {

  @Mock SearchView mockView;
  @Mock DemoUserStory mockDemoUserStory;
  @Mock DemoStoryState mockStoryState;

  private SearchPresenter searchPresenter;

  @Before public void setUp() {
    MockitoAnnotations.initMocks(this);

    when(mockDemoUserStory.getStoryState()).thenReturn(mockStoryState);

    searchPresenter = new SearchPresenter(mockDemoUserStory);
    searchPresenter.setView(mockView);
  }

  @Test public void obtain_story_state_on_start() {
    searchPresenter.start();

    verify(mockDemoUserStory).getStoryState();
  }

  @Test public void show_hint_feedback_if_query_is_empty() {
    when(mockStoryState.getQuery()).thenReturn("");

    searchPresenter.start();

    verify(mockView).showFeedbackMessage("Click on Search menu item to make a social search.");
  }

  @Test public void show_error_message_if_state_has_error() {
    when(mockStoryState.getErrorMessage()).thenReturn("Fake error message");

    searchPresenter.start();

    verify(mockView).showFeedbackMessage("Fake error message");
  }
}