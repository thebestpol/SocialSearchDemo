package com.socialsearch.data.plus.dto;

/**
 * SocialSearchDemo
 * com.socialsearch.data.plus.dto
 * PlusUserDto
 */

public class PlusUserDto {

  private String profileImage;

  public PlusUserDto(String profileImage) {
    this.profileImage = profileImage.replace("?sz=50", "?sz=180");
  }

  public String getProfileImage() {
    return profileImage;
  }

  public void setProfileImage(String profileImage) {
    this.profileImage = profileImage;
  }
}
