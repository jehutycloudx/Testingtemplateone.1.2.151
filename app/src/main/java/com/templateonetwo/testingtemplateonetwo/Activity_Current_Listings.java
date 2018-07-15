package com.templateonetwo.testingtemplateonetwo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TextView;

import com.templateonetwo.testingtemplateonetwo.Utils.SectionsPagerAdapter;

public class Activity_Current_Listings extends AppCompatActivity {
    ViewPager mViewPager;
    android.support.design.widget.TabLayout mTabLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_currentlistings);

        TextView CL_title = (TextView) findViewById(R.id.activityTitle_Current_Listings);

        /*code below is to specifically disable shiftmode on bottom nav bar*/
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        /*simple code to fix the highlighting of the appropriate bottom nav icon when tapped, menu item index is set to '1' for first icon*/
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        /*below you will need to navigate to different areas when the nav buttons are pressed and you
          will need to use switch statements, breaks, and the code below */
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.ic_new_project:
                        Intent intent0 = new Intent(Activity_Current_Listings.this, MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.ic_current_listings:

                        break;


                    case R.id.ic_find_contractor:
                        Intent intent2 = new Intent(Activity_Current_Listings.this, Activity_Find_Contractors.class);
                        startActivity(intent2);
                        break;


                    case R.id.ic_chat:
                        Intent intent3 = new Intent(Activity_Current_Listings.this, Activity_Chat.class);
                        startActivity(intent3);

                        break;


                    case R.id.ic_settings:
                        Intent intent4 = new Intent(Activity_Current_Listings.this, Activity_Settings.class);
                        startActivity(intent4);

                        break;
                }
                return false;
            }
        });

        setUpPagerTabs();

    }

    void setUpPagerTabs()
    {
        mViewPager=findViewById(R.id.customerListingscontainer);
        mViewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager(),this));
        mTabLayout=findViewById(R.id.sliding_tabs);
        mTabLayout.setupWithViewPager(mViewPager);

    }
}
