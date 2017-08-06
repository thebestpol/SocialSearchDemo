package com.socialsearch.core.executor;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * SocialSearchDemo
 * com.socialsearch.core.executor
 * MainThread
 */

public class MainThread {

  public Scheduler getScheduler() {
    return AndroidSchedulers.mainThread();
  }
}
