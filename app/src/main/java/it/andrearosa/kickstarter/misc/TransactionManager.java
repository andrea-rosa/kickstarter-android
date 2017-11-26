package it.andrearosa.kickstarter.misc;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import it.andrearosa.kickstarter.R;
import it.andrearosa.kickstarter.fragments.BaseFragment;

/**
 * Created by andrea on 24/03/17.
 * kickstarter-android
 */

public class TransactionManager {

    private static TransactionManager instance = null;
    private FragmentManager fm;

    private TransactionManager(FragmentManager fm) {
        this.fm = fm;
    }

    public static Builder with(FragmentManager fm) {
        if (fm == null) {
            throw new IllegalArgumentException("FragmentManager == null");
        }
        return new Builder(fm);
    }

    public static class Builder {
        private BaseFragment baseFragment;
        private int resId;
        private String tag;
        private Bundle bundle;
        private boolean inStack;
        private boolean animated;
        private int enter = R.anim.enter_from_left;
        private int exit = R.anim.exit_to_right;
        private int popEnter = R.anim.enter_from_right;
        private int popExit = R.anim.exit_to_left;

        public Builder(FragmentManager fm) {
            synchronized (TransactionManager.class) {
                if (instance == null) {
                    instance = new TransactionManager(fm);
                } else {
                    instance.fm = fm;
                }
            }
        }

        public Builder add(BaseFragment baseFragment) {
            this.baseFragment = baseFragment;
            return this;
        }

        public Builder tag(String tag) {
            this.tag = tag;
            return this;
        }

        public Builder bundle(Bundle bundle) {
            this.bundle = bundle;
            return this;
        }

        public Builder inStack(boolean inStack) {
            this.inStack = inStack;
            return this;
        }

        public Builder animated(boolean animated) {
            this.animated = animated;
            return this;
        }

        public Builder animation(int enter, int exit, int popEnter, int popExit) {
            this.enter = enter;
            this.exit = exit;
            this.popEnter = popEnter;
            this.popExit = popExit;
            this.animated = true;
            return this;
        }

        public void into(int resId) {
            this.resId = resId;
            performTransaction(this);
        }
    }

    private static void performTransaction(Builder builder) {
        if (instance == null) {
            throw new IllegalStateException("instance == null");
        }
        if (builder.baseFragment == null) {
            throw new IllegalArgumentException("baseFragment == null");
        }
        if (builder.tag == null) {
            builder.tag = builder.baseFragment.getClass().getName();
        }
        if (builder.resId == 0) {
            throw new IllegalArgumentException("resId == 0");
        }
        if (instance.fm.findFragmentByTag(builder.tag) == null) {
            FragmentTransaction ft = instance.fm.beginTransaction();
            builder.baseFragment.setArguments(builder.bundle);
            if (builder.animated) {
                ft.setCustomAnimations(builder.enter, builder.exit, builder.popEnter, builder.popExit);
            }
            ft.add(builder.resId, builder.baseFragment, builder.tag);
            if (builder.inStack)
                ft.addToBackStack(builder.tag);
            ft.commitAllowingStateLoss();
        }
    }
}
