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

  public SocialDataRepository(DataSource<TweetDto> tweetDataSource) {
  }

  public Observable<SocialData> getTweetsSocialData(String query) {
    return null;
  }

  public Observable<SocialData> getPlusSocialData(String query) {
    return null;
  }
}
