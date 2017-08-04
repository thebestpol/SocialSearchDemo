package com.socialsearch.main;

import android.support.v4.app.FragmentManager;
import com.socialsearch.MainActivity;

/**
 * SocialSearchDemo
 * com.socialsearch.main
 * DemoUserStory
 */

public class DemoUserStory {

  private FragmentManager supportFragmentManager;

  public DemoUserStory() {

  }

  public void initialize(MainActivity mainActivity) {
    supportFragmentManager = mainActivity.getSupportFragmentManager();
  }
}
