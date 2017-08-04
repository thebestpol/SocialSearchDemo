package com.socialsearch.application;

import android.app.Application;
import com.socialsearch.application.di.DaggerDemoApplicationComponent;
import com.socialsearch.application.di.DemoApplicationComponent;
import com.socialsearch.application.di.DemoApplicationModule;
import com.socialsearch.core.di.HasComponent;

/**
 * SocialSearchDemo
 * com.socialsearch.application
 * DemoApplication
 */

public class DemoApplication extends Application implements HasComponent<DemoApplicationComponent> {

  private DemoApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();

    initializeInjector();
  }

  private void initializeInjector() {
    applicationComponent = DaggerDemoApplicationComponent.builder()
        .demoApplicationModule(new DemoApplicationModule(this))
        .build();
    applicationComponent.inject(this);
  }

  @Override public DemoApplicationComponent getComponent() {
    return applicationComponent;
  }
}
