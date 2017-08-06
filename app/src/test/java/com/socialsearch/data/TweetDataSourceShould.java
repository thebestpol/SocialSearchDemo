package com.socialsearch.data;

import android.support.annotation.NonNull;
import com.socialsearch.data.tweet.dto.TweetDto;
import org.junit.Before;
import org.junit.Test;
import rx.Observable;
import rx.observers.TestSubscriber;
import twitter4j.MediaEntity;
import twitter4j.Status;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * SocialSearchDemo
 * com.socialsearch.data
 * TweetDataSourceShould
 */
public class TweetDataSourceShould {

  private TweetDataSource twitterDataSource;
  private Helper mockHelper;

  @Before public void setUp() {
    mockHelper = mock(Helper.class);
    twitterDataSource = new TweetDataSource() {
      @NonNull @Override public Observable<Status> createTwitterObservable(String query) {
        return mockHelper.createTwitterObservable();
      }
    };
  }

  @Test public void filter_null_status() {
    when(mockHelper.createTwitterObservable()).thenReturn(Observable.just(null));

    TestSubscriber<TweetDto> subscriber = TestSubscriber.create();
    twitterDataSource.getData("fake query").subscribe(subscriber);

    subscriber.assertCompleted();
    subscriber.assertValueCount(0);
  }

  @Test public void filter_status_without_media() {
    Status mock = mock(Status.class);
    when(mock.getMediaEntities()).thenReturn(null);
    Status mockWithEmptyMedia = mock(Status.class);
    when(mockWithEmptyMedia.getMediaEntities()).thenReturn(new MediaEntity[0]);
    when(mockHelper.createTwitterObservable()).thenReturn(
        Observable.just(mock, mockWithEmptyMedia));

    TestSubscriber<TweetDto> subscriber = TestSubscriber.create();
    twitterDataSource.getData("fake query").subscribe(subscriber);

    subscriber.assertCompleted();
    subscriber.assertValueCount(0);
  }

  @Test public void one_social_data_for_each_media_photo_entity() {
    Status mock = mock(Status.class);
    when(mock.getMediaEntities()).thenReturn(null);
    Status mockWithEmptyMedia = mock(Status.class);
    when(mockWithEmptyMedia.getMediaEntities()).thenReturn(new MediaEntity[0]);

    Status mockWithMedia = mock(Status.class);
    MediaEntity mockEntity = mock(MediaEntity.class);
    when(mockEntity.getMediaURL()).thenReturn("FakeUrl");
    when(mockEntity.getType()).thenReturn("photo");
    MediaEntity mockEntity2 = mock(MediaEntity.class);
    when(mockEntity2.getMediaURL()).thenReturn("FakeUrl2");
    MediaEntity[] value = { mockEntity, mockEntity2 };
    when(mockWithMedia.getMediaEntities()).thenReturn(value);

    when(mockHelper.createTwitterObservable()).thenReturn(
        Observable.just(mock, mockWithEmptyMedia, mockWithMedia));

    TestSubscriber<TweetDto> subscriber = TestSubscriber.create();
    twitterDataSource.getData("fake query").subscribe(subscriber);

    subscriber.assertCompleted();
    subscriber.assertValueCount(1);
  }

  @Test public void one_social_data_for_each_media_photo_entity_another_case() {
    Status mock = mock(Status.class);
    when(mock.getMediaEntities()).thenReturn(null);
    Status mockWithEmptyMedia = mock(Status.class);
    when(mockWithEmptyMedia.getMediaEntities()).thenReturn(new MediaEntity[0]);

    Status mockWithMedia = mock(Status.class);
    MediaEntity mockEntity = mock(MediaEntity.class);
    when(mockEntity.getMediaURL()).thenReturn("FakeUrl");
    when(mockEntity.getType()).thenReturn("photo");
    MediaEntity mockEntity2 = mock(MediaEntity.class);
    when(mockEntity2.getMediaURL()).thenReturn("FakeUrl2");
    MediaEntity[] value = { mockEntity, mockEntity2 };
    when(mockWithMedia.getMediaEntities()).thenReturn(value);

    Status mockWithTwoMedia = mock(Status.class);
    MediaEntity mockEntity3 = mock(MediaEntity.class);
    when(mockEntity3.getMediaURL()).thenReturn("FakeUrl3");
    when(mockEntity3.getType()).thenReturn("photo");
    MediaEntity mockEntity4 = mock(MediaEntity.class);
    when(mockEntity4.getType()).thenReturn("photo");
    when(mockEntity4.getMediaURL()).thenReturn("FakeUrl4");
    MediaEntity[] value2 = { mockEntity3, mockEntity4 };
    when(mockWithTwoMedia.getMediaEntities()).thenReturn(value2);

    when(mockHelper.createTwitterObservable()).thenReturn(
        Observable.just(mock, mockWithEmptyMedia, mockWithMedia, mockWithTwoMedia));

    TestSubscriber<TweetDto> subscriber = TestSubscriber.create();
    twitterDataSource.getData("fake query").subscribe(subscriber);

    subscriber.assertCompleted();
    subscriber.assertValueCount(3);
  }

  interface Helper {
    Observable<Status> createTwitterObservable();
  }
}