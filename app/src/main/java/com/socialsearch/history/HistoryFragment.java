package com.socialsearch.history;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import com.socialsearch.R;
import com.socialsearch.core.presenter.Presenter;
import com.socialsearch.core.view.fragment.RootFragment;
import com.socialsearch.entity.HistoryData;
import com.socialsearch.history.di.HistoryModule;
import com.socialsearch.history.presenter.HistoryPresenter;
import com.socialsearch.history.view.HistoryView;
import java.util.List;
import javax.inject.Inject;

/**
 * SocialSearchDemo
 * com.socialsearch.history
 * HistoryFragment
 */

public class HistoryFragment extends RootFragment implements HistoryView {

  @Inject HistoryPresenter presenter;

  public static HistoryFragment newInstance() {
    return new HistoryFragment();
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.history_menu, menu);
  }

  @Override protected Presenter getPresenter() {
    return presenter;
  }

  @Override protected void initializeView(View view) {

  }

  @Override protected void initializePresenter() {
    presenter.setView(this);
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
