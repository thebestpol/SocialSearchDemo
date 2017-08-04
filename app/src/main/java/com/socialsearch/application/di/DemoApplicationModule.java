package com.socialsearch.application.di;

import android.content.Context;
import com.socialsearch.application.DemoApplication;
import dagger.Module;
import dagger.Provides;

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

  @Provides public Context provideApplicationContext() {
    return applicationContext;
  }
}
