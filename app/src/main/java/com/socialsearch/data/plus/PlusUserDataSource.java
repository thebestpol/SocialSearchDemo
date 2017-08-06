package com.socialsearch.data.plus;

import com.socialsearch.data.DataSource;
import com.socialsearch.data.plus.dto.ItemDto;
import com.socialsearch.data.plus.dto.PlusUserDto;
import com.socialsearch.data.plus.dto.SearchResponseDto;
import rx.Observable;

/**
 * SocialSearchDemo
 * com.socialsearch.data.plus
 * PlusUserDataSource
 */

public class PlusUserDataSource implements DataSource<PlusUserDto> {

  private final GooglePlusApi googlePlusApi;

  public PlusUserDataSource(GooglePlusApi googlePlusApi) {
    this.googlePlusApi = googlePlusApi;
  }

  @Override public Observable<PlusUserDto> getData(String query) {
    return googlePlusApi.searchUsers(query)
        .filter(searchResponseDto -> searchResponseDto != null)
        .filter(searchResponseDto -> searchResponseDto.getItems() != null)
        .flatMapIterable(SearchResponseDto::getItems)
        .map(ItemDto::getImage)
        .filter(imageDto -> imageDto != null)
        .map(imageUrl -> new PlusUserDto(imageUrl.getUrl()));
  }
}
