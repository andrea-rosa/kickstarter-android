package org.altervista.andrearosa.kickstarter.misc;

import org.altervista.andrearosa.kickstarter.activities.MainActivity;
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
    void inject(MainActivity activity);

    void inject(BaseFragment fragment);
}
