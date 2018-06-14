package com.templateonetwo.testingtemplateonetwo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.templateonetwo.testingtemplateonetwo.SettingsPackage.EditProfileFragment;
import com.templateonetwo.testingtemplateonetwo.SettingsPackage.InviteFriendsFragment;
import com.templateonetwo.testingtemplateonetwo.SettingsPackage.NotificationsFragment;
import com.templateonetwo.testingtemplateonetwo.SettingsPackage.PaymentFragment;
import com.templateonetwo.testingtemplateonetwo.SettingsPackage.SignOutFragment;
import com.templateonetwo.testingtemplateonetwo.Utils.SectionsPagerAdapter;
import com.templateonetwo.testingtemplateonetwo.Utils.SectionsStatePagerAdapter;

import java.util.ArrayList;

public class Activity_Settings extends AppCompatActivity {

    private static final String TAG = "Activity_Settings";
    private static final int ACTIVITY_NUM = 4;
    private Context mContext = Activity_Settings.this;
    private SectionsStatePagerAdapter pagerAdapter;
    private ViewPager mViewPager;
    private RelativeLayout mRelativeLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_settings);
        Log.d(TAG, "onCreate: started.");
     // TextView settings_title = (TextView) findViewById(R.id.activityTitle_Settings);
        mViewPager = (ViewPager) findViewById(R.id.settingscontainer);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relLayout1); //* may need to Change THIS*//

     /* setting up ellipses profile menu "ellipses",
        not setting up tool bar 14:18 (Account Setting Layout Part 10 - Instagram Clone)*/
        ImageView profileMenu = (ImageView) findViewById(R.id.profileMenu);
        profileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Log.d(TAG, "onClick: navigating to account setting.");}



        });


/////////////////////////////*Bottom Navigation Begin*/////////////////////////////////////////////////////


        /*code below is to specifically disable shiftmode on bottom nav bar*/
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        /*simple code to fix the highlighting of the appropriate bottom nav icon when tapped, menu item index is set to '4' for first icon*/
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(4);
        menuItem.setChecked(true);

        /*below you will need to navigate to different areas when the nav buttons are pressed and you
          will need to use switch statements, breaks, and the code below */
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.ic_new_project:
                        Intent intent0 = new Intent(Activity_Settings.this, MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.ic_current_listings:
                        Intent intent1 = new Intent(Activity_Settings.this, Activity_Current_Listings.class);
                        startActivity(intent1);
                        break;


                    case R.id.ic_find_contractor:
                        Intent intent2 = new Intent(Activity_Settings.this, Activity_Find_Contractors.class);
                        startActivity(intent2);
                        break;


                    case R.id.ic_chat:
                        Intent intent4 = new Intent(Activity_Settings.this, Activity_Chat.class);
                        startActivity(intent4);
                        break;


                    case R.id.ic_settings:

                        break;
                }
                return false;
            }
        });

/////////////////////////////////////*Bottom Navigation Bar end*/////////////////////////////////////////////////////

        //*Need to commit this method nested inside of onCreate, otherwise won't show up*/
        setupSettingList();
        //*Need to commit this method nested inside of onCreate, otherwise won't show up for fragments*/
        setupFragments();
    }

    /////Setting up Listview in setting //////
    private void setupSettingList(){

        Log.d(TAG, "setupSettingList: initilizign 'Account Settings' list.");
        ListView listView = (ListView) findViewById(R.id.lvAccountSettings);

        ArrayList<String> options = new ArrayList<>();
        options.add(getString(R.string.edit_profile_fragment)); /*i.e. fragment (0)*/
        options.add(getString(R.string.notifications_fragment)); /*i.e. fragment (1)*/
        options.add(getString(R.string.invite_friend_fragment)); /*i.e. fragment (2)*/
        options.add(getString(R.string.edit_payment_method_fragment)); /*i.e. fragment (3)*/
        options.add(getString(R.string.sign_out_fragment)); /*i.e. fragment (4)*/

        ArrayAdapter adapterforSettingList = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, options);
        listView.setAdapter(adapterforSettingList);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: navigating to fragment#: " + position);
                setViewPager(position);


            }
        });



    }

    private void setupFragments(){
        /*Change which fragment shows up after .addFragment in the parameters,
    the first parameter in the () i.e. 'new EditProfileFragment' seems to make the decision for which
     fragment will appear, not so sure of importance of second parameter 'getString...' other then filling out parameters*/

        pagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new EditProfileFragment(), getString(R.string.edit_profile_fragment)); /*i.e. fragment (0)*/
        pagerAdapter.addFragment(new NotificationsFragment(), getString(R.string.notifications_fragment)); /*i.e. fragment (1)*/
        pagerAdapter.addFragment(new InviteFriendsFragment(), getString(R.string.invite_friend_fragment)); /*i.e. fragment (2)*/
        pagerAdapter.addFragment(new PaymentFragment(), getString(R.string.edit_payment_method_fragment)); /*i.e. fragment (3)*/
        pagerAdapter.addFragment(new SignOutFragment(), getString(R.string.sign_out_fragment)); /*i.e. fragment (4)*/


    }

    private void setViewPager(int fragmentNumber){

        mRelativeLayout.setVisibility(View.GONE);
        Log.d(TAG, "setViewPager: navigating to fragment #: " + fragmentNumber);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setCurrentItem(fragmentNumber);

    }
}




