package it.andrearosa.kickstarter.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import it.andrearosa.kickstarter.R;
import it.andrearosa.kickstarter.adapters.PostAdapter;
import it.andrearosa.kickstarter.dto.Post;
import it.andrearosa.kickstarter.dto.User;

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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.layout = R.layout.fragment_posts;

        View v = super.onCreateView(inflater, container, savedInstanceState);

        progressBar.setVisibility(View.VISIBLE);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

//        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST);
//        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Observable<List<User>> usersCall = restClient.getRestInterface().getUsers();
        usersCall.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(usersResponse -> {
                    users = usersResponse;

                    Log.d(TAG, "Fetched " + users.size() + " users");
                    Observable<List<Post>> postsCall = restClient.getRestInterface().getPosts();
                    postsCall.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(postsResponse -> {
                                posts = postsResponse;

                                adapter = new PostAdapter(users, posts);
                                recyclerView.setAdapter(adapter);

                                progressBar.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                                textView.setVisibility(View.GONE);

                                Log.d(TAG, "Fetched " + posts.size() + " posts");
                            }, throwable -> {
                                progressBar.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.GONE);
                                textView.setVisibility(View.VISIBLE);

                                Log.e(TAG, "Error fetching posts!", throwable);
                            });
                }, throwable -> {
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                    textView.setVisibility(View.VISIBLE);

                    Log.e(TAG, "Error fetching users!", throwable);
                });

        return v;
    }
}
