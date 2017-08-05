package com.socialsearch.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.socialsearch.core.story.StoryContainer;

/**
 * SocialSearchDemo
 * com.socialsearch.main
 * DemoUserStory
 */

public class DemoUserStory {

  private FragmentManager supportFragmentManager;
  private int containerId;

  public DemoUserStory() {

  }

  public void initialize(StoryContainer storyContainer) {
    supportFragmentManager = storyContainer.getSupportManager();
    containerId = storyContainer.getContainerId();
  }

  public void start() {
    supportFragmentManager.beginTransaction().add(containerId, new Fragment()).commit();
  }

  public void restoreState(Bundle savedState) {
    savedState.getParcelable("KEY_STATE");
  }

  public void saveState(Bundle outState) {
    outState.putParcelable("KEY_STATE", null);
  }
}
