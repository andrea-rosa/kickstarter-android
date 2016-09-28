package org.altervista.andrearosa.kickstarter.misc.preferences;

import java.util.List;

public enum PrefName {

    CUSTOM_BOOLEAN(Boolean.class),
    CUSTOM_STRING(String.class),
    CUSTOM_LIST(List.class);

    private Class type;

    private PrefName(Class type) {
        this.type = type;
    }

    public Class getType() {
        return type;
    }

}
