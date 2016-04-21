package org.altervista.andrearosa.kickstarter.misc;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by andre on 18/04/16.
 *
 * kickstarter-android.
 */
@Qualifier
@Retention(RUNTIME)
public @interface ForApplication {
}

