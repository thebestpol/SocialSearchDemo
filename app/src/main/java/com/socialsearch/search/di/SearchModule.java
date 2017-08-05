package com.socialsearch.search.di;

import com.socialsearch.core.di.PerFragment;
import com.socialsearch.core.model.Callback;
import com.socialsearch.entity.SocialData;
import com.socialsearch.main.DemoUserStory;
import com.socialsearch.search.model.SearchModel;
import com.socialsearch.search.presenter.SearchPresenter;
import dagger.Module;
import dagger.Provides;
import java.util.List;

/**
 * SocialSearchDemo
 * com.socialsearch.search.di
 * SearchModule
 */
@Module public class SearchModule {

  @Provides @PerFragment public SearchModel provideSearchModel() {
    return new SearchModel() {
      @Override public void obtainSocialData(String query, Callback<List<SocialData>> callback) {

      }
    };
  }

  @Provides @PerFragment
  public SearchPresenter providePresenter(DemoUserStory demoUserStory, SearchModel searchModel) {
    return new SearchPresenter(demoUserStory, searchModel);
  }
}
