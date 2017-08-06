package com.socialsearch.data;

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
    when(tweetDataSource.getData(anyString())).thenReturn(Observable.empty());
    SocialDataRepository repository = new SocialDataRepository(tweetDataSource);

    repository.getTweetsSocialData("Fake query");

    verify(tweetDataSource).getData("Fake query");
  }
}