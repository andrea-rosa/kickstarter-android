package org.altervista.andrearosa.kickstarter.events.objects;

/**
 * Created by andre on 18/04/16.
 * <p/>
 * kickstarter-android.
 */
public class TitleEvent {
    private String title;

    public TitleEvent() {
    }

    public TitleEvent(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

