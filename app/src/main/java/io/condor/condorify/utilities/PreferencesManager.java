package io.condor.condorify.utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by esteban on 7/10/17.
 */

public class PreferencesManager {

    /**
     * Set Preferences
     */

    private static SharedPreferences.Editor getPreferencesEditor(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.Preferences.PREFERENCES_NAME,
                Context.MODE_PRIVATE);
        return sharedPreferences.edit();
    }

    public static void cleanPreferences(Context context) {
        SharedPreferences.Editor editor = getPreferencesEditor(context);
        editor.clear();
        editor.commit();
    }

    public static void saveString(Context context, String key, String value) {
        SharedPreferences.Editor editor = getPreferencesEditor(context);
        editor.putString(key, value);
        editor.commit();
    }

    public static void saveInt(Context context, String key, int value) {
        SharedPreferences.Editor editor = getPreferencesEditor(context);
        editor.putInt(key, value);
        editor.commit();
    }

    public static void saveBoolean(Context context, String key, boolean value) {
        SharedPreferences.Editor editor = getPreferencesEditor(context);
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void saveFloat(Context context, String key, float value) {
        SharedPreferences.Editor editor = getPreferencesEditor(context);
        editor.putFloat(key, value);
        editor.commit();
    }

    public static void saveLong(Context context, String key, long value) {
        SharedPreferences.Editor editor = getPreferencesEditor(context);
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * Get Preferences
     */

    public static String getString(Context context, String key) {
        if ((context != null)) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.Preferences.PREFERENCES_NAME,
                    Context.MODE_PRIVATE);
            return sharedPreferences.getString(key, "");
        }
        return null;
    }

    public static int getInt(Context context, String key) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.Preferences.PREFERENCES_NAME,
                    Context.MODE_PRIVATE);
            return sharedPreferences.getInt(key, -404);
        }
        return 0;
    }

    public static boolean getBoolean(Context context, String key) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.Preferences.PREFERENCES_NAME,
                    Context.MODE_PRIVATE);
            return sharedPreferences.getBoolean(key, false);
        }
        return false;
    }

    public static float getFloat(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.Preferences.PREFERENCES_NAME,
                Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(key, -404);
    }

    public static long getLong(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.Preferences.PREFERENCES_NAME,
                Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, -404);
    }

}
