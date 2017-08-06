package com.socialsearch.data.plus;

import com.socialsearch.data.plus.dto.SearchResponseDto;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * SocialSearchDemo
 * com.socialsearch.data.plus
 * GooglePlusApi
 */

public interface GooglePlusApi {
  public static final String BASE_URL = "https://www.googleapis.com/plus";
  public static final String KEY = "AIzaSyBT8GFL3DRDmFoI_t1RULvGdreioQcpHc4";

  @GET("/v1/people?key=" + KEY) Observable<SearchResponseDto> searchUsers(
      @Query("query") String query);
}
