package com.socialsearch.main;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.FragmentManager;
import com.socialsearch.core.story.StoryContainer;
import com.socialsearch.main.state.DemoStoryState;
import com.socialsearch.search.SocialSearchFragment;

/**
 * SocialSearchDemo
 * com.socialsearch.main
 * DemoUserStory
 */

public class DemoUserStory {

  @VisibleForTesting public static final String KEY_STATE = "KEY_STATE";
  private DemoStoryState storyState;
  private FragmentManager supportFragmentManager;
  private int containerId;

  public DemoUserStory() {
    storyState = initializeStoryState();
  }

  @VisibleForTesting public DemoStoryState initializeStoryState() {
    return new DemoStoryState();
  }

  public void initialize(StoryContainer storyContainer) {
    supportFragmentManager = storyContainer.getSupportManager();
    containerId = storyContainer.getContainerId();
  }

  public void start() {
    supportFragmentManager.beginTransaction()
        .add(containerId, SocialSearchFragment.newInstance())
        .commit();
  }

  public void restoreState(Bundle savedState) {
    storyState = savedState.getParcelable(KEY_STATE);
  }

  public void saveState(Bundle outState) {
    outState.putParcelable(KEY_STATE, storyState);
  }

  public DemoStoryState getStoryState() {
    return storyState;
  }

  public void updateState(DemoStoryState storyState) {
    this.storyState = storyState;
  }
}
