package org.altervista.andrearosa.kickstarter.misc;

import android.content.Context;

import org.altervista.andrearosa.kickstarter.events.MainThreadBus;
import org.altervista.andrearosa.kickstarter.services.RestClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andre on 18/04/16.
 *
 * kickstarter-android.
 */
@Module
public class ApplicationModule {
    private static final String TAG = "ApplicationModule";

    private final KickstarterApp application;

    public ApplicationModule(KickstarterApp application) {
        this.application = application;
    }

    @Provides
    @Singleton
    @ForApplication
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    RestClient provideRestService() {
        return new RestClient(provideApplicationContext());
    }

    @Provides
    @Singleton
    MainThreadBus provideBus() {
        return new MainThreadBus();
    }
}
