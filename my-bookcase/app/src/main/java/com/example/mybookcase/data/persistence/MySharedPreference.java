package com.example.mybookcase.data.persistence;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreference {

    public static final String USER_NAME = "USER_NAME";
    private static MySharedPreference sInstance;
    private static Context mContext;
    private static final String PREFERENCE_NAME = "MINHAS_PREFERENCIAS";

    private MySharedPreference() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static MySharedPreference getInstance(Context context) {
        if (sInstance == null) {
            mContext = context;
            sInstance = new MySharedPreference();
        }
        return sInstance;
    }

    private static SharedPreferences myPreferences() {
        return mContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public void setUserName(String name) {
        myPreferences().edit().putString(USER_NAME, name).commit();
    }

    public String getUserName() {
        return myPreferences().getString("USER_NAME", "");
    }

    public void cleanUserName() {
        myPreferences().edit().remove("USER_NAME");
    }

    public void setUserMatricula(int matricula) {
        myPreferences().edit().putInt("USER_MAT", matricula).commit();
    }

    public int getUserMatricula() {
        return myPreferences().getInt("USER_MAT", 0);
    }
}
