package com.socialsearch.data;

import com.socialsearch.data.plus.dto.PlusUserDto;
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
  private final DataSource<PlusUserDto> plusDataSource;

  public SocialDataRepository(DataSource<TweetDto> tweetDataSource,
      DataSource<PlusUserDto> plusDataSource) {
    this.tweetDataSource = tweetDataSource;
    this.plusDataSource = plusDataSource;
  }

  public Observable<SocialData> getTweetsSocialData(String query) {
    return tweetDataSource.getData(query)
        .cast(SocialData.class)
        .onErrorResumeNext(Observable.empty());
  }

  public Observable<SocialData> getPlusSocialData(String query) {
    return plusDataSource.getData(query)
        .cast(SocialData.class)
        .onErrorResumeNext(Observable.empty());
  }
}
