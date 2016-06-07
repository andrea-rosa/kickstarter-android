package org.altervista.andrearosa.kickstarter.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.altervista.andrearosa.kickstarter.R;
import org.altervista.andrearosa.kickstarter.dto.Post;
import org.altervista.andrearosa.kickstarter.dto.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andre on 07/06/16.
 * <p/>
 * kickstarter-android.
 */
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    public static final String TAG = "PostAdapter";

    private List<Post> posts = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public PostAdapter(List<User> users, List<Post> posts) {
        this.users = users;
        this.posts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_post, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            Post post = posts.get(position);
            User user = findById(post.getUserId());
            holder.title.setText(post.getTitle());
            holder.body.setText(post.getBody());
            holder.author.setText(user.getUsername());
        } catch (Exception e) {
            Log.e(TAG, "Error in onBindViewHolder");
            e.printStackTrace();
        }
    }

    private User findById(Integer userId) {
        User u = null;
        for (User usr : users) {
            if (usr.getId().equals(userId)) {
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView body;
        public TextView author;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.postTitle_textView);
            body = (TextView) v.findViewById(R.id.postBody_textView);
            author = (TextView) v.findViewById(R.id.postAuthor_textView);
        }
    }


}
