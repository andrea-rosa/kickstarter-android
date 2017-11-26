package it.andrearosa.kickstarter.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import it.andrearosa.kickstarter.R;
import it.andrearosa.kickstarter.databinding.AdapterPostBinding;
import it.andrearosa.kickstarter.dto.Post;
import it.andrearosa.kickstarter.dto.User;
import it.andrearosa.kickstarter.models.PostModel;
import it.andrearosa.kickstarter.viewModel.PostViewModel;

/**
 * Created by andre on 07/06/16.
 * <p/>
 * kickstarter-android.
 */
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.BindingHolder> {

    public static final String TAG = "PostAdapter";

    private List<Post> posts = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private AdapterPostBinding binding;

        public BindingHolder(AdapterPostBinding binding) {
            super(binding.postAdapterLayout);
            this.binding = binding;
        }
    }


    public PostAdapter(List<User> users, List<Post> posts) {
        this.users = users;
        this.posts = posts;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterPostBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.adapter_post,
                parent,
                false
        );
        return new BindingHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        AdapterPostBinding binding = holder.binding;
        PostModel post = new PostModel();
        post.body = posts.get(position).body;
        post.title = posts.get(position).title;
        post.username = findById(posts.get(position).userId).username;
        binding.setViewModel(new PostViewModel(post));
    }

    private User findById(Integer userId) {
        User u = null;
        for (User usr : users) {
            if (usr.id.equals(userId)) {
                u = usr;
                break;
            }
        }
        return u;
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


}
