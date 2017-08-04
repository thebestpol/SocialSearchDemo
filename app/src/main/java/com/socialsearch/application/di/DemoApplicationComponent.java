package com.socialsearch.application.di;

import com.socialsearch.application.DemoApplication;
import com.socialsearch.main.di.MainComponent;
import com.socialsearch.main.di.MainModule;
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

  MainComponent createMainComponent(MainModule module);
}
