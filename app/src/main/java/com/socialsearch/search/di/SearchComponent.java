package com.socialsearch.search.di;

import com.socialsearch.core.di.PerFragment;
import com.socialsearch.search.SocialSearchFragment;
import dagger.Subcomponent;

/**
 * SocialSearchDemo
 * com.socialsearch.search.di
 * SearchComponent
 */
@PerFragment @Subcomponent(modules = SearchModule.class) public interface SearchComponent {

  void inject(SocialSearchFragment fragment);
}
