package com.socialsearch.main.di;

import android.content.Context;
import com.socialsearch.core.di.PerActivity;
import com.socialsearch.main.DemoUserStory;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

/**
 * SocialSearchDemo
 * com.socialsearch.main.di
 * MainModule
 */
@Module public class MainModule {

  @Provides @PerActivity public DemoUserStory provideUserStory() {
    return new DemoUserStory();
  }

  @Provides @Named("screenOrientation") public boolean provideScreenOrientation(Context context) {
    return context.getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE;
  }
}
