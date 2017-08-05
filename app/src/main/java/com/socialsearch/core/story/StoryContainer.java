package com.socialsearch.core.story;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;

/**
 * SocialSearchDemo
 * com.socialsearch.core.story
 * StoryContainer
 */

public interface StoryContainer {

  @IdRes int getContainerId();

  FragmentManager getSupportManager();
}
