package com.socialsearch.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

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

    getActivity().setTitle("Search");
  }
}
