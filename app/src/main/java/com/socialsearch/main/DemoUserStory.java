package com.socialsearch.main;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.FragmentManager;
import com.socialsearch.core.story.StoryContainer;
import com.socialsearch.main.state.DemoStoryState;
import com.socialsearch.search.SearchFragment;

/**
 * SocialSearchDemo
 * com.socialsearch.main
 * DemoUserStory
 */

public class DemoUserStory {

  private DemoStoryState storyState;
  private FragmentManager supportFragmentManager;
  private int containerId;

  public DemoUserStory() {
    storyState = createStoryState();
  }

  @VisibleForTesting public DemoStoryState createStoryState() {
    return new DemoStoryState();
  }

  public void initialize(StoryContainer storyContainer) {
    supportFragmentManager = storyContainer.getSupportManager();
    containerId = storyContainer.getContainerId();
  }

  public void start() {
    supportFragmentManager.beginTransaction()
        .add(containerId, SearchFragment.newInstance())
        .commit();
  }

  public void restoreState(Bundle savedState) {
    savedState.getParcelable("KEY_STATE");
  }

  public void saveState(Bundle outState) {
    outState.putParcelable("KEY_STATE", null);
  }

  public DemoStoryState getStoryState() {
    return storyState;
  }
}
