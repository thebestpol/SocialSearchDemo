package com.socialsearch.rule;

import android.support.test.InstrumentationRegistry;
import com.socialsearch.application.DemoApplication;
import com.socialsearch.application.di.DemoApplicationComponent;
import com.socialsearch.application.di.DemoApplicationModule;
import it.cosenonjaviste.daggermock.DaggerMockRule;

/**
 * SocialSearchDemo
 * com.socialsearch.rule
 * EspressoMockComponentRule
 */

public class EspressoMockComponentRule extends DaggerMockRule<DemoApplicationComponent> {

  public EspressoMockComponentRule() {
    super(DemoApplicationComponent.class, new DemoApplicationModule(getApp()));
    set(component -> getApp().setComponent(component));
  }

  private static DemoApplication getApp() {
    return (DemoApplication) InstrumentationRegistry.getInstrumentation()
        .getTargetContext()
        .getApplicationContext();
  }
}