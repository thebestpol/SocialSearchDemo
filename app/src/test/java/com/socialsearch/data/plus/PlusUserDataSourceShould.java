package com.socialsearch.data.plus;

import com.socialsearch.data.plus.dto.ImageDto;
import com.socialsearch.data.plus.dto.ItemDto;
import com.socialsearch.data.plus.dto.PlusUserDto;
import com.socialsearch.data.plus.dto.SearchResponseDto;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * SocialSearchDemo
 * com.socialsearch.data.plus
 * PlusUserDataSourceShould
 */
public class PlusUserDataSourceShould {

  @Mock GooglePlusApi mockApi;
  private PlusUserDataSource plusUserDataSource;

  @Before public void setUp() {
    MockitoAnnotations.initMocks(this);

    plusUserDataSource = new PlusUserDataSource(mockApi);
  }

  @Test public void filter_null_response() {
    when(mockApi.searchUsers(anyString())).thenReturn(Observable.just(null));

    TestSubscriber<PlusUserDto> subscriber = TestSubscriber.create();
    plusUserDataSource.getData("query").subscribe(subscriber);

    subscriber.onCompleted();
    subscriber.assertValueCount(0);
  }

  @Test public void filter_empty_response() {
    SearchResponseDto mock = new SearchResponseDto();
    when(mockApi.searchUsers(anyString())).thenReturn(Observable.just(mock));

    TestSubscriber<PlusUserDto> subscriber = TestSubscriber.create();
    plusUserDataSource.getData("query").subscribe(subscriber);

    subscriber.onCompleted();
    subscriber.assertValueCount(0);
  }

  @Test public void filter_response_without_items() {
    SearchResponseDto mock = new SearchResponseDto();
    mock.setItems(new ArrayList<>());
    when(mockApi.searchUsers(anyString())).thenReturn(Observable.just(mock));

    TestSubscriber<PlusUserDto> subscriber = TestSubscriber.create();
    plusUserDataSource.getData("query").subscribe(subscriber);

    subscriber.onCompleted();
    subscriber.assertValueCount(0);
  }

  @Test public void filter_items_without_image() {

    SearchResponseDto mock = new SearchResponseDto();
    List<ItemDto> items = new ArrayList<>();
    ItemDto itemDto = new ItemDto();
    items.add(itemDto);
    mock.setItems(items);
    when(mockApi.searchUsers(anyString())).thenReturn(Observable.just(mock));

    TestSubscriber<PlusUserDto> subscriber = TestSubscriber.create();
    plusUserDataSource.getData("query").subscribe(subscriber);

    subscriber.onCompleted();
    subscriber.assertValueCount(0);
  }

  @Test public void return_plus_user_for_each_image() {

    SearchResponseDto mock = new SearchResponseDto();
    List<ItemDto> items = new ArrayList<>();
    ItemDto itemDto = new ItemDto();
    ImageDto image = new ImageDto();
    image.setUrl("fake");
    itemDto.setImage(image);
    items.add(itemDto);
    mock.setItems(items);
    when(mockApi.searchUsers(anyString())).thenReturn(Observable.just(mock));

    TestSubscriber<PlusUserDto> subscriber = TestSubscriber.create();
    plusUserDataSource.getData("query").subscribe(subscriber);

    subscriber.onCompleted();
    subscriber.assertValueCount(1);
  }
}