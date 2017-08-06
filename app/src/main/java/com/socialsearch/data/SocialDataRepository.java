package com.socialsearch.data;

import com.socialsearch.data.tweet.dto.TweetDto;
import com.socialsearch.entity.SocialData;
import rx.Observable;

/**
 * SocialSearchDemo
 * com.socialsearch.data
 * SocialDataRepository
 */

public class SocialDataRepository {

  private final DataSource<TweetDto> tweetDataSource;

  public SocialDataRepository(DataSource<TweetDto> tweetDataSource) {
    this.tweetDataSource = tweetDataSource;
  }

  public Observable<SocialData> getTweetsSocialData(String query) {
    return tweetDataSource.getData(query).cast(SocialData.class);
  }

  public Observable<SocialData> getPlusSocialData(String query) {
    return null;
  }
}
