package com.socialsearch.search.presenter;

import com.socialsearch.core.model.Callback;
import com.socialsearch.entity.SocialData;
import com.socialsearch.main.DemoUserStory;
import com.socialsearch.main.state.DemoStoryState;
import com.socialsearch.search.model.SearchModel;
import com.socialsearch.search.view.SocialSearchView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * SocialSearchDemo
 * com.socialsearch.search.presenter
 * SearchPresenterShould
 */
public class SearchPresenterShould {

  @Mock SocialSearchView mockView;
  @Mock DemoUserStory mockDemoUserStory;
  @Mock DemoStoryState mockStoryState;
  @Mock SearchModel mockSearchModel;

  private SearchPresenter searchPresenter;

  @Before public void setUp() {
    MockitoAnnotations.initMocks(this);

    when(mockDemoUserStory.getStoryState()).thenReturn(mockStoryState);

    searchPresenter = new SearchPresenter(mockDemoUserStory, mockSearchModel);
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

  @Test public void show_progress_if_state_has_query_and_make_the_query() {
    when(mockStoryState.getQuery()).thenReturn("Fake query");

    searchPresenter.start();

    verify(mockView).showProgress("Searching Fake query in social media...");
    verify(mockSearchModel).obtainSocialData(eq("Fake query"), any(Callback.class));
  }

  @Test public void delegate_error_from_model_to_view() {
    doAnswer(invocation -> {
      ((Callback) invocation.getArguments()[1]).onError("Fake model error message");
      return null;
    }).when(mockSearchModel).obtainSocialData(Mockito.anyString(), any(Callback.class));
    when(mockStoryState.getQuery()).thenReturn("Fake query");

    searchPresenter.start();

    verify(mockView).showFeedbackMessage("Fake model error message");
  }

  @Test public void show_empty_message_on_empty_model_response() {
    doAnswer(invocation -> {
      ((Callback<List<SocialData>>) invocation.getArguments()[1]).onSuccess(new ArrayList<>());
      return null;
    }).when(mockSearchModel).obtainSocialData(Mockito.anyString(), any(Callback.class));
    when(mockStoryState.getQuery()).thenReturn("Fake query");

    searchPresenter.start();

    verify(mockView).showFeedbackMessage("Any results found.");
  }

  @Test public void load_model_response() {
    doAnswer(invocation -> {
      ((Callback<List<SocialData>>) invocation.getArguments()[1]).onSuccess(
          Arrays.asList(mock(SocialData.class)));
      return null;
    }).when(mockSearchModel).obtainSocialData(Mockito.anyString(), any(Callback.class));
    when(mockStoryState.getQuery()).thenReturn("Fake query");

    searchPresenter.start();

    verify(mockView).loadSocialData(Mockito.anyList());
  }

  @Test public void delegate_query_to_model_on_trigger() {
    searchPresenter.onQuerySubmitted("Fake query submitted");

    verify(mockView).showProgress("Searching Fake query submitted in social media...");
    verify(mockSearchModel).obtainSocialData(eq("Fake query submitted"), any(Callback.class));
  }
}