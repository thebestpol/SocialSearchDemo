package com.socialsearch.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import com.socialsearch.R;

/**
 * SocialSearchDemo
 * com.socialsearch.search
 * SearchFragment
 */

public class SearchFragment extends Fragment {

  public static SearchFragment newInstance() {
    return new SearchFragment();
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    getActivity().setTitle(R.string.search_label);
    setHasOptionsMenu(true);
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.search_menu, menu);
  }
}
