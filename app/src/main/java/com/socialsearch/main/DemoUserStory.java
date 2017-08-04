package com.socialsearch.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.socialsearch.MainActivity;
import com.socialsearch.R;

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

  public void start() {
    supportFragmentManager.beginTransaction().add(R.id.container, new Fragment()).commit();
  }
}
