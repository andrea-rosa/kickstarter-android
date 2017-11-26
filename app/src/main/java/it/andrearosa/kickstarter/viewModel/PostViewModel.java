package it.andrearosa.kickstarter.viewModel;

import android.databinding.BaseObservable;
import android.view.View;
import android.widget.Toast;

import it.andrearosa.kickstarter.models.PostModel;

/**
 * Created by andre on 26/11/17.
 * <p>
 * kickstarter-android
 */

public class PostViewModel extends BaseObservable {

    private PostModel post;

    public PostViewModel(PostModel post) {
        this.post = post;
    }

    public String getTitle() {
        return post.title;
    }

    public String getBody() {
        return post.body;
    }

    public String getUsername() {
        return post.username;
    }

    public View.OnClickListener onPostClick() {
        return view -> Toast.makeText(view.getContext(), "You clicked on post!", Toast.LENGTH_SHORT).show();
    }
}
