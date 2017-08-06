package com.socialsearch.data;

import com.socialsearch.data.plus.dto.PlusUserDto;
import com.socialsearch.data.tweet.dto.TweetDto;
import org.junit.Test;
import rx.Observable;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * SocialSearchDemo
 * com.socialsearch.data
 * SocialDataRepositoryShould
 */
public class SocialDataRepositoryShould {

  @Test public void get_tweets_from_data_source_and_map_to_social_data() {
    DataSource<TweetDto> tweetDataSource = mock(DataSource.class);
    DataSource<PlusUserDto> plusDataSource = mock(DataSource.class);
    when(tweetDataSource.getData(anyString())).thenReturn(Observable.empty());
    SocialDataRepository repository = new SocialDataRepository(tweetDataSource, plusDataSource);

    repository.getTweetsSocialData("Fake query");

    verify(tweetDataSource).getData("Fake query");
  }

  @Test public void get_plus_users_from_data_source_and_map_to_social_data() {
    DataSource<TweetDto> tweetDataSource = mock(DataSource.class);
    DataSource<PlusUserDto> plusDataSource = mock(DataSource.class);
    when(plusDataSource.getData(anyString())).thenReturn(Observable.empty());
    SocialDataRepository repository = new SocialDataRepository(tweetDataSource, plusDataSource);

    repository.getPlusSocialData("Fake query");

    verify(plusDataSource).getData("Fake query");
  }
}