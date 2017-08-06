package com.socialsearch.data.plus.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * SocialSearchDemo
 * com.socialsearch.data.plus.dto
 * ImageDto
 */

public class ImageDto {

  @SerializedName("url") @Expose private String url;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
