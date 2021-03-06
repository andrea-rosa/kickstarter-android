package it.andrearosa.kickstarter.misc;

import android.app.Application;

import com.orhanobut.hawk.Hawk;

/**
 * Created by andre on 18/04/16.
 * <p/>
 * kickstarter-android.
 */
public class App extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();

        Hawk.init(getApplicationContext()).build();
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
