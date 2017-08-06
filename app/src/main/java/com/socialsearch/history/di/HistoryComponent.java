package com.socialsearch.history.di;

import com.socialsearch.core.di.PerFragment;
import com.socialsearch.history.HistoryFragment;
import dagger.Subcomponent;

/**
 * SocialSearchDemo
 * com.socialsearch.history.di
 * HistoryComponent
 */
@PerFragment @Subcomponent(modules = HistoryModule.class) public interface HistoryComponent {
  void inject(HistoryFragment historyFragment);
}
