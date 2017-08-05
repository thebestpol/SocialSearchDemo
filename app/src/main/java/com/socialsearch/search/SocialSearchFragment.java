package com.socialsearch.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.socialsearch.R;
import com.socialsearch.core.di.HasComponent;
import com.socialsearch.entity.SocialData;
import com.socialsearch.main.di.MainComponent;
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

public class SocialSearchFragment extends Fragment implements SocialSearchView {

  @Inject SearchPresenter presenter;
  @Inject SocialDataAdapter socialDataAdapter;
  @Inject RecyclerView.LayoutManager layoutManager;

  private RecyclerView recyclerView;
  private View progressView;
  private TextView feedbackTextView;
  private TextView progressTextView;

  public static SocialSearchFragment newInstance() {
    return new SocialSearchFragment();
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    getActivity().setTitle(R.string.search_label);
    setHasOptionsMenu(true);

    initializeInjector();
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_search, container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    initializeView(view);
    initializePresenter();
  }

  private void initializeView(View view) {
    recyclerView = ((RecyclerView) view.findViewById(R.id.recyclerView));
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(socialDataAdapter);

    progressView = view.findViewById(R.id.progress);
    feedbackTextView = ((TextView) view.findViewById(R.id.feedbackTextView));
    progressTextView = ((TextView) progressView.findViewById(R.id.progressTextView));
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.history) {
      presenter.onHistoryItemSelected();
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public void onResume() {
    super.onResume();

    presenter.start();
  }

  @Override public void onStop() {
    super.onStop();

    presenter.stop();
  }

  private void initializePresenter() {
    presenter.setView(this);
  }

  private void initializeInjector() {
    ((HasComponent<MainComponent>) getActivity()).getComponent()
        .createSearchComponent(new SearchModule())
        .inject(this);
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
