package com.socialsearch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.socialsearch.application.di.DemoApplicationComponent;
import com.socialsearch.core.di.HasComponent;
import com.socialsearch.main.DemoUserStory;
import com.socialsearch.main.di.MainModule;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

  @Inject DemoUserStory demoUserStory;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initializeInjector();

    demoUserStory.initialize(this);
    demoUserStory.start();
  }

  private void initializeInjector() {
    ((HasComponent<DemoApplicationComponent>) getApplication()).getComponent()
        .createMainComponent(new MainModule())
        .inject(this);
  }
}
