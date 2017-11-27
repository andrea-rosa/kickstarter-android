package it.andrearosa.kickstarter.activities;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import it.andrearosa.kickstarter.R;
import it.andrearosa.kickstarter.dialogs.ConfirmationDialog;
import it.andrearosa.kickstarter.events.TitleEvent;
import it.andrearosa.kickstarter.fragments.HomeFragment;
import it.andrearosa.kickstarter.fragments.LoginFragment;
import it.andrearosa.kickstarter.fragments.PostsFragment;
import it.andrearosa.kickstarter.misc.TransactionManager;

/**
 * Created by andre on 18/04/16.
 * <p/>
 * kickstarter-android.
 */
public class MainActivity extends BaseActivity {

    public static final String TAG = "MainActivity";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nvView)
    NavigationView nvView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);

        EventBus.getDefault().register(this);

        toolbar.setNavigationIcon(R.drawable.ic_menu);

        nvView.setNavigationItemSelectedListener(item -> {

            clearBackStack();

            switch (item.getItemId()) {
                case R.id.nav_login_fragment:
                    TransactionManager
                            .with(getSupportFragmentManager())
                            .add(new LoginFragment())
                            .tag(LoginFragment.TAG)
                            .inStack(true)
                            .animated(true)
                            .into(R.id.flContent);
                    break;
                case R.id.nav_posts_fragment:
                    TransactionManager
                            .with(getSupportFragmentManager())
                            .add(new PostsFragment())
                            .tag(PostsFragment.TAG)
                            .inStack(true)
                            .animated(true)
                            .into(R.id.flContent);
                    break;
            }

            item.setChecked(true);
            drawerLayout.closeDrawers();
            return true;
        });

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);

        TransactionManager
                .with(getSupportFragmentManager())
                .add(new HomeFragment())
                .tag(HomeFragment.TAG)
                .into(R.id.flContent);
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
            if (imm != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
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
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.syncState();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
