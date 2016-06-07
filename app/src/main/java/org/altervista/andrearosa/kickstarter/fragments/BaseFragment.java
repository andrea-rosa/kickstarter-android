package org.altervista.andrearosa.kickstarter.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.altervista.andrearosa.kickstarter.events.MainThreadBus;
import org.altervista.andrearosa.kickstarter.misc.KickstarterApp;
import org.altervista.andrearosa.kickstarter.services.RestClient;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import retrofit2.Call;

/**
 * Created by andre on 18/04/16.
 *
 * kickstarter-android.
 */
public class BaseFragment extends Fragment {

    @Inject
    RestClient restClient;

    @Inject
    MainThreadBus bus;

    protected int layout = 0;

    protected List<Call> calls = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(layout == 0)
            throw new IllegalArgumentException("Layout must be initialized first!");
        View v = inflater.inflate(layout, container, false);
        ButterKnife.bind(this, v);
        (((KickstarterApp) getActivity().getApplication()).getApplicationComponent()).inject(BaseFragment.this);
        bus.register(this);
        try {
            v.getRootView().setOnClickListener(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (Call call : calls) {
            call.cancel();
        }
        ButterKnife.unbind(this);
        bus.unregister(this);
    }
}
