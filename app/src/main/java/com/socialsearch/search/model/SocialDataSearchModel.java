package com.socialsearch.search.model;

import com.socialsearch.core.model.Callback;
import com.socialsearch.entity.SocialData;
import com.socialsearch.history.usecase.GetSocialDataUseCase;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.Subscriber;

/**
 * SocialSearchDemo
 * com.socialsearch.search.model
 * SocialDataSearchModel
 */

public class SocialDataSearchModel implements SearchModel {

  private final GetSocialDataUseCase useCase;

  @Inject public SocialDataSearchModel(GetSocialDataUseCase useCase) {
    this.useCase = useCase;
  }

  @Override public void obtainSocialData(String query, Callback<List<SocialData>> callback) {
    useCase.execute(query, new SocialDataSubscriber(callback));
  }

  private static class SocialDataSubscriber extends Subscriber<SocialData> {

    private final Callback<List<SocialData>> callback;
    private final List<SocialData> data;

    public SocialDataSubscriber(Callback<List<SocialData>> callback) {
      this.callback = callback;
      data = new ArrayList<>();
    }

    @Override public void onCompleted() {
      callback.onSuccess(data);
    }

    @Override public void onError(Throwable e) {
      callback.onError(e.getMessage());
    }

    @Override public void onNext(SocialData socialData) {
      data.add(socialData);
    }
  }
}
