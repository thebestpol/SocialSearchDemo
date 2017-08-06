package com.socialsearch.data.mapper;

/**
 * SocialSearchDemo
 * com.socialsearch.data.mapper
 * DtoMapper
 */

public interface DtoMapper<D, E> {

  E dtoToEntity(D dto);
}
