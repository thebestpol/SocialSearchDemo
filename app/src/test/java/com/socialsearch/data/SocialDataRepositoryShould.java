package com.socialsearch.data;

import com.socialsearch.data.mapper.SocialDataMapper;
import com.socialsearch.data.plus.dto.PlusUserDto;
import com.socialsearch.data.tweet.dto.TweetDto;
import com.socialsearch.entity.SocialData;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * SocialSearchDemo
 * com.socialsearch.data
 * SocialDataRepositoryShould
 */
public class SocialDataRepositoryShould {

  @Mock DataSource<TweetDto> tweetDataSource;
  @Mock DataSource<PlusUserDto> plusDataSource;
  @Spy SocialDataMapper socialDataMapper;
  private SocialDataRepository repository;

  @Before public void setUp() {
    MockitoAnnotations.initMocks(this);
    repository = new SocialDataRepository(tweetDataSource, plusDataSource, socialDataMapper);
  }

  @Test public void get_tweets_from_data_source_and_map_to_social_data() {
    when(tweetDataSource.getData(anyString())).thenReturn(Observable.empty());

    repository.getTweetsSocialData("Fake query");

    verify(tweetDataSource).getData("Fake query");
  }

  @Test public void get_plus_users_from_data_source_and_map_to_social_data() {
    when(plusDataSource.getData(anyString())).thenReturn(Observable.empty());

    repository.getPlusSocialData("Fake query");

    verify(plusDataSource).getData("Fake query");
  }

  @Test public void handles_error_from_tweet_data_source() {
    when(tweetDataSource.getData(anyString())).thenReturn(Observable.error(new Exception()));

    TestSubscriber<SocialData> subscriber = TestSubscriber.create();
    repository.getTweetsSocialData("any").subscribe(subscriber);

    subscriber.awaitTerminalEvent();

    subscriber.assertNoErrors();
    subscriber.assertCompleted();
    subscriber.assertValueCount(0);
  }

  @Test public void handles_error_from_plus_data_source() {
    when(plusDataSource.getData(anyString())).thenReturn(Observable.error(new Exception()));

    TestSubscriber<SocialData> subscriber = TestSubscriber.create();
    repository.getPlusSocialData("any").subscribe(subscriber);

    subscriber.awaitTerminalEvent();

    subscriber.assertNoErrors();
    subscriber.assertCompleted();
    subscriber.assertValueCount(0);
  }

  @Test public void map_response_from_tweet_data_source() {
    TweetDto tweetDto = new TweetDto("fakeTweetImage");
    when(tweetDataSource.getData(anyString())).thenReturn(Observable.just(tweetDto));

    TestSubscriber<SocialData> subscriber = TestSubscriber.create();
    repository.getTweetsSocialData("any").subscribe(subscriber);

    subscriber.awaitTerminalEvent();

    subscriber.assertNoErrors();
    subscriber.assertCompleted();
    subscriber.assertValueCount(1);
    assertThat(subscriber.getOnNextEvents().get(0),
        hasProperty("imageUrl", is(equalTo("fakeTweetImage"))));
  }

  @Test public void map_response_from_plus_data_source() {
    PlusUserDto userDto = new PlusUserDto();
    userDto.setProfileImage("fakeProfileImage");
    when(plusDataSource.getData(anyString())).thenReturn(Observable.just(userDto));

    TestSubscriber<SocialData> subscriber = TestSubscriber.create();
    repository.getPlusSocialData("any").subscribe(subscriber);

    subscriber.awaitTerminalEvent();

    subscriber.assertCompleted();
    subscriber.assertValueCount(1);
    assertThat(subscriber.getOnNextEvents().get(0),
        hasProperty("imageUrl", is(equalTo("fakeProfileImage"))));
  }
}