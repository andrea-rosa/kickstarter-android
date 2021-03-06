package it.andrearosa.kickstarter.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import it.andrearosa.kickstarter.misc.App;
import it.andrearosa.kickstarter.services.RestClient;

/**
 * Created by andre on 18/04/16.
 * <p/>
 * kickstarter-android.
 */
public class BaseFragment extends Fragment {

    @Inject
    RestClient restClient;

    protected int layout = 0;

    private Unbinder unbinder;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (layout == 0)
            throw new IllegalStateException("Layout must be initialized first!");
        View v = inflater.inflate(layout, container, false);
        unbinder = ButterKnife.bind(this, v);
        (((App) getActivity().getApplication()).getApplicationComponent()).inject(BaseFragment.this);
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
        unbinder.unbind();
    }
}
