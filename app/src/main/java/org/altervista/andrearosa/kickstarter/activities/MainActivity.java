package org.altervista.andrearosa.kickstarter.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.otto.Subscribe;

import org.altervista.andrearosa.kickstarter.R;
import org.altervista.andrearosa.kickstarter.events.MainThreadBus;
import org.altervista.andrearosa.kickstarter.events.TitleEvent;
import org.altervista.andrearosa.kickstarter.fragments.PostsFragment;
import org.altervista.andrearosa.kickstarter.misc.KickstarterApp;
import org.altervista.andrearosa.kickstarter.misc.Utils;
import org.altervista.andrearosa.kickstarter.services.RestClient;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;

/**
 * Created by andre on 18/04/16.
 *
 * kickstarter-android.
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    RestClient restClient;
    @Inject
    MainThreadBus bus;

    private List<Call> calls = new ArrayList<>();

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        (((KickstarterApp) getApplication()).getApplicationComponent()).inject(MainActivity.this);
        unbinder = ButterKnife.bind(this);
        bus.register(this);
        setSupportActionBar(toolbar);
        Utils.fragmentTransaction(new PostsFragment(), R.id.flContent, PostsFragment.TAG, false, getSupportFragmentManager());
    }

    @Subscribe
    public void setTitle(TitleEvent event) {
        if (event != null && event.getTitle() != null)
            toolbar.setTitle(event.getTitle());
        else
            toolbar.setTitle(R.string.app_name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();

        for (Call call : calls) {
            call.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        bus.unregister(this);
    }
}
