package com.socialsearch.core.di;

import java.lang.annotation.Retention;
import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * SocialSearchDemo
 * com.socialsearch.core.di
 * PerFragment
 */

@Scope @Retention(RUNTIME) public @interface PerFragment {
}
