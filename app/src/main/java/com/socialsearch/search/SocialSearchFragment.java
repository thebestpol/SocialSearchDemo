package com.socialsearch.search;

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
import com.socialsearch.core.view.manager.LayoutManagerProvider;
import com.socialsearch.entity.SocialData;
import com.socialsearch.search.di.SearchModule;
import com.socialsearch.search.presenter.SearchPresenter;
import com.socialsearch.search.view.SocialSearchView;
import com.socialsearch.search.view.adapter.SocialDataAdapter;
import java.util.List;
import javax.inject.Inject;

/**
 * SocialSearchDemo
 * com.socialsearch.search
 * SocialSearchFragment
 */

public class SocialSearchFragment extends RootFragment implements SocialSearchView {

  @Inject SearchPresenter presenter;
  @Inject SocialDataAdapter socialDataAdapter;
  @Inject LayoutManagerProvider layoutManagerProvider;

  private RecyclerView recyclerView;
  private View progressView;
  private TextView feedbackTextView;
  private TextView progressTextView;

  public static SocialSearchFragment newInstance() {
    return new SocialSearchFragment();
  }

  @Override protected void initializeView(View view) {
    recyclerView = ((RecyclerView) view.findViewById(R.id.recyclerView));
    recyclerView.setLayoutManager(layoutManagerProvider.getLayoutManager());
    recyclerView.setAdapter(socialDataAdapter);

    progressView = view.findViewById(R.id.progress);
    feedbackTextView = (TextView) view.findViewById(R.id.feedbackTextView);
    progressTextView = (TextView) progressView.findViewById(R.id.progressTextView);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.history) {
      presenter.onHistoryItemSelected();
    }
    return super.onOptionsItemSelected(item);
  }

  @Override protected Presenter getPresenter() {
    return presenter;
  }

  @Override protected void initializePresenter() {
    presenter.setView(this);
  }

  @Override protected void initializeInjector() {
    getMainComponent().createSearchComponent(new SearchModule()).inject(this);
  }

  @Override protected int getTitleResource() {
    return R.string.search_label;
  }

  @Override protected boolean enableOptionsMenu() {
    return true;
  }

  @Override protected int getLayoutResource() {
    return R.layout.fragment_search;
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.search_menu, menu);

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

  @Override public void showFeedbackMessage(String feedbackMessage) {
    feedbackTextView.setText(feedbackMessage);
    feedbackTextView.setVisibility(View.VISIBLE);
    recyclerView.setVisibility(View.GONE);
    progressView.setVisibility(View.GONE);
  }

  @Override public void showProgress(String progressMessage) {
    progressTextView.setText(progressMessage);
    progressView.setVisibility(View.VISIBLE);
    feedbackTextView.setVisibility(View.GONE);
    recyclerView.setVisibility(View.GONE);
  }

  @Override public void loadSocialData(List<SocialData> socialData) {
    recyclerView.setVisibility(View.VISIBLE);
    progressView.setVisibility(View.GONE);
    feedbackTextView.setVisibility(View.GONE);

    socialDataAdapter.setItems(socialData);
  }
}
