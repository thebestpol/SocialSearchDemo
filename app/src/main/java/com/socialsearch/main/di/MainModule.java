package com.socialsearch.main.di;

import com.socialsearch.core.di.PerActivity;
import com.socialsearch.main.DemoUserStory;
import dagger.Module;
import dagger.Provides;

/**
 * SocialSearchDemo
 * com.socialsearch.main.di
 * MainModule
 */
@Module public class MainModule {

  @Provides @PerActivity public DemoUserStory provideUserStory() {
    return new DemoUserStory();
  }
}
