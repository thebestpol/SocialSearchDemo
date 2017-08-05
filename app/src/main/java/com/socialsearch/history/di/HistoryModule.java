package com.socialsearch.history.di;

import com.socialsearch.core.di.PerFragment;
import com.socialsearch.core.model.Callback;
import com.socialsearch.entity.HistoryData;
import com.socialsearch.history.model.HistoryModel;
import com.socialsearch.history.presenter.HistoryPresenter;
import dagger.Module;
import dagger.Provides;
import java.util.List;

/**
 * SocialSearchDemo
 * com.socialsearch.history.di
 * HistoryModule
 */

@Module public class HistoryModule {

  @Provides @PerFragment HistoryModel provideHistoryModel() {
    return new HistoryModel() {
      @Override public void obtainHistoryData(Callback<List<HistoryData>> callback) {

      }
    };
  }

  @Provides @PerFragment HistoryPresenter provideHistoryPresenter(HistoryModel model) {
    return new HistoryPresenter(model);
  }
}
