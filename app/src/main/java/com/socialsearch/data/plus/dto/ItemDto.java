package com.socialsearch.data.plus.dto;

/**
 * SocialSearchDemo
 * com.socialsearch.data.plus.dto
 * ItemDto
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemDto {

  @SerializedName("kind") @Expose private String kind;
  @SerializedName("etag") @Expose private String etag;
  @SerializedName("objectType") @Expose private String objectType;
  @SerializedName("id") @Expose private String id;
  @SerializedName("displayName") @Expose private String displayName;
  @SerializedName("url") @Expose private String url;
  @SerializedName("image") @Expose private ImageDto image;

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

  public String getObjectType() {
    return objectType;
  }

  public void setObjectType(String objectType) {
    this.objectType = objectType;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public ImageDto getImage() {
    return image;
  }

  public void setImage(ImageDto image) {
    this.image = image;
  }
}
