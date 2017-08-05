package com.socialsearch.main;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import com.socialsearch.R;
import com.socialsearch.application.di.DemoApplicationComponent;
import com.socialsearch.core.di.HasComponent;
import com.socialsearch.core.story.StoryContainer;
import com.socialsearch.main.di.MainComponent;
import com.socialsearch.main.di.MainModule;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity
    implements StoryContainer, HasComponent<MainComponent> {

  @Inject DemoUserStory demoUserStory;
  private MainComponent mainComponent;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initializeInjector();

    demoUserStory.initialize(this);

    if (savedInstanceState == null) {
      demoUserStory.start();
    } else {
      demoUserStory.restoreState(savedInstanceState);
    }
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);

    demoUserStory.saveState(outState);
  }

  private void initializeInjector() {
    mainComponent = ((HasComponent<DemoApplicationComponent>) getApplication()).getComponent()
        .createMainComponent(new MainModule());
    mainComponent.inject(this);
  }

  @IdRes @Override public int getContainerId() {
    return R.id.container;
  }

  @Override public FragmentManager getSupportManager() {
    return getSupportFragmentManager();
  }

  @Override public MainComponent getComponent() {
    return mainComponent;
  }
}
