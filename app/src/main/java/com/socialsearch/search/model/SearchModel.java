package com.socialsearch.search.model;

import com.socialsearch.core.model.Callback;
import com.socialsearch.entity.SocialData;
import java.util.List;

/**
 * SocialSearchDemo
 * com.socialsearch.search.model
 * SearchModel
 */

public interface SearchModel {

  void obtainSocialData(String query, Callback<List<SocialData>> callback);

  void stop();
}
