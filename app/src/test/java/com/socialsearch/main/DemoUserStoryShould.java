package com.socialsearch.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.socialsearch.R;
import com.socialsearch.main.state.DemoStoryState;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    verify(mockMainActivity).getSupportManager();
    verify(mockMainActivity).getContainerId();
  }

  @Test public void add_initial_screen_fragment_on_start() {
    FragmentTransaction mockFragmentTransaction = mock(FragmentTransaction.class);
    when(mockFragmentTransaction.add(anyInt(), any(Fragment.class))).thenReturn(
        mockFragmentTransaction);

    FragmentManager mockFragmentManager = mock(FragmentManager.class);
    when(mockFragmentManager.beginTransaction()).thenReturn(mockFragmentTransaction);

    MainActivity mockMainActivity = mock(MainActivity.class);
    when(mockMainActivity.getSupportManager()).thenReturn(mockFragmentManager);
    when(mockMainActivity.getContainerId()).thenReturn(R.id.container);

    DemoUserStory demoUserStory = new DemoUserStory();
    demoUserStory.initialize(mockMainActivity);

    demoUserStory.start();

    verify(mockFragmentManager).beginTransaction();
    verify(mockFragmentTransaction).add(eq(R.id.container), any(Fragment.class));
    verify(mockFragmentTransaction).commit();
  }

  @Test public void restore_user_story_state() {
    Bundle mockBundle = mock(Bundle.class);
    DemoUserStory demoUserStory = new DemoUserStory();

    demoUserStory.restoreState(mockBundle);

    verify(mockBundle).getParcelable(DemoUserStory.KEY_STATE);
  }

  @Test public void store_story_state_in_activity_outstate() {
    Bundle spyBundle = spy(new Bundle());
    doNothing().when(spyBundle).putParcelable(anyString(), Mockito.any());
    DemoStoryState spyStoryState = spy(new DemoStoryState());
    DemoUserStory spyDemoUserStory = new DemoUserStory() {
      @Override public DemoStoryState initializeStoryState() {
        return spyStoryState;
      }
    };

    spyDemoUserStory.saveState(spyBundle);

    verify(spyBundle).putParcelable(eq(DemoUserStory.KEY_STATE), eq(spyStoryState));
  }

  @Test public void replace_search_screen_by_history_screen() {
    FragmentTransaction mockFragmentTransaction = mock(FragmentTransaction.class);
    when(mockFragmentTransaction.replace(anyInt(), any(Fragment.class))).thenReturn(
        mockFragmentTransaction);
    when(mockFragmentTransaction.addToBackStack(anyString())).thenReturn(mockFragmentTransaction);

    FragmentManager mockFragmentManager = mock(FragmentManager.class);
    when(mockFragmentManager.beginTransaction()).thenReturn(mockFragmentTransaction);

    MainActivity mockMainActivity = mock(MainActivity.class);
    when(mockMainActivity.getSupportManager()).thenReturn(mockFragmentManager);
    when(mockMainActivity.getContainerId()).thenReturn(R.id.container);

    DemoUserStory demoUserStory = new DemoUserStory();
    demoUserStory.initialize(mockMainActivity);

    demoUserStory.navigateToHistory();

    verify(mockFragmentManager).beginTransaction();
    verify(mockFragmentTransaction).replace(eq(R.id.container), any(Fragment.class));
    verify(mockFragmentTransaction).addToBackStack("HistoryFragment");
    verify(mockFragmentTransaction).commit();
  }

  @Test public void pop_backstack_on_navigates_to_search() {
    FragmentManager mockFragmentManager = mock(FragmentManager.class);

    MainActivity mockMainActivity = mock(MainActivity.class);
    when(mockMainActivity.getSupportManager()).thenReturn(mockFragmentManager);

    DemoUserStory demoUserStory = new DemoUserStory();
    demoUserStory.initialize(mockMainActivity);

    demoUserStory.navigateToSearch();

    verify(mockFragmentManager).popBackStack();
  }
}