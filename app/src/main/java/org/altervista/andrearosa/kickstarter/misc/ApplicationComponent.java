package org.altervista.andrearosa.kickstarter.misc;

import org.altervista.andrearosa.kickstarter.activities.BaseActivity;
import org.altervista.andrearosa.kickstarter.fragments.BaseFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andre on 18/04/16.
 * <p/>
 * kickstarter-android.
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(BaseActivity activity);
    void inject(BaseFragment fragment);
}
