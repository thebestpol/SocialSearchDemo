package com.socialsearch.core.presenter;

/**
 * SocialSearchDemo
 * com.socialsearch.core.presenter
 * Presenter
 */

public abstract class Presenter<V> {

  protected V view;

  public void setView(V view) {
    this.view = view;
  }

  public abstract void start();

  public abstract void stop();
}
