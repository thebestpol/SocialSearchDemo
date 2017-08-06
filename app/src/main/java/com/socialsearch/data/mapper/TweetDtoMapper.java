package com.socialsearch.data.mapper;

import com.socialsearch.entity.SocialData;

/**
 * SocialSearchDemo
 * com.socialsearch.data.mapper
 * TweetDtoMapper
 */

public class TweetDtoMapper implements DtoMapper<String, SocialData> {
  @Override public SocialData dtoToEntity(String dto) {
    return new SocialData(dto);
  }
}
