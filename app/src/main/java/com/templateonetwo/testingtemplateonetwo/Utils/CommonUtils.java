package com.templateonetwo.testingtemplateonetwo.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class CommonUtils {

    public static void setStringSharedPref(Context p_context, String p_spKey, String p_value) {

       SharedPreferences sharedPreferences=p_context.getSharedPreferences("a",Context.MODE_PRIVATE);
       SharedPreferences.Editor editor=sharedPreferences.edit();
       editor.putString(p_spKey,p_value);
       editor.commit();
       // we are saving the data permanently in shared prefrences in the form of string key value pairs here;
    }

    public static void saveFragmentData(Context context, FragmentModelDataPasssing fragmentModelDataPasssing) {
        Gson gson = new Gson();
        String json = gson.toJson(fragmentModelDataPasssing);
        CommonUtils.setStringSharedPref(context, "data", json);

        // we are converting our java object into jsonformat so as to save the data in a single
        // shared prefrence in the form of key/value pair.
    }

    public static FragmentModelDataPasssing getFragmentData(Context context) {
        Gson gson = new Gson();
        String json = CommonUtils.getStringSharedPref(context, "data", "");
        if(json!=null)
        return gson.fromJson(json, FragmentModelDataPasssing.class);
        else
            return  new FragmentModelDataPasssing();
    }

    public static String getStringSharedPref(Context p_context, String p_spKey, String p_value) {
        SharedPreferences sharedPreferences=p_context.getSharedPreferences("a",Context.MODE_PRIVATE);
        return sharedPreferences.getString("data",null);
    }



}
