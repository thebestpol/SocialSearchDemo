package com.socialsearch.core.view.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * SocialSearchDemo
 * com.socialsearch.core.view.fragment
 * RootFragment
 */

public abstract class RootFragment extends Fragment {

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    getActivity().setTitle(getTitleResource());
    setHasOptionsMenu(enableOptionsMenu());

    initializeInjector();
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(getLayoutResource(), container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    initializeView(view);
    initializePresenter();
  }

  protected abstract void initializeView(View view);

  protected abstract void initializePresenter();

  protected abstract void initializeInjector();

  protected abstract @StringRes int getTitleResource();

  protected abstract boolean enableOptionsMenu();

  protected abstract @LayoutRes int getLayoutResource();
}
