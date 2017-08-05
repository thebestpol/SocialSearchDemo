package com.socialsearch.search.view;

import com.socialsearch.entity.SocialData;
import java.util.List;

/**
 * SocialSearchDemo
 * com.socialsearch.search.view
 * SearchView
 */

public interface SearchView {

  void showFeedbackMessage(String feedbackMessage);

  void showProgress(String progressMessage);

  void loadSocialData(List<SocialData> response);
}
