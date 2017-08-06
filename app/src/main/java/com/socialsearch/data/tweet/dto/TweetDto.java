package com.socialsearch.data.tweet.dto;

/**
 * SocialSearchDemo
 * com.socialsearch.data.tweet.dto
 * TweetDto
 */

public class TweetDto {

  private String tweetImage;

  public TweetDto(String tweetImage) {
    this.tweetImage = tweetImage;
  }

  public String getTweetImage() {
    return tweetImage;
  }

  public void setTweetImage(String tweetImage) {
    this.tweetImage = tweetImage;
  }
}
