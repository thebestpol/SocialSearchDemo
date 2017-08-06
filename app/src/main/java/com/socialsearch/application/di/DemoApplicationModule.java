package com.socialsearch.application.di;

import android.content.Context;
import com.socialsearch.application.DemoApplication;
import com.socialsearch.core.view.ImageLoader;
import com.socialsearch.data.DataSource;
import com.socialsearch.data.SocialDataRepository;
import com.socialsearch.data.TweetDataSource;
import com.socialsearch.data.mapper.SocialDataMapper;
import com.socialsearch.data.plus.GooglePlusApi;
import com.socialsearch.data.plus.PlusUserDataSource;
import com.socialsearch.data.plus.ServiceFactory;
import com.socialsearch.data.plus.dto.PlusUserDto;
import com.socialsearch.data.tweet.dto.TweetDto;
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

  @Provides @Singleton
  public DataSource<TweetDto> provideTweetDataSource(TweetDataSource dataSource) {
    return dataSource;
  }

  @Provides @Singleton GooglePlusApi providesGoogleAPiService() {
    return ServiceFactory.createRetrofitService(GooglePlusApi.class, GooglePlusApi.BASE_URL);
  }

  @Provides @Singleton
  public DataSource<PlusUserDto> providePlusUserDataSource(PlusUserDataSource dataSource) {
    return dataSource;
  }

  @Provides @Singleton
  public SocialDataRepository provideSocialDataRepository(DataSource<TweetDto> tweetDataSource,
      DataSource<PlusUserDto> plusUserDataSource, SocialDataMapper socialDataMapper) {
    return new SocialDataRepository(tweetDataSource, plusUserDataSource, socialDataMapper);
  }
}
