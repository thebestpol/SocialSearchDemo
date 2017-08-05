package com.socialsearch.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * SocialSearchDemo
 * com.socialsearch.entity
 * HistoryData
 */

public class HistoryData implements Parcelable {

  public static final Creator<HistoryData> CREATOR = new Creator<HistoryData>() {
    @Override public HistoryData createFromParcel(Parcel source) {
      return new HistoryData(source);
    }

    @Override public HistoryData[] newArray(int size) {
      return new HistoryData[size];
    }
  };
  private final String query;

  public HistoryData(String query) {
    this.query = query;
  }

  protected HistoryData(Parcel in) {
    this.query = in.readString();
  }

  public String getQuery() {
    return query;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.query);
  }
}
