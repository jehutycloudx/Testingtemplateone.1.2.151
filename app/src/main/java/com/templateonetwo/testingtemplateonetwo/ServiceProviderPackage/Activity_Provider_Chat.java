package com.templateonetwo.testingtemplateonetwo.ServiceProviderPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.templateonetwo.testingtemplateonetwo.Activity_Current_Listings;
import com.templateonetwo.testingtemplateonetwo.Activity_Find_Contractors;
import com.templateonetwo.testingtemplateonetwo.Activity_Settings;
import com.templateonetwo.testingtemplateonetwo.BottomNavigationViewHelper;
import com.templateonetwo.testingtemplateonetwo.MainActivity;
import com.templateonetwo.testingtemplateonetwo.R;

public class Activity_Provider_Chat extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chat);

        TextView Chat_title = (TextView) findViewById(R.id.activityTitle_Chat);

        /*code below is to specifically disable shiftmode on bottom nav bar*/
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        /*simple code to fix the highlighting of the appropriate bottom nav icon when tapped, menu item index is set to '3' for first icon*/
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        /*below you will need to navigate to different areas when the nav buttons are pressed and you
          will need to use switch statements, breaks, and the code below */
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.ic_new_project:
                        Intent intent0 = new Intent(Activity_Provider_Chat.this, MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.ic_current_listings:
                        Intent intent1 = new Intent(Activity_Provider_Chat.this, Activity_Current_Listings.class);
                        startActivity(intent1);
                        break;


                    case R.id.ic_find_contractor:
                        Intent intent2 = new Intent(Activity_Provider_Chat.this, Activity_Find_Contractors.class);
                        startActivity(intent2);
                        break;


                    case R.id.ic_chat:

                        break;


                    case R.id.ic_settings:
                        Intent intent4 = new Intent(Activity_Provider_Chat.this, Activity_Settings.class);
                        startActivity(intent4);

                        break;
                }
                return false;
            }
        });
    }

}
