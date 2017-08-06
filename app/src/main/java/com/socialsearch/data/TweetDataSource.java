package com.socialsearch.data;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.socialsearch.data.tweet.dto.TweetDto;
import java.util.List;
import rx.Emitter;
import rx.Observable;
import twitter4j.MediaEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

/**
 * SocialSearchDemo
 * com.socialsearch.data
 * TweetDataSource
 */

public class TweetDataSource implements DataSource<TweetDto> {

  boolean cancelled;

  @Override public Observable<TweetDto> getData(String query) {
    return createTwitterObservable(query).filter(status -> status != null)
        .map(Status::getMediaEntities)
        .filter(mediaEntities -> mediaEntities != null && mediaEntities.length != 0)
        .flatMap(mediaEntities -> Observable.from(mediaEntities)
            .filter(mediaEntity -> mediaEntity.getType() != null && mediaEntity.getType()
                .equals("photo"))
            .map(MediaEntity::getMediaURL)
            .map(TweetDto::new));
  }

  @VisibleForTesting @NonNull public Observable<Status> createTwitterObservable(String query) {
    return Observable.create(emitter -> {
      cancelled = false;
      Twitter twitter = new TwitterFactory().getInstance();
      try {
        Query twitterQuery = new Query(query);
        QueryResult result;
        do {
          result = twitter.search(twitterQuery);
          List<Status> tweets = result.getTweets();
          for (Status tweet : tweets) {
            emitter.onNext(tweet);
          }
        } while ((twitterQuery = result.nextQuery()) != null && !cancelled);
      } catch (Exception e) {
        emitter.onError(e);
      }

      emitter.onCompleted();

      emitter.setCancellation(() -> cancelled = true);
    }, Emitter.BackpressureMode.BUFFER);
  }
}
