package com.socialsearch.search.di;

import com.socialsearch.core.di.PerFragment;
import com.socialsearch.search.presenter.SearchPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * SocialSearchDemo
 * com.socialsearch.search.di
 * SearchModule
 */
@Module public class SearchModule {

  @Provides @PerFragment public SearchPresenter providePresenter() {
    return new SearchPresenter();
  }
}
