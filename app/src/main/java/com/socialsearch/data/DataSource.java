package com.socialsearch.data;

import rx.Observable;

/**
 * SocialSearchDemo
 * com.socialsearch.data
 * DataSource
 */

public interface DataSource<T> {

  Observable<T> getData(String query);
}
