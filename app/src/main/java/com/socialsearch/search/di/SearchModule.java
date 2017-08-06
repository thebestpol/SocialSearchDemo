package com.socialsearch.search.di;

import android.content.Context;
import com.socialsearch.core.di.PerFragment;
import com.socialsearch.core.executor.MainThread;
import com.socialsearch.core.view.ImageLoader;
import com.socialsearch.core.view.manager.GirdLayoutManagerProvider;
import com.socialsearch.core.view.manager.LayoutManagerProvider;
import com.socialsearch.core.view.manager.LinerLayoutManagerProvider;
import com.socialsearch.data.SocialDataRepository;
import com.socialsearch.history.usecase.GetSocialDataUseCase;
import com.socialsearch.main.DemoUserStory;
import com.socialsearch.search.model.SearchModel;
import com.socialsearch.search.model.SocialDataSearchModel;
import com.socialsearch.search.presenter.SearchPresenter;
import com.socialsearch.search.view.adapter.SocialDataAdapter;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.Executor;
import javax.inject.Named;

/**
 * SocialSearchDemo
 * com.socialsearch.search.di
 * SearchModule
 */
@Module public class SearchModule {

  @Provides @PerFragment public SearchModel provideSearchModel(SocialDataSearchModel searchModel) {
    return searchModel;
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

  @Provides @PerFragment
  public GetSocialDataUseCase provideGetSocialDataUseCase(SocialDataRepository repository,
      Executor executor, MainThread scheduler) {
    return new GetSocialDataUseCase(repository, executor, scheduler);
  }
}
