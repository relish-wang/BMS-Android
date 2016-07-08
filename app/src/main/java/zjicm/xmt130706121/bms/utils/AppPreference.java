package zjicm.xmt130706121.bms.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import zjicm.xmt130706121.bms.App;


@SuppressWarnings("unused")
public class AppPreference {
    private AppPreference() {}
    public static void put(String key, int value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.CONTEXT);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void put(String key, long value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.CONTEXT);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static void put(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.CONTEXT);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void put(String key, boolean value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.CONTEXT);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void put(String key, float value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.CONTEXT);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public static int getInt(String key, int defValue) {
        return PreferenceManager.getDefaultSharedPreferences(App.CONTEXT).getInt(key, defValue);
    }

    public static float getFloat(String key, float defValue) {
        return PreferenceManager.getDefaultSharedPreferences(App.CONTEXT).getFloat(key, defValue);
    }

    public static long getLong(String key, long defValue) {
        return PreferenceManager.getDefaultSharedPreferences(App.CONTEXT).getLong(key, defValue);
    }

    public static String getString(String key, String defValue) {
        return PreferenceManager.getDefaultSharedPreferences(App.CONTEXT).getString(key, defValue);
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return PreferenceManager.getDefaultSharedPreferences(App.CONTEXT).getBoolean(key, defValue);
    }

    /**
     * @return 如果该“键”下有值则返回true，否则返回false
     */
    public static boolean contains(String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.CONTEXT);
        return sharedPreferences.contains(key);
    }

    /**
     * 清空所有数据
     */
    public static void clear() {
        SharedPreferences.Editor editor = PreferenceManager.
                getDefaultSharedPreferences(App.CONTEXT).edit();
        editor.clear();
        editor.apply();
    }

    /**
     * 根据键值删除数据
     */
    public static void remove(String... keys) {
        if (keys != null) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.CONTEXT);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            for (String key : keys) {
                editor.remove(key);
            }
            editor.apply();
        }
    }
}
