package org.altervista.andrearosa.kickstarter.services;

import org.altervista.andrearosa.kickstarter.dto.Post;
import org.altervista.andrearosa.kickstarter.dto.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by andre on 18/04/16.
 * <p/>
 * kickstarter-android.
 */
public interface RestInterface {

    // XXX test api
    @GET("users")
    Call<List<User>> getUsers();

    @GET("posts")
    Call<List<Post>> getPosts();

    // TODO
}
