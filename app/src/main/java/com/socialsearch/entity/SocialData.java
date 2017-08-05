package com.socialsearch.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * SocialSearchDemo
 * com.socialsearch.entity
 * SocialData
 */

public class SocialData implements Parcelable {

  public static final Creator<SocialData> CREATOR = new Creator<SocialData>() {
    @Override public SocialData createFromParcel(Parcel source) {
      return new SocialData(source);
    }

    @Override public SocialData[] newArray(int size) {
      return new SocialData[size];
    }
  };
  private final String imageUrl;

  public SocialData(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  protected SocialData(Parcel in) {
    this.imageUrl = in.readString();
  }

  public String getImageUrl() {
    return imageUrl;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.imageUrl);
  }
}
