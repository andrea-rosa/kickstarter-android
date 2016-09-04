package org.altervista.andrearosa.kickstarter.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import org.altervista.andrearosa.kickstarter.R;
import org.altervista.andrearosa.kickstarter.events.TitleEvent;
import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * Created by andre on 18/04/16.
 * <p/>
 * kickstarter-android.
 */
public class LoginFragment extends BaseFragment {
    public static final String TAG = "LoginFragment";

    @BindView(R.id.fragmentLogin_layout)
    LinearLayout content;
    @BindView(R.id.fragmentLogin_usernameEditText)
    TextInputEditText usernameEditText;
    @BindView(R.id.fragmentLogin_passwordEditText)
    TextInputEditText passwordEditText;
    @BindView(R.id.fragmentLogin_loginButton)
    Button loginButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.layout = R.layout.fragment_login;

        View v = super.onCreateView(inflater, container, savedInstanceState);

        content.setOnClickListener(null);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().post(new TitleEvent(getString(R.string.app_name)));
    }
}
