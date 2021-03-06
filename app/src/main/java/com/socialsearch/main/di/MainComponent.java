package com.socialsearch.main.di;

import com.socialsearch.core.di.PerActivity;
import com.socialsearch.history.di.HistoryComponent;
import com.socialsearch.history.di.HistoryModule;
import com.socialsearch.main.MainActivity;
import com.socialsearch.search.di.SearchComponent;
import com.socialsearch.search.di.SearchModule;
import dagger.Subcomponent;

/**
 * SocialSearchDemo
 * com.socialsearch.main.di
 * MainComponent
 */
@PerActivity @Subcomponent(modules = MainModule.class) public interface MainComponent {
  void inject(MainActivity activity);

  SearchComponent createSearchComponent(SearchModule module);

  HistoryComponent createHistoryComponent(HistoryModule module);
}
