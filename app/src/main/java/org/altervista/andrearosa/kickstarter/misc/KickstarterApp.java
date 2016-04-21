package org.altervista.andrearosa.kickstarter.misc;

import android.app.Application;

/**
 * Created by andre on 18/04/16.
 *
 * kickstarter-android.
 */
public class KickstarterApp extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
