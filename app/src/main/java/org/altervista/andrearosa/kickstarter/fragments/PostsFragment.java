package org.altervista.andrearosa.kickstarter.fragments;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.altervista.andrearosa.kickstarter.R;
import org.altervista.andrearosa.kickstarter.adapters.PostAdapter;
import org.altervista.andrearosa.kickstarter.dto.Post;
import org.altervista.andrearosa.kickstarter.dto.User;
import org.altervista.andrearosa.kickstarter.events.TitleEvent;
import org.altervista.andrearosa.kickstarter.misc.DividerItemDecoration;
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by andre on 18/04/16.
 * <p/>
 * kickstarter-android.
 */
public class PostsFragment extends BaseFragment {
    public static final String TAG = "PostsFragment";

    @BindView(R.id.posts_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.posts_progressBar)
    ProgressBar progressBar;
    @BindView(R.id.posts_emptyTextView)
    TextView textView;

    private PostAdapter adapter;
    private List<User> users = new ArrayList<>();
    private List<Post> posts = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.layout = R.layout.fragment_posts;

        View v = super.onCreateView(inflater, container, savedInstanceState);

        progressBar.setVisibility(View.VISIBLE);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST);
        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Call<List<User>> usersCall = restClient.getRestInterface().getUsers();
        usersCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                try {
                    users = response.body();
                    Call<List<Post>> postsCall = restClient.getRestInterface().getPosts();
                    postsCall.enqueue(new Callback<List<Post>>() {
                        @Override
                        public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                            try {
                                posts = response.body();

                                adapter = new PostAdapter(users, posts);
                                recyclerView.setAdapter(adapter);

                                progressBar.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                                textView.setVisibility(View.GONE);
                            } catch (Exception e) {
                                Log.e(TAG, "postsCall onResponse!");
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Post>> call, Throwable t) {
                            try {
                                progressBar.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.GONE);
                                textView.setVisibility(View.VISIBLE);
                            } catch (Exception e) {
                                Log.e(TAG, "postsCall onFailure!");
                                e.printStackTrace();
                            }
                        }
                    });
                    calls.add(postsCall);
                } catch (Exception e) {
                    Log.e(TAG, "usersCall onResponse!");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                try {
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                    textView.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                    Log.e(TAG, "usersCall onFailure!");
                    e.printStackTrace();
                }
            }
        });
        calls.add(usersCall);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().post(new TitleEvent(getString(R.string.app_name)));
    }
}
