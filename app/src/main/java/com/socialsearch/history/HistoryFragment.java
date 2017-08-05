package com.socialsearch.history;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.socialsearch.R;
import com.socialsearch.core.presenter.Presenter;
import com.socialsearch.core.view.fragment.RootFragment;
import com.socialsearch.entity.HistoryData;
import com.socialsearch.history.di.HistoryModule;
import com.socialsearch.history.presenter.HistoryPresenter;
import com.socialsearch.history.view.HistoryView;
import com.socialsearch.history.view.adapter.HistoryDataAdapter;
import java.util.List;
import javax.inject.Inject;

/**
 * SocialSearchDemo
 * com.socialsearch.history
 * HistoryFragment
 */

public class HistoryFragment extends RootFragment implements HistoryView {

  @Inject HistoryPresenter presenter;
  @Inject HistoryDataAdapter adapter;
  @Inject RecyclerView.LayoutManager layoutManager;

  private RecyclerView recyclerView;
  private TextView feedbackTextView;

  public static HistoryFragment newInstance() {
    return new HistoryFragment();
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.history_menu, menu);

    MenuItem item = menu.findItem(R.id.search);
    ((SearchView) item.getActionView()).setOnQueryTextListener(
        new SearchView.OnQueryTextListener() {
          @Override public boolean onQueryTextSubmit(String query) {
            presenter.onQuerySubmitted(query);
            return false;
          }

          @Override public boolean onQueryTextChange(String newText) {
            return false;
          }
        });
  }

  @Override protected Presenter getPresenter() {
    return presenter;
  }

  @Override protected void initializeView(View view) {
    recyclerView = ((RecyclerView) view.findViewById(R.id.recyclerView));
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(adapter);
    feedbackTextView = ((TextView) view.findViewById(R.id.feedbackTextView));
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
    recyclerView.setVisibility(View.VISIBLE);
    feedbackTextView.setVisibility(View.GONE);

    adapter.setItems(historyData);
  }

  @Override public void showFeedbackMessage(String feedbackMessage) {
    recyclerView.setVisibility(View.GONE);
    feedbackTextView.setVisibility(View.VISIBLE);
    feedbackTextView.setText(feedbackMessage);
  }
}
