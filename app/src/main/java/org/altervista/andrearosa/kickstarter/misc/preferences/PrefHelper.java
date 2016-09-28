package org.altervista.andrearosa.kickstarter.misc.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;

public class PrefHelper {

    private static final String TAG = "PrefHelper";

    public static void setString(String string, String value, Context context) {
        try {
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = sharedPref.edit();
            String secret = new Gson().toJson(value, String.class);
            editor.putString(string, secret);
            editor.commit();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public static String getString(String string, Context context) {

        try {
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
            String json = sharedPref.getString(string, null);
            return new Gson().fromJson(json, String.class);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }

    public static <T> void setPreference(PrefName preference, T value, Context context) {

        try {
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
            try {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(preference.toString(), new Gson().toJson(value, preference.getType()));
                editor.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public static <T> T getPreference(PrefName preference, Context context) {

        try {
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
            try {
                String json = sharedPref.getString(preference.toString(), null);
                return (T) new Gson().fromJson(json, preference.getType());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } catch (Exception e) {
            Log.e(TAG, "" + e.getMessage());
            return null;
        }
    }

}
