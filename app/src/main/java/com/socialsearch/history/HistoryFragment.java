package com.socialsearch.history;

import android.view.View;
import com.socialsearch.R;
import com.socialsearch.core.presenter.Presenter;
import com.socialsearch.core.view.fragment.RootFragment;
import com.socialsearch.entity.HistoryData;
import com.socialsearch.history.di.HistoryModule;
import com.socialsearch.history.view.HistoryView;
import java.util.List;

/**
 * SocialSearchDemo
 * com.socialsearch.history
 * HistoryFragment
 */

public class HistoryFragment extends RootFragment implements HistoryView {

  public static HistoryFragment newInstance() {
    return new HistoryFragment();
  }

  @Override protected Presenter getPresenter() {
    return null;
  }

  @Override protected void initializeView(View view) {

  }

  @Override protected void initializePresenter() {

  }

  @Override protected void initializeInjector() {
    getMainComponent().createHistoryComponent(new HistoryModule()).inject(this);
  }

  @Override protected int getTitleResource() {
    return R.string.history_label;
  }

  @Override protected boolean enableOptionsMenu() {
    return true;
  }

  @Override protected int getLayoutResource() {
    return R.layout.fragment_history;
  }

  @Override public void loadHistoryData(List<HistoryData> historyData) {
    
  }

  @Override public void showFeedbackMessage(String feedbackMessage) {

  }
}
