package com.socialsearch.history;

import android.view.View;
import com.socialsearch.R;
import com.socialsearch.core.presenter.Presenter;
import com.socialsearch.core.view.fragment.RootFragment;
import com.socialsearch.history.di.HistoryModule;

/**
 * SocialSearchDemo
 * com.socialsearch.history
 * HistoryFragment
 */

public class HistoryFragment extends RootFragment {

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
}
