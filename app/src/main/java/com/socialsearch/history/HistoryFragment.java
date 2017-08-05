package com.socialsearch.history;

import android.view.View;
import com.socialsearch.core.view.fragment.RootFragment;

/**
 * SocialSearchDemo
 * com.socialsearch.history
 * HistoryFragment
 */

public class HistoryFragment extends RootFragment {

  public static HistoryFragment newInstance() {
    return new HistoryFragment();
  }

  @Override protected void initializeView(View view) {

  }

  @Override protected void initializePresenter() {

  }

  @Override protected void initializeInjector() {
    
  }

  @Override protected int getTitleResource() {
    return 0;
  }

  @Override protected boolean enableOptionsMenu() {
    return false;
  }

  @Override protected int getLayoutResource() {
    return 0;
  }
}
