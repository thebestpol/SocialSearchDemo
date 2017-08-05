package com.socialsearch.history.view;

import com.socialsearch.entity.HistoryData;
import java.util.List;

/**
 * SocialSearchDemo
 * com.socialsearch.history.view
 * HistoryView
 */

public interface HistoryView {
  void loadHistoryData(List<HistoryData> historyData);

  void showFeedbackMessage(String feedbackMessage);
}
