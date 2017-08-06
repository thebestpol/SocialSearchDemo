package com.socialsearch.data.plus.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * SearchResponseDto
 * SocialSearchDemo
 * com.socialsearch.data.plus.dto
 */

public class SearchResponseDto {

  @SerializedName("kind") @Expose private String kind;
  @SerializedName("etag") @Expose private String etag;
  @SerializedName("selfLink") @Expose private String selfLink;
  @SerializedName("title") @Expose private String title;
  @SerializedName("nextPageToken") @Expose private String nextPageToken;
  @SerializedName("items") @Expose private List<ItemDto> items = null;

  public String getKind() {
    return kind;
  }

  public void setKind(String kind) {
    this.kind = kind;
  }

  public String getEtag() {
    return etag;
  }

  public void setEtag(String etag) {
    this.etag = etag;
  }

  public String getSelfLink() {
    return selfLink;
  }

  public void setSelfLink(String selfLink) {
    this.selfLink = selfLink;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getNextPageToken() {
    return nextPageToken;
  }

  public void setNextPageToken(String nextPageToken) {
    this.nextPageToken = nextPageToken;
  }

  public List<ItemDto> getItems() {
    return items;
  }

  public void setItems(List<ItemDto> items) {
    this.items = items;
  }
}
