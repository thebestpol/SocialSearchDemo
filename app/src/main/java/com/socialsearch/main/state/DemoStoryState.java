package com.socialsearch.main.state;

import android.os.Parcel;
import android.os.Parcelable;
import com.socialsearch.entity.HistoryData;
import com.socialsearch.entity.SocialData;
import java.util.ArrayList;
import java.util.List;

/**
 * SocialSearchDemo
 * com.socialsearch.main.state
 * DemoStoryState
 */

public class DemoStoryState implements Parcelable {

  private String query;
  private String feedBackMessage;
  private List<SocialData> socialData;
  private List<HistoryData> history;

  public DemoStoryState() {
    history = new ArrayList<>();
  }

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public void addQueryToHistory(String query) {
    history.add(new HistoryData(query));
  }

  public String getFeedBackMessage() {
    return feedBackMessage;
  }

  public void setFeedbackMessage(String feedbackMessage) {
    this.feedBackMessage = feedbackMessage;
  }

  public void setQueryResponse(List<SocialData> socialData) {
    this.socialData = socialData;
  }

  public List<SocialData> getSocialData() {
    return socialData;
  }

  public List<HistoryData> getHistory() {
    return history;
  }

  public void clearQueryResponse() {
    socialData = null;
  }

  public void clearQuery() {
    query = null;
  }

  public void clearFeedbackMessage() {
    feedBackMessage = null;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.query);
    dest.writeString(this.feedBackMessage);
    dest.writeTypedList(this.socialData);
    dest.writeTypedList(this.history);
  }

  protected DemoStoryState(Parcel in) {
    this.query = in.readString();
    this.feedBackMessage = in.readString();
    this.socialData = in.createTypedArrayList(SocialData.CREATOR);
    this.history = in.createTypedArrayList(HistoryData.CREATOR);
  }

  public static final Creator<DemoStoryState> CREATOR = new Creator<DemoStoryState>() {
    @Override public DemoStoryState createFromParcel(Parcel source) {
      return new DemoStoryState(source);
    }

    @Override public DemoStoryState[] newArray(int size) {
      return new DemoStoryState[size];
    }
  };
}
