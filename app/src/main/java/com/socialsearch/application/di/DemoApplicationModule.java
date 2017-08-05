package com.socialsearch.application.di;

import android.content.Context;
import com.socialsearch.application.DemoApplication;
import com.socialsearch.core.view.ImageLoader;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * SocialSearchDemo
 * com.socialsearch.application.di
 * DemoApplicationModule
 */
@Module public class DemoApplicationModule {

  private final Context applicationContext;

  public DemoApplicationModule(DemoApplication demoApplication) {
    applicationContext = demoApplication.getApplicationContext();
  }

  @Provides @Singleton public Context provideApplicationContext() {
    return applicationContext;
  }

  @Provides @Singleton public ImageLoader provideImageLoader() {
    return new ImageLoader();
  }
}
