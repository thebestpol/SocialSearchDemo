package com.socialsearch.data;

import com.socialsearch.data.mapper.SocialDataMapper;
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
  private final SocialDataMapper socialDataMapper;

  public SocialDataRepository(DataSource<TweetDto> tweetDataSource,
      DataSource<PlusUserDto> plusDataSource, SocialDataMapper socialDataMapper) {
    this.tweetDataSource = tweetDataSource;
    this.plusDataSource = plusDataSource;
    this.socialDataMapper = socialDataMapper;
  }

  public Observable<SocialData> getTweetsSocialData(String query) {
    return tweetDataSource.getData(query)
        .map(socialDataMapper::tweetToSocialData)
        .onErrorResumeNext(Observable.empty());
  }

  public Observable<SocialData> getPlusSocialData(String query) {
    return plusDataSource.getData(query)
        .map(socialDataMapper::plusUserToData)
        .onErrorResumeNext(Observable.empty());
  }
}
