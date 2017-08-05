package com.socialsearch.history.di;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.socialsearch.core.di.PerFragment;
import com.socialsearch.core.model.Callback;
import com.socialsearch.entity.HistoryData;
import com.socialsearch.history.model.HistoryModel;
import com.socialsearch.history.presenter.HistoryPresenter;
import com.socialsearch.history.view.adapter.HistoryDataAdapter;
import com.socialsearch.main.DemoUserStory;
import dagger.Module;
import dagger.Provides;
import java.util.ArrayList;
import java.util.List;

/**
 * SocialSearchDemo
 * com.socialsearch.history.di
 * HistoryModule
 */

@Module public class HistoryModule {

  @Provides @PerFragment public HistoryModel provideHistoryModel() {
    return new HistoryModel() {
      @Override public void obtainHistoryData(Callback<List<HistoryData>> callback) {
        List<HistoryData> data = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
          data.add(new HistoryData("QUery " + i));
        }

        callback.onSuccess(data);
      }
    };
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
