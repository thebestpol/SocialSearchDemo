package com.socialsearch.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
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

  public static SearchFragment newInstance() {
    return new SearchFragment();
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    getActivity().setTitle(R.string.search_label);
    setHasOptionsMenu(true);

    initializeInjector();
    initializePresenter();
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
}
