package org.altervista.andrearosa.kickstarter.misc;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Set;

/**
 * Created by andre on 08/05/16.
 * <p>
 * kickstarter-android.
 */
public class PreferenceHelper {
    public static final String TAG = "PreferenceHelper";

    public static void setString(String key, String value, Context context) {
        try {
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString(key, value);
            editor.apply();
        } catch (Exception e) {
            Log.e(TAG, "Error in setString for key: " + key + " - value: " + value);
            e.printStackTrace();
        }
    }

    public static String getString(String key, String defValue, Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(key, defValue);
    }

    public static void setInt(String key, int value, Context context) {
        try {
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt(key, value);
            editor.apply();
        } catch (Exception e) {
            Log.e(TAG, "Error in setInt for key: " + key + " - value: " + value);
            e.printStackTrace();
        }
    }

    public static int getInt(String key, int defValue, Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(key, defValue);
    }

    public static void setFloat(String key, float value, Context context) {
        try {
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = pref.edit();
            editor.putFloat(key, value);
            editor.apply();
        } catch (Exception e) {
            Log.e(TAG, "Error in setFloat for key: " + key + " - value: " + value);
            e.printStackTrace();
        }
    }

    public static float getFloat(String key, float defValue, Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getFloat(key, defValue);
    }

    public static void setLong(String key, long value, Context context) {
        try {
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = pref.edit();
            editor.putLong(key, value);
            editor.apply();
        } catch (Exception e) {
            Log.e(TAG, "Error in setLong for key: " + key + " - value: " + value);
            e.printStackTrace();
        }
    }

    public static long getLong(String key, Long defValue, Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getLong(key, defValue);
    }

    public static void setBoolean(String key, boolean value, Context context) {
        try {
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean(key, value);
            editor.apply();
        } catch (Exception e) {
            Log.e(TAG, "Error in setBoolean for key: " + key + " - value: " + value);
            e.printStackTrace();
        }
    }

    public static boolean getBoolean(String key, boolean defValue, Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key, defValue);
    }

    public static void setStringSet(String key, Set<String> values, Context context) {
        try {
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = pref.edit();
            editor.putStringSet(key, values);
            editor.apply();
        } catch (Exception e) {
            Log.e(TAG, "Error in setStringSet for key: " + key + " - values: " + values);
            e.printStackTrace();
        }
    }

    public static Set<String> getStringSet(String key, Set<String> defValues, Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getStringSet(key, defValues);
    }

}
