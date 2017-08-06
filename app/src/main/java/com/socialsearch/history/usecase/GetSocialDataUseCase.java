package com.socialsearch.history.usecase;

import com.socialsearch.core.executor.MainThread;
import com.socialsearch.data.SocialDataRepository;
import com.socialsearch.entity.SocialData;
import java.util.concurrent.Executor;
import javax.inject.Inject;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

/**
 * SocialSearchDemo
 * com.socialsearch.history.usecase
 * GetSocialDataUseCase
 */

public class GetSocialDataUseCase {

  private final SocialDataRepository repository;
  private final Executor executor;
  private final Scheduler postExecution;

  @Inject public GetSocialDataUseCase(SocialDataRepository repository, Executor executor,
      MainThread scheduler) {
    this.repository = repository;
    this.executor = executor;
    this.postExecution = scheduler.getScheduler();
  }

  public Subscription execute(String query, Subscriber<SocialData> subscriber) {
    return Observable.merge(
        repository.getPlusSocialData(query).subscribeOn(Schedulers.from(executor)),
        repository.getTweetsSocialData(query).subscribeOn(Schedulers.from(executor)))
        .subscribeOn(Schedulers.from(executor))
        .observeOn(postExecution)
        .subscribe(subscriber);
  }
}
