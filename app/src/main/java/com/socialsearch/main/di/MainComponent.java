package com.socialsearch.main.di;

import com.socialsearch.main.MainActivity;
import com.socialsearch.core.di.PerActivity;
import dagger.Subcomponent;

/**
 * SocialSearchDemo
 * com.socialsearch.main.di
 * MainComponent
 */
@PerActivity @Subcomponent(modules = MainModule.class) public interface MainComponent {
  void inject(MainActivity activity);
}
