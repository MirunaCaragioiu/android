package com.example.schimbcase;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    private SharedPreferences mySharedPref;

    public SharedPref(Context context){
        mySharedPref=context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
    }

    public void setUserDate(String date){
        SharedPreferences.Editor editor=mySharedPref.edit();
        editor.putString("data curenta",date);
        editor.commit();
    }

    public String getUsertDate(){
        String date=mySharedPref.getString("data curenta",null);
        return date;
    }
}
