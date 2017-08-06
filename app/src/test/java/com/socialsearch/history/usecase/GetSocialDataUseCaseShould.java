package com.socialsearch.history.usecase;

import com.socialsearch.core.executor.MainThread;
import com.socialsearch.data.SocialDataRepository;
import com.socialsearch.data.TweetDataSource;
import com.socialsearch.data.plus.PlusUserDataSource;
import com.socialsearch.entity.SocialData;
import com.socialsearch.rules.ImmediateSchedulerTestRule;
import java.util.concurrent.Executors;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * SocialSearchDemo
 * com.socialsearch.history.usecase
 * GetSocialDataUseCaseShould
 */
public class GetSocialDataUseCaseShould {

  @Rule public ImmediateSchedulerTestRule testRule = new ImmediateSchedulerTestRule();

  private PlusUserDataSource plusDataSource;
  private TweetDataSource tweetDataSource;
  private GetSocialDataUseCase getSocialDataUseCase;
  private SocialDataRepository repository;

  @Before public void setUp() {
    repository = mock(SocialDataRepository.class);

    getSocialDataUseCase =
        new GetSocialDataUseCase(repository, Executors.newSingleThreadExecutor(), new MainThread());
  }

  @Test public void merge_data_source_into_subscription() {
    SocialData socialData = new SocialData("fake");
    when(repository.getTweetsSocialData(anyString())).thenReturn(
        Observable.just(socialData, socialData));
    when(repository.getPlusSocialData(anyString())).thenReturn(
        Observable.just(socialData, socialData, socialData));

    TestSubscriber<SocialData> subscriber = TestSubscriber.create();
    getSocialDataUseCase.execute("fake", subscriber);

    subscriber.awaitTerminalEvent();

    subscriber.assertCompleted();
    subscriber.assertValueCount(5);
  }
}