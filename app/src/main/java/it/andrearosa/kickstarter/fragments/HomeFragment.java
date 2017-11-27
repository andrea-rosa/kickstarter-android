package it.andrearosa.kickstarter.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import it.andrearosa.kickstarter.R;
import it.andrearosa.kickstarter.events.TitleEvent;

/**
 * Created by andre on 18/04/16.
 * <p/>
 * kickstarter-android.
 */
public class HomeFragment extends BaseFragment {
    public static final String TAG = "HomeFragment";

    @BindView(R.id.fragmentHome_textView)
    TextView textView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // !Important! Always set the layout before inflate view
        this.layout = R.layout.fragment_home;

        View v = super.onCreateView(inflater, container, savedInstanceState);
        textView.setText(getString(R.string.app_name));
        EventBus.getDefault().post(new TitleEvent(getString(R.string.app_name)));
        return v;
    }
}
