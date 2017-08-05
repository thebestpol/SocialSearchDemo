package com.socialsearch.core.model;

/**
 * SocialSearchDemo
 * com.socialsearch.core.model
 * Callback
 */

public interface Callback<T> {

  void onSuccess(T response);

  void onError(String errorMessage);
}
