package Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Util {

    public static void saveIntValueInPrefrences(Context context, String key, int value) {
        try {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            preferences.edit().putInt(key, value).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getIntValuePreferences(Context context, String key) {
        try {
            SharedPreferences sharedpref = PreferenceManager.getDefaultSharedPreferences(context);
            return sharedpref.getInt(key, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void saveValueInPrefrences(Context context, String key, String value) {
        try {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            preferences.edit().putString(key, value).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getValuePreferences(Context context, String key) {
        try {
            SharedPreferences sharedpref = PreferenceManager.getDefaultSharedPreferences(context);
            return sharedpref.getString(key, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




}
