package org.altervista.andrearosa.kickstarter.misc;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import org.altervista.andrearosa.kickstarter.R;
import org.altervista.andrearosa.kickstarter.fragments.BaseFragment;

/**
 * Created by andre on 18/04/16.
 *
 * kickstarter-android.
 */
public class Utils {
    public static final String TAG = "Utils";

    public static void fragmentTransaction(@NonNull BaseFragment fragment, int fragmentId, String tag,
                                           boolean inStack, @NonNull FragmentManager fragmentManager,
                                           int enter, int exit, int popEnter, int popExit) {

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(enter, exit, popEnter, popExit);
        ft.add(fragmentId, fragment, tag);
        if (inStack)
            ft.addToBackStack(tag);
        ft.commitAllowingStateLoss();

    }

    public static void fragmentTransaction(@NonNull BaseFragment fragment, int fragmentId, String tag,
                                           boolean inStack, @NonNull FragmentManager fragmentManager) {

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        ft.add(fragmentId, fragment, tag);
        if (inStack)
            ft.addToBackStack(tag);
        ft.commitAllowingStateLoss();
    }

    public static void fragmentTransaction(@NonNull BaseFragment fragment, int fragmentId, String tag,
                                           boolean inStack, @NonNull FragmentManager fragmentManager, Bundle bundle) {

        fragment.setArguments(bundle);
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        ft.add(fragmentId, fragment, tag);
        if (inStack)
            ft.addToBackStack(tag);
        ft.commitAllowingStateLoss();
    }
}
