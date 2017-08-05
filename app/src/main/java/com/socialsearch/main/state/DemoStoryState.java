package com.socialsearch.main.state;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * SocialSearchDemo
 * com.socialsearch.main.state
 * DemoStoryState
 */

public class DemoStoryState implements Parcelable {

  public static final Creator<DemoStoryState> CREATOR = new Creator<DemoStoryState>() {
    @Override public DemoStoryState createFromParcel(Parcel source) {
      return new DemoStoryState(source);
    }

    @Override public DemoStoryState[] newArray(int size) {
      return new DemoStoryState[size];
    }
  };

  public DemoStoryState() {
  }

  protected DemoStoryState(Parcel in) {
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
  }
}
