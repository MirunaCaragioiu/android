package com.example.facturi;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    SharedPreferences sharedPreferences;
    public SharedPref(Context context){
        sharedPreferences=context.getSharedPreferences("filename",Context.MODE_PRIVATE);
    }
    public void setDarkTheme(Boolean state){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("DarkMode",state);
        editor.commit();
    }

    public Boolean loadDarkTheme(){
        Boolean state=sharedPreferences.getBoolean("DarkMode",false);
        return state;
    }

}
