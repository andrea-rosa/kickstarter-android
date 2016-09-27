package org.altervista.andrearosa.kickstarter.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.altervista.andrearosa.kickstarter.R;

import butterknife.BindView;

/**
 * Created by andre on 18/04/16.
 * <p/>
 * kickstarter-android.
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener {
    public static final String TAG = "LoginFragment";

    @BindView(R.id.fragmentLogin_layout)
    LinearLayout content;
    @BindView(R.id.fragmentLogin_usernameEditText)
    TextInputEditText usernameEditText;
    @BindView(R.id.fragmentLogin_passwordEditText)
    TextInputEditText passwordEditText;
    @BindView(R.id.fragmentLogin_loginButton)
    Button loginButton;
    @BindView(R.id.fragmentLogin_rememberMeCheckBox)
    CheckBox rememberMeCheckBox;
    @BindView(R.id.fragmentLogin_forgotPasswordButton)
    Button forgotPasswordButton;
    @BindView(R.id.fragmentLogin_registerButton)
    Button registerButton;

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

        forgotPasswordButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), "Nothing happened =)", Toast.LENGTH_SHORT).show();
    }
}
