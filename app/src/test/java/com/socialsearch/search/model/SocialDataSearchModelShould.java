package com.socialsearch.search.model;

import com.socialsearch.core.model.Callback;
import com.socialsearch.entity.SocialData;
import com.socialsearch.history.usecase.GetSocialDataUseCase;
import org.junit.Test;
import rx.Subscriber;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * SocialSearchDemo
 * com.socialsearch.search.model
 * SocialDataSearchModelShould
 */
public class SocialDataSearchModelShould {

  @Test public void execute_use_case() {
    GetSocialDataUseCase mock = mock(GetSocialDataUseCase.class);
    SocialDataSearchModel socialDataSearchModel = new SocialDataSearchModel(mock);

    socialDataSearchModel.obtainSocialData("fake query", mock(Callback.class));

    verify(mock).execute(eq("fake query"), any(Subscriber.class));
  }

  @Test public void on_observable_error_calls_callback() {
    GetSocialDataUseCase mock = mock(GetSocialDataUseCase.class);
    doAnswer(invocation -> {
      ((Subscriber) invocation.getArguments()[1]).onError(new Exception());
      return null;
    }).when(mock).execute(anyString(), any(Subscriber.class));

    SocialDataSearchModel socialDataSearchModel = new SocialDataSearchModel(mock);

    Callback mockCallback = mock(Callback.class);
    socialDataSearchModel.obtainSocialData("fake query", mockCallback);

    verify(mockCallback).onError(anyString());
  }

  @Test public void on_observable_success_calls_callback() {
    GetSocialDataUseCase mock = mock(GetSocialDataUseCase.class);
    doAnswer(invocation -> {
      ((Subscriber) invocation.getArguments()[1]).onNext(new SocialData("fake"));
      ((Subscriber) invocation.getArguments()[1]).onCompleted();
      return null;
    }).when(mock).execute(anyString(), any(Subscriber.class));

    SocialDataSearchModel socialDataSearchModel = new SocialDataSearchModel(mock);

    Callback mockCallback = mock(Callback.class);
    socialDataSearchModel.obtainSocialData("fake query", mockCallback);

    verify(mockCallback).onSuccess(any());
  }
}