package org.altervista.andrearosa.kickstarter.activities;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import org.altervista.andrearosa.kickstarter.R;
import org.altervista.andrearosa.kickstarter.dialogs.ConfirmationDialog;
import org.altervista.andrearosa.kickstarter.events.TitleEvent;
import org.altervista.andrearosa.kickstarter.fragments.HomeFragment;
import org.altervista.andrearosa.kickstarter.fragments.LoginFragment;
import org.altervista.andrearosa.kickstarter.fragments.PostsFragment;
import org.altervista.andrearosa.kickstarter.misc.KickstarterApp;
import org.altervista.andrearosa.kickstarter.misc.Utils;
import org.altervista.andrearosa.kickstarter.services.RestClient;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;

/**
 * Created by andre on 18/04/16.
 * <p/>
 * kickstarter-android.
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nvView)
    NavigationView nvView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private ActionBarDrawerToggle drawerToggle;

    @Inject
    RestClient restClient;

    private List<Call> calls = new ArrayList<>();

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        (((KickstarterApp) getApplication()).getApplicationComponent()).inject(MainActivity.this);
        unbinder = ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        EventBus.getDefault().register(this);

        toolbar.setNavigationIcon(R.drawable.ic_menu);

        nvView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                clearBackStack();

                switch (item.getItemId()) {
                    case R.id.nav_login_fragment:
                        Utils.fragmentTransaction(new LoginFragment(), R.id.flContent, LoginFragment.TAG, true, getSupportFragmentManager());
                        break;
                    case R.id.nav_posts_fragment:
                        Utils.fragmentTransaction(new PostsFragment(), R.id.flContent, PostsFragment.TAG, true, getSupportFragmentManager());
                        break;
                }

                item.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);

        Utils.fragmentTransaction(new HomeFragment(), R.id.flContent, HomeFragment.TAG, false, getSupportFragmentManager());
    }

    @Subscribe
    public void titleEvent(TitleEvent event) {
        if (event.getTitle() != null && toolbar != null)
            toolbar.setTitle(event.getTitle());
        else if (toolbar != null)
            toolbar.setTitle(getString(R.string.app_name));
    }

    private void clearBackStack() {
        FragmentManager fm = getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                ConfirmationDialog dialog = ConfirmationDialog.newInstance(
                        "Title",
                        "You clicked on settings.",
                        "OK",
                        "Cancel",
                        new ConfirmationDialog.DialogListener() {
                            @Override
                            public void onConfirm() {
                                Toast.makeText(getApplicationContext(), "Clicked on OK", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancel() {
                                Toast.makeText(getApplicationContext(), "Clicked on Cancel", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                dialog.show(getSupportFragmentManager(), "DIALOG");
                return true;

            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    public void hideKeyboard() {
        if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawers();
        else {
            super.onBackPressed();
            hideKeyboard();
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                if (nvView != null && nvView.getMenu() != null)
                    nvView.getMenu().getItem(0).setChecked(true);
            }
        }
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.syncState();
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
        EventBus.getDefault().unregister(this);
    }
}
