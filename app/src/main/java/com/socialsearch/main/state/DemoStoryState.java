package com.socialsearch.main.state;

import android.os.Parcel;
import android.os.Parcelable;
import com.socialsearch.entity.SocialData;
import java.util.ArrayList;
import java.util.List;

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
  private String query;
  private String errorMessage;
  private List<SocialData> socialData;

  public DemoStoryState() {
  }

  protected DemoStoryState(Parcel in) {
    this.query = in.readString();
    this.errorMessage = in.readString();
    this.socialData = new ArrayList<SocialData>();
    in.readList(this.socialData, SocialData.class.getClassLoader());
  }

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public void setQueryResponse(List<SocialData> socialData) {
    this.socialData = socialData;
  }

  public List<SocialData> getSocialData() {
    return socialData;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.query);
    dest.writeString(this.errorMessage);
    dest.writeList(this.socialData);
  }
}
