package com.socialsearch.search.presenter;

import com.socialsearch.main.DemoUserStory;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
}