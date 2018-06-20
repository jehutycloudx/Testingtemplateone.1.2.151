package com.templateonetwo.testingtemplateonetwo.ServiceProviderPackage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.templateonetwo.testingtemplateonetwo.R;
import com.templateonetwo.testingtemplateonetwo.Utils.UniversalImageLoader;

public class Activity_Provider_Profile extends Fragment {

    private static final String TAG = "EditProifleFragment";

    private ImageView mProfilePhoto;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_setting_fragment_editprofile, container, false);
        mProfilePhoto = (ImageView) view.findViewById(R.id.profile_photo);


        setProfileImage();



        return view;

    }



    private void setProfileImage() {
        Log.d(TAG, "setProfileImage: setting proifle image.");
        String imgURL = "mobilemarketingwatch.com/wp-content/uploads/2016/11/android.png";
        UniversalImageLoader.setImage(imgURL, mProfilePhoto, null, "https://" );

    }

}
