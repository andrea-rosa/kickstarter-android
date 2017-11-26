package it.andrearosa.kickstarter.services;

import java.util.List;

import io.reactivex.Observable;
import it.andrearosa.kickstarter.dto.Post;
import it.andrearosa.kickstarter.dto.User;
import retrofit2.http.GET;

/**
 * Created by andre on 18/04/16.
 * <p/>
 * kickstarter-android.
 */
public interface RestInterface {

    @GET("users")
    Observable<List<User>> getUsers();

    @GET("posts")
    Observable<List<Post>> getPosts();
}
