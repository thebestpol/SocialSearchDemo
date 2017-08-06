package com.socialsearch.history.di;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.socialsearch.core.di.PerFragment;
import com.socialsearch.history.model.HistoryModel;
import com.socialsearch.history.model.StateHistoryModel;
import com.socialsearch.history.presenter.HistoryPresenter;
import com.socialsearch.history.view.adapter.HistoryDataAdapter;
import com.socialsearch.main.DemoUserStory;
import dagger.Module;
import dagger.Provides;

/**
 * SocialSearchDemo
 * com.socialsearch.history.di
 * HistoryModule
 */

@Module public class HistoryModule {

  @Provides @PerFragment public HistoryModel provideHistoryModel(StateHistoryModel historyModel) {
    return historyModel;
  }

  @Provides @PerFragment
  public HistoryPresenter provideHistoryPresenter(HistoryModel model, DemoUserStory demoUserStory) {
    return new HistoryPresenter(model, demoUserStory);
  }

  @Provides @PerFragment public HistoryDataAdapter provideHistoryDataAdapter() {
    return new HistoryDataAdapter();
  }

  @Provides @PerFragment public RecyclerView.LayoutManager provideLayoutManager(Context context) {
    return new LinearLayoutManager(context);
  }
}
