package com.socialsearch.application.di;

import com.socialsearch.application.DemoApplication;
import dagger.Component;
import javax.inject.Singleton;

/**
 * SocialSearchDemo
 * com.socialsearch.application.di
 * DemoApplicationComponent
 */

@Singleton @Component(modules = DemoApplicationModule.class)
public interface DemoApplicationComponent {

  void inject(DemoApplication application);
}
