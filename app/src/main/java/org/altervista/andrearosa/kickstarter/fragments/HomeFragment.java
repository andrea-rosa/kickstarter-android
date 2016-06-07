package org.altervista.andrearosa.kickstarter.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.altervista.andrearosa.kickstarter.R;
import org.altervista.andrearosa.kickstarter.events.TitleEvent;

import butterknife.Bind;

/**
 * Created by andre on 18/04/16.
 *
 * kickstarter-android.
 */
public class HomeFragment extends BaseFragment {
    public static final String TAG = "HomeFragment";

    @Bind(R.id.fragmentHome_textView)
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // !Important! Always set the layout before inflate view
        this.layout = R.layout.fragment_home;

        View v = super.onCreateView(inflater, container, savedInstanceState);
        textView.setText(getString(R.string.app_name));
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        bus.post(new TitleEvent(getString(R.string.app_name)));
    }
}
