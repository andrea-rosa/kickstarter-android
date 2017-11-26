package it.andrearosa.kickstarter.misc;

import it.andrearosa.kickstarter.activities.BaseActivity;
import it.andrearosa.kickstarter.fragments.BaseFragment;

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
