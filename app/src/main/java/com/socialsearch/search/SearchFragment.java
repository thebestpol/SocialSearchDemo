package com.socialsearch.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.socialsearch.R;
import com.socialsearch.core.di.HasComponent;
import com.socialsearch.main.di.MainComponent;
import com.socialsearch.search.di.SearchModule;
import com.socialsearch.search.presenter.SearchPresenter;
import javax.inject.Inject;

/**
 * SocialSearchDemo
 * com.socialsearch.search
 * SearchFragment
 */

public class SearchFragment extends Fragment {

  @Inject SearchPresenter presenter;
  private RecyclerView recyclerView;
  private View progressView;
  private TextView feedbackTextView;

  public static SearchFragment newInstance() {
    return new SearchFragment();
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
    progressView = view.findViewById(R.id.progress);
    feedbackTextView = ((TextView) view.findViewById(R.id.feedbackTextView));
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

    //MenuItem item = menu.findItem(R.id.search);
    //((SearchView) item.getActionView()).setOnQueryTextListener(
    //    new SearchView.OnQueryTextListener() {
    //      @Override public boolean onQueryTextSubmit(String query) {
    //        return false;
    //      }
    //
    //      @Override public boolean onQueryTextChange(String newText) {
    //        return false;
    //      }
    //    });
  }

  public void showFeedbackMessage(String feedbackMessage) {
    feedbackTextView.setText(feedbackMessage);
    feedbackTextView.setVisibility(View.VISIBLE);
    recyclerView.setVisibility(View.GONE);
    progressView.setVisibility(View.GONE);
  }
}
