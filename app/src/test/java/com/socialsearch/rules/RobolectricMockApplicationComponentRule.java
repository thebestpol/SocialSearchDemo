package com.socialsearch.rules;

import com.socialsearch.application.DemoApplication;
import com.socialsearch.application.di.DemoApplicationComponent;
import com.socialsearch.application.di.DemoApplicationModule;
import it.cosenonjaviste.daggermock.DaggerMockRule;
import org.robolectric.RuntimeEnvironment;

/**
 * SocialSearchDemo
 * com.socialsearch.rules
 * RobolectricMockApplicationComponentRule
 */

public class RobolectricMockApplicationComponentRule
    extends DaggerMockRule<DemoApplicationComponent> {

  public RobolectricMockApplicationComponentRule() {
    super(DemoApplicationComponent.class, new DemoApplicationModule(getApp()));
    set(component -> getApp().setComponent(component));
  }

  private static DemoApplication getApp() {
    return ((DemoApplication) RuntimeEnvironment.application);
  }
}
