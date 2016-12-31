package org.altervista.andrearosa.kickstarter.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.altervista.andrearosa.kickstarter.misc.App;
import org.altervista.andrearosa.kickstarter.services.RestClient;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;

public class BaseActivity extends AppCompatActivity {

    @Inject
    RestClient restClient;

    Unbinder unbinder;

    protected List<Call> calls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        unbinder = ButterKnife.bind(this);
        (((App) getApplication()).getApplicationComponent()).inject(BaseActivity.this);
    }

    protected void onPause() {
        super.onPause();
        for (Call call : calls) {
            call.cancel();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Utils.refreshToken(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public RestClient getRestClient() {
        return restClient;
    }
}
