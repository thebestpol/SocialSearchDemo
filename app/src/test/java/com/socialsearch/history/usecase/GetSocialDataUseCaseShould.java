package com.socialsearch.history.usecase;

import com.socialsearch.data.SocialDataRepository;
import com.socialsearch.data.TweetDataSource;
import com.socialsearch.data.mapper.SocialDataMapper;
import com.socialsearch.data.plus.PlusUserDataSource;
import java.util.concurrent.Executors;
import org.junit.Before;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.mock;

/**
 * SocialSearchDemo
 * com.socialsearch.history.usecase
 * GetSocialDataUseCaseShould
 */
public class GetSocialDataUseCaseShould {

  private PlusUserDataSource plusDataSource;
  private TweetDataSource tweetDataSource;
  private GetSocialDataUseCase getSocialDataUseCase;

  @Before public void setUp() {
    tweetDataSource = mock(TweetDataSource.class);
    plusDataSource = mock(PlusUserDataSource.class);
    SocialDataRepository repository =
        new SocialDataRepository(tweetDataSource, plusDataSource, new SocialDataMapper());

    getSocialDataUseCase = new GetSocialDataUseCase(repository, Executors.newSingleThreadExecutor(),
        Schedulers.immediate());
  }
}