package com.socialsearch.core.view.manager;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * SocialSearchDemo
 * com.socialsearch.core.view.manager
 * LinerLayoutManagerProvider
 */

public class GirdLayoutManagerProvider implements LayoutManagerProvider {

  private final Context context;

  public GirdLayoutManagerProvider(Context context) {
    this.context = context;
  }

  @Override public RecyclerView.LayoutManager getLayoutManager() {
    return new GridLayoutManager(context, 4);
  }
}
