package org.altervista.andrearosa.kickstarter.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Album {

    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;

    /**
     * No args constructor for use in serialization
     */
    public Album() {
    }

    /**
     * @param id
     * @param title
     * @param userId
     */
    public Album(Integer userId, Integer id, String title) {
        this.userId = userId;
        this.id = id;
        this.title = title;
    }

    /**
     * @return The userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId The userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

}
