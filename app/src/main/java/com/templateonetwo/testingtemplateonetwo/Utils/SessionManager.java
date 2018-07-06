package com.templateonetwo.testingtemplateonetwo.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;

import com.firebase.ui.auth.data.model.User;
import com.google.gson.Gson;
import com.templateonetwo.testingtemplateonetwo.Models.UserModel;

public class SessionManager {

    // Shared Preferences
    public static SharedPreferences pref;

    // Editor for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    private Location currentLocation;
    private String currentSerial="v";

    private boolean isInside=false;



    // Sharedpref file name

    public static final String PREF_NAME = "LEAVE_MANAGEMENT_PREF";
    public static final String KEY_USERID = "userid";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_USERMASTERCODE = "usercode";
    public static final String KEY_USERLONGITUDE = "longitude";
    public static final String KEY_USERLATITUDE = "latitude";

    public final String KEY_NAME = "name";
    public final String KEY_IS_LOGIN = "is_login";
    public final String KEY_DISTANCE = "distance";
    public final String KEY_SERIALID = "serial";
    public static final String KEY_PROFILE = "profile";


    // SessionManager Object
    static SessionManager sessionObj;


    // Constructor
    private SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        editor.commit();


    }

    public static SessionManager NewInstance(Context context) {
        if (sessionObj == null)
            sessionObj = new SessionManager(context);
        return sessionObj;
    }


    public void setLoginDetails(String id, String password, String name, String userCode) {
        editor.putString(KEY_USERID, id);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_USERMASTERCODE, userCode);
        editor.commit();
    }



    public void setLogin(boolean b) {
        editor.putBoolean(KEY_IS_LOGIN, b).commit();
    }

    public boolean isLogin() {
        return pref.getBoolean(KEY_IS_LOGIN, false);
    }

    public void setUserCode(String b) {
        editor.putString(KEY_USERMASTERCODE, b).commit();
    }

    public String getUserCode() {
        return pref.getString(KEY_USERMASTERCODE,"");
    }

    public String getUserId() {
        return pref.getString(KEY_USERID,"");
    }

    public void setLongitude(String b) {
        editor.putString(KEY_USERLONGITUDE, b).commit();
    }

    public void setLatitude(String b) {
        editor.putString(KEY_USERLATITUDE, b).commit();
    }

    public String getLatitude() {
        return pref.getString(KEY_USERLATITUDE, "null");
    }

    public String getLongitude() {
        return pref.getString(KEY_USERLONGITUDE, "null");
    }

    public void setDistance(Float b) {
        editor.putFloat(KEY_DISTANCE, b).commit();
    }

    public Float getDistance() {
        return pref.getFloat(KEY_DISTANCE, 2987.00f);
    }




    public void setLatitudea(String a) {
        editor.putString("av",a).commit();
    }

    public String getLongitudea() {
        return pref.getString("av", "f");
    }

   public void setCurrentLocation(Location location)
   {
       currentLocation=location;
   }

    public Location getCurrentLocation()
    {
        return currentLocation;
    }


    public void setSerial(String a) {
        editor.putString(KEY_SERIALID,a).commit();
    }

    public String getSerial() {
        return pref.getString(KEY_SERIALID, "");
    }

    public void setCurrentSerial(String a) {
        currentSerial=a;
    }

    public String getCurrentSerial() {
        return currentSerial;
    }

    public void setName(String b) {
        editor.putString(KEY_NAME, b).commit();
    }

    public String getName() {
        return pref.getString(KEY_NAME,"");
    }

    public  boolean getIsInside()
    {
        return  isInside;

    }

    public void setIsInside(boolean val)
    {
        isInside=val;
    }


    public  void saveProfileData(Context context, UserModel profilePojo) {
        Gson gson = new Gson();
        String json = gson.toJson(profilePojo);
        editor.putString(KEY_PROFILE,json);

    }

    public  UserModel getProfileData(Context context) {
        Gson gson = new Gson();
        String json = pref.getString(KEY_PROFILE,"");
        return gson.fromJson(json, UserModel.class);
    }

}
