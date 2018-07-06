package com.templateonetwo.testingtemplateonetwo.SettingsPackage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.templateonetwo.testingtemplateonetwo.R;

import java.util.ArrayList;

public class SettingsMenuFragment extends Fragment{
    View rootView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.layout_center_settings,container,false);
        return  rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpList();
    }

    void  setUpList()
    {

        //Log.d(TAG, "setupSettingList: initilizign 'Account Settings' list.");
        ListView listView = (ListView) rootView.findViewById(R.id.lvAccountSettings);

        ArrayList<String> options = new ArrayList<>();
        options.add(getString(R.string.edit_profile_fragment)); /*i.e. fragment (0)*/
        options.add(getString(R.string.notifications_fragment)); /*i.e. fragment (1)*/
        options.add(getString(R.string.invite_friend_fragment)); /*i.e. fragment (2)*/
        options.add(getString(R.string.edit_payment_method_fragment)); /*i.e. fragment (3)*/
        options.add(getString(R.string.sign_out_fragment)); /*i.e. fragment (4)*/

        ArrayAdapter adapterforSettingList = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, options);
        listView.setAdapter(adapterforSettingList);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              //  Log.d(TAG, "onItemClick: navigating to fragment#: " + position);
                Fragment fragment=new EditProfileFragment();
                FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
                switch (position)
                {
                    case 0:
                        fragment=new EditProfileFragment();
                        break;
                    case 1:
                        fragment=new NotificationsFragment();
                        break;
                    case 2:
                        fragment=new InviteFriendsFragment();
                        break;
                    case 3:
                        fragment=new PaymentFragment();
                        break;
                    case 4:
                        fragment=new SignOutFragment();
                        break;

                }
                fragmentTransaction.replace(R.id.relLayoutMiddle,fragment,fragment.getClass().getName());
                fragmentTransaction.addToBackStack(fragment.getClass().getName());
                fragmentTransaction.commit();
                //setViewPager(position);


            }
        });



    }
}
