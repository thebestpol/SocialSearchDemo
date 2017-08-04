package com.socialsearch.application;

import android.app.Application;

/**
 * SocialSearchDemo
 * com.socialsearch.application
 * DemoApplication
 */

public class DemoApplication extends Application {

  @Override public void onCreate() {
    super.onCreate();

    initializeInjector();
  }

  private void initializeInjector() {

  }
}
