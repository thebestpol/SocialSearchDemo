package com.socialsearch.search.di;

import android.content.Context;
import com.socialsearch.core.di.PerFragment;
import com.socialsearch.core.model.Callback;
import com.socialsearch.core.view.ImageLoader;
import com.socialsearch.core.view.manager.GirdLayoutManagerProvider;
import com.socialsearch.core.view.manager.LayoutManagerProvider;
import com.socialsearch.core.view.manager.LinerLayoutManagerProvider;
import com.socialsearch.entity.SocialData;
import com.socialsearch.main.DemoUserStory;
import com.socialsearch.search.model.SearchModel;
import com.socialsearch.search.presenter.SearchPresenter;
import com.socialsearch.search.view.adapter.SocialDataAdapter;
import dagger.Module;
import dagger.Provides;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;

/**
 * SocialSearchDemo
 * com.socialsearch.search.di
 * SearchModule
 */
@Module public class SearchModule {

  @Provides @PerFragment public SearchModel provideSearchModel() {
    return new SearchModel() {
      @Override public void obtainSocialData(String query, Callback<List<SocialData>> callback) {
        List<SocialData> socialData = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
          socialData.add(new SocialData(
              "https://globalgamejam.org/sites/default/files/styles/game_sidebar__normal/public/game/featured_image/promo_5.png"));
        }

        callback.onSuccess(socialData);
      }
    };
  }

  @Provides @PerFragment
  public SearchPresenter providePresenter(DemoUserStory demoUserStory, SearchModel searchModel) {
    return new SearchPresenter(demoUserStory, searchModel);
  }

  @Provides @PerFragment
  public SocialDataAdapter provideSocialDataAdapter(ImageLoader imageLoader) {
    return new SocialDataAdapter(imageLoader);
  }

  @Provides @PerFragment public LayoutManagerProvider provideLayoutManagerProvider(Context context,
      @Named("screenOrientation") boolean isLandscape) {
    return isLandscape ? new GirdLayoutManagerProvider(context)
        : new LinerLayoutManagerProvider(context);
  }
}
