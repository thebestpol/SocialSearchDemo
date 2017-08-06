package com.socialsearch.data.mapper;

import com.socialsearch.data.plus.dto.PlusUserDto;
import com.socialsearch.data.tweet.dto.TweetDto;
import com.socialsearch.entity.SocialData;

/**
 * SocialSearchDemo
 * com.socialsearch.data.mapper
 * SocialDataMapper
 */

public class SocialDataMapper {

  private final DtoMapper<String, SocialData> dtoMapper = SocialData::new;

  public SocialData tweetToSocialData(TweetDto tweetDto) {
    return dtoMapper.dtoToEntity(tweetDto.getTweetImage());
  }

  public SocialData plusUserToData(PlusUserDto tweetDto) {
    return dtoMapper.dtoToEntity(tweetDto.getProfileImage());
  }
}
