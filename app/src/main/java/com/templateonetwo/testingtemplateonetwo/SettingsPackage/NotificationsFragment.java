package com.templateonetwo.testingtemplateonetwo.SettingsPackage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.templateonetwo.testingtemplateonetwo.R;

public class NotificationsFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "NotificationsFragment";

    Button notificationButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);




        return view;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        notificationButton=view.findViewById(R.id.notification_button);
        notificationButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.notification_button:
                Toast.makeText(getActivity(), "Notification Button Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
