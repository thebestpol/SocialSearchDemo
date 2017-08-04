package com.socialsearch.main;

import com.socialsearch.MainActivity;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * SocialSearchDemo
 * com.socialsearch.main
 * DemoUserStoryShould
 */
public class DemoUserStoryShould {

  @Test public void obtain_fragment_manager() {
    MainActivity mockMainActivity = mock(MainActivity.class);
    DemoUserStory demoUserStory = new DemoUserStory();

    demoUserStory.initialize(mockMainActivity);

    verify(mockMainActivity).getSupportFragmentManager();
  }
}