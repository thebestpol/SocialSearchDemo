package com.socialsearch.core.view.manager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * SocialSearchDemo
 * com.socialsearch.core.view.manager
 * LinerLayoutManagerProvider
 */

public class LinerLayoutManagerProvider implements LayoutManagerProvider {

  private final Context context;

  public LinerLayoutManagerProvider(Context context) {
    this.context = context;
  }

  @Override public RecyclerView.LayoutManager getLayoutManager() {
    return new LinearLayoutManager(context);
  }
}
