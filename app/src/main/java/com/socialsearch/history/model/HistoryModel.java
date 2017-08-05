package com.socialsearch.history.model;

import com.socialsearch.core.model.Callback;
import com.socialsearch.entity.HistoryData;
import java.util.List;

/**
 * SocialSearchDemo
 * com.socialsearch.history.model
 * HistoryModel
 */

public interface HistoryModel {

  void obtainHistoryData(Callback<List<HistoryData>> callback);
}
