package com.templateonetwo.testingtemplateonetwo.SettingsPackage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.templateonetwo.testingtemplateonetwo.R;

public class PaymentFragment extends Fragment {

    private static final String TAG = "PaymentFragment";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_method, container, false);




        return view;

    }



}
